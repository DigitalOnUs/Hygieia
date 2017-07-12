// package com.capitalone.dashboard.rest;

// import static org.hamcrest.Matchers.hasSize;
// import static org.hamcrest.Matchers.is;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.util.List;
// import java.util.ArrayList;


// import org.bson.types.ObjectId;
// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;

// import com.capitalone.dashboard.config.TestConfig;
// import com.capitalone.dashboard.config.WebMVCConfig;
// import com.capitalone.dashboard.model.Collector;
// import com.capitalone.dashboard.model.CollectorItem;
// import com.capitalone.dashboard.model.CollectorType;
// import com.capitalone.dashboard.model.Component;
// import com.capitalone.dashboard.model.DataResponse;
// import com.capitalone.dashboard.model.FeatureBranch;
// import com.capitalone.dashboard.service.FeatureBranchService;

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = { TestConfig.class, WebMVCConfig.class })
// @WebAppConfiguration
// public class FeatureBranchControllerTest {

//     private static FeatureBranch featureBranchOne;
//     private static FeatureBranch featureBranchTwo;

    
//     private MockMvc mockMvc;

//     @Autowired
//     private WebApplicationContext wac;
    
//     @Autowired
//     private FeatureBranchService featureBranchService;

//     @Before
//     public void before() {
//         mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

//         featureBranchOne = new FeatureBranch();
//         featureBranchOne.setName("F1");
//         featureBranchOne.setCommitIdFirstCommit("G001_FC");
//         featureBranchOne.setCommitIdThatTriggeredDeploy("G011_TB");
//         featureBranchOne.setGitRepoUrl("api.github.com/repo/commits/G001-G011");
//         featureBranchOne.setFirstCommitTimeStamp(1499153944L);      //2017/07/04 :: 7:39:04
//         featureBranchOne.setDeployTimeStamp(1499253944L);           // 2017/07/05 :: 11:25:44
        
//         featureBranchTwo = new FeatureBranch();
//         featureBranchTwo.setName("F2");
//         featureBranchTwo.setCommitIdFirstCommit("G002_FC");
//         featureBranchTwo.setCommitIdThatTriggeredDeploy("G022_TB");
//         featureBranchTwo.setGitRepoUrl("api.github.com/repo/commits/G002-G022");
//         featureBranchTwo.setFirstCommitTimeStamp(1499353944L);      // 2017/07/06 :: 15:12:24
//         featureBranchTwo.setDeployTimeStamp(1499453944L);           //2017/07/07 :: 18:59:04
        
//     }

//     @After
//     public void after() {
//        featureBranchOne = null;
//        featureBranchTwo = null;  
//        mockMvc = null;
//     }

//     @Test
//     public void testRelevantStories_HappyPath() throws Exception {
 

//         featureBranchService.save(featureBranchOne);
//         featureBranchService.save(featureBranchTwo);
//         List<FeatureBranch> response = new ArrayList();
//         response.add(featureBranchOne);
//         response.add(featureBranchTwo);

//         // DataResponse<List<Feature>> response = new DataResponse<>(features,
//                 // mockV1Collector.getLastExecuted());

//         when(featureBranchService.getFeatureBranchByTimeFrame(
//             featureBranchOne.getFirstCommitTimeStamp(), featureBranchTwo.getDeployTimeStamp())).thenReturn(response);
//         mockMvc.perform(get("/featurebranch/" 
//             + featureBranchOne.getFirstCommitTimeStamp() + 
//             featureBranchTwo.getDeployTimeStamp()))
//                 .andExpect(status().isOk());
//     }

//     // @Test
//     // public void testFeatureEstimates_HappyPath() throws Exception {
//     //     String testTeamId = mockV1Feature.getsTeamID();
//     //     String testProjectId = mockV1Feature.getsProjectID();       
//     //     List<Feature> features = new ArrayList<Feature>();
//     //     features.add(mockV1Feature);
//     //     features.add(mockJiraFeature);
//     //     features.add(mockJiraFeature2);
//     //     DataResponse<List<Feature>> response = new DataResponse<>(features,
//     //             mockV1Collector.getLastExecuted());

//     //     when(featureService.getFeatureEpicEstimates(mockComponentId, testTeamId, testProjectId, Optional.empty(), Optional.empty())).thenReturn(response);
//     //     mockMvc.perform(
//     //             get("/feature/estimates/super/" + testTeamId + "?component="
//     //                     + mockComponentId.toString() + "&projectId=" + testProjectId)).andExpect(status().isOk());
//     // }

//     // @Test
//     // public void testFeatureEstimates_SameEpicWithEstimates_UniqueResponse() throws Exception {
//     //     String testTeamId = mockV1Feature.getsTeamID();
//     //     String testProjectId = mockV1Feature.getsProjectID();
//     //     List<Feature> features = new ArrayList<Feature>();
//     //     features.add(mockV1Feature);
//     //     features.add(mockJiraFeature);
//     //     features.add(mockJiraFeature2);
//     //     DataResponse<List<Feature>> response = new DataResponse<>(features,
//     //             mockV1Collector.getLastExecuted());

//     //     when(featureService.getFeatureEpicEstimates(mockComponentId, testTeamId, testProjectId, Optional.empty(), Optional.empty())).thenReturn(response);
//     //     mockMvc.perform(
//     //             get("/feature/estimates/super/" + testTeamId + "?component="
//     //                     + mockComponentId.toString() + "&projectId=" + testProjectId))
//     //             .andExpect(jsonPath("$.result[0].sEpicNumber", is(mockV1Feature.getsEpicNumber())))
//     //             .andExpect(jsonPath("$.result[0].sEstimate", is(mockV1Feature.getsEstimate())))
//     //             .andExpect(jsonPath("$.result", hasSize(3)));
//     // }
// }
