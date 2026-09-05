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
import com.splitwise.splitwise.services.GroupService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateGroupResponse>> createNewGroup(
            @RequestBody @Valid CreateGroupRequest createGroupRequest,
            Principal principal
    ) {
        SplitGroup newGroup = groupService.createGroup(createGroupRequest, principal.getName());
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
            Principal principal
    ) {
        List<GroupSummaryResponse> groupSummaryResponses =
                groupService.getGroupsSummaryForUser(principal.getName());
        return ResponseEntity.ok(
                ApiResponse.success("Group details fetched successfully", groupSummaryResponses)
        );
    }

    @PostMapping("/{groupId}/members")
    public ResponseEntity<ApiResponse<AddMembersResponse>> addMembers(
            @PathVariable String groupId,
            @RequestBody @Valid AddMembersRequest addMembersRequest,
            Principal principal
    ) {
        List<User> addedMembers = groupService.addMembers(principal.getName(), groupId, addMembersRequest);
        
        List<CommonUserResponse> memberResponses = addedMembers.stream()
                .map(user -> new CommonUserResponse(user.getId(), user.getName(), user.getEmail()))
                .toList();

        AddMembersResponse addMembersResponse = new AddMembersResponse(groupId, memberResponses);

        return ResponseEntity.ok(
                ApiResponse.success("Members added successfully", addMembersResponse)
        );
    }

}
