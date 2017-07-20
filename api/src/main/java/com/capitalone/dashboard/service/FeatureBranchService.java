package com.capitalone.dashboard.service;

import java.util.List;
import com.capitalone.dashboard.model.FeatureBranch;

public interface FeatureBranchService {

    List<FeatureBranch> getFeatureBranchByTimeFrame(long timestamp1, long timestamp2);
    
    List<FeatureBranch> getFeatureBranchByFirstCommitTimeFrame(long timestamp1, long timestamp2);
    
    List<FeatureBranch> getFeatureBranchByDeployTimeFrame(long timestamp1, long timestamp2);
}
