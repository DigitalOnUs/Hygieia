package com.capitalone.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitalone.dashboard.model.FeatureBranch;
import com.capitalone.dashboard.repository.FeatureBranchRepository;

import java.util.List;
// import org.springframework.beans.factory.annotation.Qualifier;

@Service("mybranch")
public class FeatureBranchServiceImpl implements FeatureBranchService{

    private final FeatureBranchRepository featureBranchRepository;

    // public FeatureBranchServiceImpl(){}
    
    @Autowired
    public FeatureBranchServiceImpl(final FeatureBranchRepository featureBranchRepository) {
        this.featureBranchRepository = featureBranchRepository;
    }

    @Override
    public List<FeatureBranch> getFeatureBranchByTimeFrame(long timestamp1, long timestamp2)
    {   	
    	return featureBranchRepository.findByDeployTimeFrame(timestamp1,timestamp2);
    }

    @Override
    public void save(FeatureBranch featureBranch)
    {
    	featureBranchRepository.save(featureBranch);
    }
}