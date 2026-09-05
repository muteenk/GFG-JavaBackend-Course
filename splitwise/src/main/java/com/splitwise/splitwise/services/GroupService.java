package com.splitwise.splitwise.services;

import com.splitwise.splitwise.dtos.request.AddMembersRequest;
import com.splitwise.splitwise.dtos.request.CreateGroupRequest;
import com.splitwise.splitwise.dtos.response.GroupSummaryResponse;
import com.splitwise.splitwise.entites.SplitGroup;
import com.splitwise.splitwise.entites.User;

import java.util.List;

public interface GroupService {
    SplitGroup createGroup(CreateGroupRequest createGroupRequest, String userId);

    List<GroupSummaryResponse> getGroupsSummaryForUser(String userId);

    List<User> addMembers(String userId, String groupId, AddMembersRequest addMembersRequest);
}
