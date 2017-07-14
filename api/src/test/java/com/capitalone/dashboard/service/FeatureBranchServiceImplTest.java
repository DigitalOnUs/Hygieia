package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.FeatureBranch;
import com.capitalone.dashboard.repository.FeatureBranchRepository;
import com.capitalone.dashboard.service.FeatureBranchServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeatureBranchServiceImplTest {

    private static FeatureBranch featureBranchOne;
    private static FeatureBranch featureBranchTwo;
    private static FeatureBranch featureBranchThree;
    private static FeatureBranch featureBranchFour;

    @Mock
    private FeatureBranchRepository featureBranchRepository;
    
    @InjectMocks
    private FeatureBranchServiceImpl featureBranchService;
    

    @Before
    public void setup() {

        featureBranchOne = new FeatureBranch();
        featureBranchOne.setName("F1");
        featureBranchOne.setCommitIdFirstCommit("G001_FC");
        featureBranchOne.setCommitIdThatTriggeredDeploy("G011_TB");
        featureBranchOne.setGitRepoUrl("api.github.com/repo/commits/G001-G011");
        featureBranchOne.setFirstCommitTimeStamp(1499153944L);      //2017/07/04 :: 7:39:04
        featureBranchOne.setDeployTimeStamp(1499253944L);           // 2017/07/05 :: 11:25:44
        
        featureBranchTwo = new FeatureBranch();
        featureBranchTwo.setName("F2");
        featureBranchTwo.setCommitIdFirstCommit("G002_FC");
        featureBranchTwo.setCommitIdThatTriggeredDeploy("G022_TB");
        featureBranchTwo.setGitRepoUrl("api.github.com/repo/commits/G002-G022");
        featureBranchTwo.setFirstCommitTimeStamp(1499353944L);      // 2017/07/06 :: 15:12:24
        featureBranchTwo.setDeployTimeStamp(1499453944L);           //2017/07/07 :: 18:59:04

        featureBranchThree = new FeatureBranch();
        featureBranchThree.setName("F3");
        featureBranchThree.setCommitIdFirstCommit("G003_FC");
        featureBranchThree.setCommitIdThatTriggeredDeploy("G033_TB");
        featureBranchThree.setGitRepoUrl("api.github.com/repo/commits/G003-G033");
        featureBranchThree.setFirstCommitTimeStamp(1499553944L);    // 2017/07/08 22:45:54
        featureBranchThree.setDeployTimeStamp(1499653944L);         //2017/07/10 02:32:24   

        featureBranchService.save(featureBranchOne);
        featureBranchService.save(featureBranchTwo);
        featureBranchService.save(featureBranchThree);

    }

    @After
    public void cleanup() {
     
     featureBranchOne = null;
     featureBranchTwo = null;
     featureBranchThree = null;
     featureBranchRepository.deleteAll();
    }

    private List<FeatureBranch> getFeatureBranchList()
    {
        List<FeatureBranch> featureBranchList = new ArrayList();

        featureBranchList.add(featureBranchOne);
        featureBranchList.add(featureBranchTwo);
        featureBranchList.add(featureBranchThree);

        return featureBranchList;
    }

    @Test
    public void validateSave() {

        List<FeatureBranch> featureBranchList = getFeatureBranchList();

        when(featureBranchRepository.findAll()).thenReturn(featureBranchList);

        assertEquals("Save function validation for the FeatureBranchRepository has failed",
                featureBranchService.findAll().size(),3);
        verify(featureBranchRepository).findAll();
    }

    @Test 
    public void getFeatureBranchByFirstCommitTimeFrameTest()
    {
        List<FeatureBranch> featureBranchList = getFeatureBranchList();

        when(featureBranchRepository.findByFirstCommitTimeFrame(
            featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp()))
        .thenReturn(featureBranchList);

        List<FeatureBranch> result = featureBranchService.getFeatureBranchByFirstCommitTimeFrame(
            featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp());

        assertEquals("getFeatureBranchByFirstCommitTimeFrame() for FeatureBranchServiceImpl has failed",
            result, featureBranchList);
        verify(featureBranchRepository).findByFirstCommitTimeFrame(
            featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp());
    }

    @Test 
    public void getFeatureBranchByDeployTimeFrameTest()
    {
        List<FeatureBranch> featureBranchList = getFeatureBranchList();

        when(featureBranchRepository.findByDeployTimeFrame(
            featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp()))
        .thenReturn(featureBranchList);

        List<FeatureBranch> result = featureBranchService.getFeatureBranchByDeployTimeFrame(
            featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp());

        assertEquals("getFeatureBranchByDeployTimeFrame() for FeatureBranchServiceImpl has failed",
            result, featureBranchList);
        verify(featureBranchRepository).findByDeployTimeFrame(
            featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp());
    }

    @Test 
    public void getFeatureBranchByTimeFrameTest()
    {
        List<FeatureBranch> featureBranchList = getFeatureBranchList();

        when(featureBranchRepository.findByTimeFrame(
            featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp()))
        .thenReturn(featureBranchList);

        List<FeatureBranch> result = featureBranchService.getFeatureBranchByTimeFrame(
            featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp());

        assertEquals("getFeatureBranchByTimeFrame() for FeatureBranchServiceImpl has failed",
            result, featureBranchList);
        verify(featureBranchRepository).findByTimeFrame(
            featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp());
    }
}
