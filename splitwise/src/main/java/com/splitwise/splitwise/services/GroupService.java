package com.splitwise.splitwise.services;

import com.splitwise.splitwise.dtos.request.AddMembersRequest;
import com.splitwise.splitwise.dtos.request.CreateGroupRequest;
import com.splitwise.splitwise.entites.SplitGroup;
import com.splitwise.splitwise.entites.User;
import com.splitwise.splitwise.repositories.projections.GroupSummaryProjection;

import java.util.List;

public interface GroupService {
    SplitGroup createGroup(CreateGroupRequest createGroupRequest, String userId);

    List<GroupSummaryProjection> getGroupsSummaryForUser(String userId);

    List<User> addMembers(String userId, String groupId, AddMembersRequest addMembersRequest);
}
