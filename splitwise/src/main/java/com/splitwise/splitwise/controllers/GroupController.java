package com.splitwise.splitwise.controllers;

import com.splitwise.splitwise.dtos.request.AddMembersRequest;
import com.splitwise.splitwise.dtos.request.CreateGroupRequest;
import com.splitwise.splitwise.dtos.response.AddMembersResponse;
import com.splitwise.splitwise.dtos.response.CommonUserResponse;
import com.splitwise.splitwise.dtos.response.CreateGroupResponse;
import com.splitwise.splitwise.dtos.response.GroupSummaryResponse;
import com.splitwise.splitwise.entites.SplitGroup;
import com.splitwise.splitwise.entites.User;
import com.splitwise.splitwise.payloads.ApiResponse;
import com.splitwise.splitwise.repositories.projections.GroupSummaryProjection;
import com.splitwise.splitwise.services.GroupService;
import com.splitwise.splitwise.utilities.ExpenseUtility;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateGroupResponse>> createNewGroup(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody @Valid CreateGroupRequest createGroupRequest
    ) {
        SplitGroup newGroup = groupService.createGroup(createGroupRequest, userId);
        CreateGroupResponse createGroupResponse = new CreateGroupResponse(
                newGroup.getId(),
                newGroup.getGroupName(),
                newGroup.getDescription()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                "Group Created Successfully !",
                createGroupResponse
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GroupSummaryResponse>>> getGroupDetails(
            @RequestHeader("X-User-Id") String userId
    ) {
        List<GroupSummaryProjection> groupSummary = groupService.getGroupsSummaryForUser(userId);

        List<GroupSummaryResponse> groupSummaryResponses = groupSummary.stream()
                .map(groupSummaryProjection -> {
                    BigInteger totalPaid = groupSummaryProjection.getTotalPaid();
                    BigInteger totalOwed = groupSummaryProjection.getTotalOwed();
                    BigInteger totalBalance = totalOwed.subtract(totalPaid);
                    String balanceType = totalBalance.compareTo(BigInteger.ZERO) > 0 ? "Owed" : "Paid";
                    BigDecimal balanceInRupees = ExpenseUtility.covertToRupees(totalBalance);

                    return new GroupSummaryResponse(
                        groupSummaryProjection.getGroupId(),
                        groupSummaryProjection.getGroupName(),
                        groupSummaryProjection.getGroupDescription(),
                        balanceInRupees,
                        balanceType
                    );
                })
                .toList();
        return ResponseEntity.ok(ApiResponse.success("Group details fetched successfully", groupSummaryResponses));
    }

    @PostMapping("/{groupId}/members")
    public ResponseEntity<ApiResponse<AddMembersResponse>> addMembers(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String groupId,
            @RequestBody @Valid AddMembersRequest addMembersRequest
    ) {
        List<User> addedMembers = groupService.addMembers(userId, groupId, addMembersRequest);
        
        List<CommonUserResponse> memberResponses = addedMembers.stream()
                .map(user -> new CommonUserResponse(user.getId(), user.getName(), user.getEmail()))
                .toList();

        AddMembersResponse addMembersResponse = new AddMembersResponse(groupId, memberResponses);

        return ResponseEntity.ok(
                ApiResponse.success("Members added successfully", addMembersResponse)
        );
    }

}
