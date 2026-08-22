package com.splitwise.splitwise.services.impl;

import com.splitwise.splitwise.dtos.request.AddMembersRequest;
import com.splitwise.splitwise.dtos.request.CreateGroupRequest;
import com.splitwise.splitwise.entites.SplitGroup;
import com.splitwise.splitwise.entites.User;
import com.splitwise.splitwise.exceptions.ResourceDoesNotExist;
import com.splitwise.splitwise.exceptions.ResourceExistsException;
import com.splitwise.splitwise.repositories.ExpenseRepository;
import com.splitwise.splitwise.repositories.GroupRepository;
import com.splitwise.splitwise.repositories.UserRepository;
import com.splitwise.splitwise.repositories.projections.GroupSummaryProjection;
import com.splitwise.splitwise.services.GroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

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
    public List<GroupSummaryProjection> getGroupsSummaryForUser(String userId) {
        
        User _ = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceDoesNotExist("User with id: '" + userId + "', not found !")
                );

        return expenseRepository.getGroupsSummaryForUser(userId);
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
