package com.capitalone.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitalone.dashboard.model.FeatureBranch;
import com.capitalone.dashboard.repository.FeatureBranchRepository;

import java.util.List;

@Service
public class FeatureBranchServiceImpl implements FeatureBranchService {

    private final FeatureBranchRepository featureBranchRepository;
    
    @Autowired
    public FeatureBranchServiceImpl(FeatureBranchRepository featureBranchRepository) {
        this.featureBranchRepository = featureBranchRepository;
    }

    @Override
    public List<FeatureBranch> getFeatureBranchByTimeFrame(long timestamp1, long timestamp2) {   	
    	return featureBranchRepository.findByTimeFrame(timestamp1,timestamp2);
    }

    @Override
    public List<FeatureBranch> getFeatureBranchByFirstCommitTimeFrame(long timestamp1, long timestamp2) {       
        return featureBranchRepository.findByFirstCommitTimeFrame(timestamp1,timestamp2);
    }

    @Override
    public List<FeatureBranch> getFeatureBranchByDeployTimeFrame(long timestamp1, long timestamp2) {       
        return featureBranchRepository.findByDeployTimeFrame(timestamp1,timestamp2);
    }
}