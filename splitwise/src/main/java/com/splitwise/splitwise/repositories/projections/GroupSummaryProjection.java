package com.splitwise.splitwise.repositories.projections;

import java.math.BigInteger;

public interface GroupSummaryProjection {
    String getGroupId();
    String getGroupName();
    String getGroupDescription();
    BigInteger getTotalPaid();
    BigInteger getTotalOwed();
}
