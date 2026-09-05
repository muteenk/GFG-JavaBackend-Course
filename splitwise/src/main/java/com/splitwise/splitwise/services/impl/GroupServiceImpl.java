package com.splitwise.splitwise.services.impl;

import com.splitwise.splitwise.dtos.request.AddMembersRequest;
import com.splitwise.splitwise.dtos.request.CreateGroupRequest;
import com.splitwise.splitwise.dtos.response.GroupSummaryResponse;
import com.splitwise.splitwise.entites.SplitGroup;
import com.splitwise.splitwise.entites.User;
import com.splitwise.splitwise.exceptions.ResourceDoesNotExist;
import com.splitwise.splitwise.repositories.ExpenseRepository;
import com.splitwise.splitwise.repositories.GroupRepository;
import com.splitwise.splitwise.repositories.UserRepository;
import com.splitwise.splitwise.services.GroupService;
import com.splitwise.splitwise.services.utility.RedisService;
import com.splitwise.splitwise.utilities.ExpenseUtility;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final RedisService redisService;

    @Override
    @Transactional
    public SplitGroup createGroup(CreateGroupRequest createGroupRequest, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceDoesNotExist("User with id: '" + userId + "', not found !")
                );

        SplitGroup splitGroup = SplitGroup.builder()
                .groupName(createGroupRequest.groupName())
                .description(createGroupRequest.description())
                .build();

        splitGroup.addUser(user);
        return groupRepository.save(splitGroup);
    }

    @Override
    public List<GroupSummaryResponse> getGroupsSummaryForUser(String userId) {
        String cacheKey = "user-group-summary:" + userId;

        List<GroupSummaryResponse> cached = redisService.get(
                cacheKey,
                new TypeReference<List<GroupSummaryResponse>>() {}
        );
        if (cached != null) {
            return cached;
        }

        List<GroupSummaryResponse> summary = expenseRepository.getGroupsSummaryForUser(userId).stream()
                .map(projection -> {
                    BigInteger totalPaid = projection.getTotalPaid();
                    BigInteger totalOwed = projection.getTotalOwed();
                    BigInteger totalBalance = totalOwed.subtract(totalPaid);
                    String balanceType = totalBalance.compareTo(BigInteger.ZERO) > 0 ? "Owed" : "Paid";
                    BigDecimal balanceInRupees = ExpenseUtility.covertToRupees(totalBalance);

                    return new GroupSummaryResponse(
                            projection.getGroupId(),
                            projection.getGroupName(),
                            projection.getGroupDescription(),
                            balanceInRupees,
                            balanceType
                    );
                })
                .toList();

        redisService.set(cacheKey, summary, 600L);
        return summary;
    }

    @Override
    @Transactional
    public List<User> addMembers(String userId, String groupId, AddMembersRequest addMembersRequest) {
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceDoesNotExist("User with id: '" + userId + "', not found !")
                );

        SplitGroup splitGroup = groupRepository.findByIdWithMembers(groupId)
                .orElseThrow(() ->
                        new ResourceDoesNotExist("Group with id: '" + groupId + "', not found !")
                );

        if (!groupRepository.existsByIdAndUsersContaining(groupId, currentUser)) {
            throw new ResourceDoesNotExist("User with id: '" + userId + "', is not a member of this group !");
        }

        List<User> addedMembers = new ArrayList<>();
        List<User> usersByEmails = userRepository.findByEmailIn(addMembersRequest.userEmails());

        if (usersByEmails.size() != addMembersRequest.userEmails().size()) {
            throw new ResourceDoesNotExist("One or more users with emails: '" + addMembersRequest.userEmails() + "', not found !");
        }

        for (User user : usersByEmails) {
            if (splitGroup.getUsers().contains(user)) {
                continue;
            }

            splitGroup.addUser(user);
            addedMembers.add(user);
        }

        groupRepository.save(splitGroup);
        return addedMembers;
    }
}
