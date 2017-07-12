package com.capitalone.dashboard.service;

import java.util.List;
import com.capitalone.dashboard.model.FeatureBranch;
// import org.springframework.stereotype.Service;


// @Service
public interface FeatureBranchService {

    List<FeatureBranch> getFeatureBranchByTimeFrame(long timestamp1, long timestamp2);
    
    void save(FeatureBranch featureBranch);
}
