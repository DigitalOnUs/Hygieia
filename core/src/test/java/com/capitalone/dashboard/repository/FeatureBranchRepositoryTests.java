package com.capitalone.dashboard.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.lang.Iterable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capitalone.dashboard.model.FeatureBranch;
import com.capitalone.dashboard.repository.FeatureBranchRepository;

public class FeatureBranchRepositoryTests extends FongoBaseRepositoryTest {

	private static FeatureBranch featureBranchOne;
	private static FeatureBranch featureBranchTwo;
	private static FeatureBranch featureBranchThree;
	private static FeatureBranch featureBranchFour;

	@Autowired
	private FeatureBranchRepository featureBranchRepository;

	@Before
	public void setUp() {

		featureBranchOne = new FeatureBranch();
		featureBranchOne.setName("F1");
		featureBranchOne.setCommitIdFirstCommit("G001_FC");
		featureBranchOne.setCommitIdThatTriggeredDeploy("G011_TB");
		featureBranchOne.setGitRepoUrl("api.github.com/repo/commits/G001-G011");
		featureBranchOne.setFirstCommitTimeStamp(1499153944L);		//2017/07/04 :: 7:39:04
		featureBranchOne.setDeployTimeStamp(1499253944L); 			// 2017/07/05 :: 11:25:44
		
		featureBranchTwo = new FeatureBranch();
		featureBranchTwo.setName("F2");
		featureBranchTwo.setCommitIdFirstCommit("G002_FC");
		featureBranchTwo.setCommitIdThatTriggeredDeploy("G022_TB");
		featureBranchTwo.setGitRepoUrl("api.github.com/repo/commits/G002-G022");
		featureBranchTwo.setFirstCommitTimeStamp(1499353944L);		// 2017/07/06 :: 15:12:24
		featureBranchTwo.setDeployTimeStamp(1499453944L); 			//2017/07/07 :: 18:59:04

		featureBranchThree = new FeatureBranch();
		featureBranchThree.setName("F3");
		featureBranchThree.setCommitIdFirstCommit("G003_FC");
		featureBranchThree.setCommitIdThatTriggeredDeploy("G033_TB");
		featureBranchThree.setGitRepoUrl("api.github.com/repo/commits/G003-G033");
		featureBranchThree.setFirstCommitTimeStamp(1499553944L);	// 2017/07/08 22:45:54
		featureBranchThree.setDeployTimeStamp(1499653944L); 		//2017/07/10 02:32:24

		featureBranchFour = new FeatureBranch();
		featureBranchFour.setName("F4");
		featureBranchFour.setCommitIdThatTriggeredDeploy("G04_TB");
		featureBranchFour.setGitRepoUrl("api.github.com/repo/commits/G004-G04");
		featureBranchFour.setDeployTimeStamp(1499753944L); 		//2017/07/11 06:19:14
	}

	@After
	public void tearDown() {
		featureBranchOne = null;
		featureBranchTwo = null;
		featureBranchThree = null;
		featureBranchFour = null;
		featureBranchRepository.deleteAll();
	}

	@Test
	public void validateSave() {
		featureBranchRepository.save(featureBranchOne);
		featureBranchRepository.save(featureBranchTwo);
		featureBranchRepository.save(featureBranchThree);

		Iterable<FeatureBranch> featureBranchIterable = featureBranchRepository.findAll();
		int size = 0;
		for ( FeatureBranch featureBranchObject : featureBranchIterable )
		{
			size++; 
		}

		assertEquals("Save function validation for the FeatureBranchRepository has failed",
				size,3);
	}


	@Test
	public void testFindById()
	{
		featureBranchRepository.save(featureBranchOne);

		FeatureBranch testCompare = featureBranchRepository.findById(featureBranchOne.getId());

		assertEquals("FeatureBranchRepository's findById() not functioning as expected",
			testCompare.getCommitIdFirstCommit(), featureBranchOne.getCommitIdFirstCommit());
	}


	@Test
	public void testFindByCommitIdThatTriggeredDeploy()
	{
		featureBranchRepository.save(featureBranchOne);
		String commitIdFirstCommit = featureBranchOne.getCommitIdFirstCommit();

		FeatureBranch testCompare = featureBranchRepository.findByCommitIdThatTriggeredDeploy("G011_TB");

		assertEquals("Expected FeatureBranch Commit ID did not match ; findByCommitIdThatTriggeredDeploy() not functioning as required ",
			testCompare.getCommitIdFirstCommit(), commitIdFirstCommit);
	} 	

	@Test
	public void testFindByCommitIdFirstCommit()
	{
		featureBranchRepository.save(featureBranchOne);
		String commitIdThatTriggeredDeploy = featureBranchOne.getCommitIdThatTriggeredDeploy();

		FeatureBranch testCompare = featureBranchRepository.findByCommitIdFirstCommit("G001_FC");

		assertEquals("Expected FeatureBranch Commit ID did not match ; findByCommitIdFirstCommit() not functioning as required ",
			testCompare.getCommitIdThatTriggeredDeploy(), commitIdThatTriggeredDeploy);		
	}

	@Test
	public void testFindByNull()
	{
		featureBranchRepository.save(featureBranchOne);
		featureBranchRepository.save(featureBranchFour);

		assertEquals("FeatureBranchRepository's findByNull() not functioning as expected",
			featureBranchRepository.findByNull().size(),1);

		featureBranchFour.setCommitIdFirstCommit("G004-G04");
		featureBranchFour.setFirstCommitTimeStamp(1499753944L);
		featureBranchRepository.save(featureBranchFour);

		assertEquals("FeatureBranchRepository's findByNull() not functioning as expected",
			featureBranchRepository.findByNull().size(),0);
	}	

	/*
		For tests, FeatureBranch objects are created in a manner such that
			
			featureBranchOne.firstCommitTimeStamp < featureBranchTwo.firstCommitTimeStamp  < featureBranchThree.firstCommitTimeStamp 
			
			featureBranchOne.deployTimeStamp < featureBranchTwo.deployTimeStamp < featureBranchThree.deployTimeStamp

			FeatureBranch.firstCommitTimeStamp < FeatureBranch.deployTimeStamp
	*/
	
	 	@Test
 	public void testFindByFirstCommitTimeFrame()
 	{
 		featureBranchRepository.save(featureBranchOne);
 		featureBranchRepository.save(featureBranchTwo);
 		featureBranchRepository.save(featureBranchThree);

 		List<FeatureBranch> featureBranchList = featureBranchRepository.findByFirstCommitTimeFrame(
 			featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp());

 		assertEquals("FeatureBranchRepository's findByFirstCommitTimeFrame() not functioning as expected ",
 			featureBranchList.size(),3);

 		featureBranchList = featureBranchRepository.findByFirstCommitTimeFrame(
 			featureBranchOne.getFirstCommitTimeStamp(), featureBranchTwo.getDeployTimeStamp());

 		assertEquals("FeatureBranchRepository's findByFirstCommitTimeFrame() not functioning as expected ",
 			featureBranchList.size(),2);

 		featureBranchList = featureBranchRepository.findByFirstCommitTimeFrame(
 			featureBranchOne.getDeployTimeStamp(),featureBranchTwo.getDeployTimeStamp());

 		assertEquals("FeatureBranchRepository's findByFirstCommitTimeFrame() not functioning as expected ",
 			featureBranchList.size(),1);
 		assertEquals("FeatureBranchRepository's findByFirstCommitTimeFrame() not functioning as expected ",
 			featureBranchList.get(0).getId(),featureBranchTwo.getId());
 	}

 	@Test
 	public void testFindByDeployTimeFrame()
 	{
 		featureBranchRepository.save(featureBranchOne);
 		featureBranchRepository.save(featureBranchTwo);
 		featureBranchRepository.save(featureBranchThree);

 		List<FeatureBranch> featureBranchList = featureBranchRepository.findByDeployTimeFrame(
 			featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp());

 		assertEquals("FeatureBranchRepository's findByDeployTimeFrame() not functioning as expected ",
 			featureBranchList.size(),3);

 		featureBranchList = featureBranchRepository.findByDeployTimeFrame(
 			featureBranchOne.getFirstCommitTimeStamp(), featureBranchTwo.getDeployTimeStamp());

 		assertEquals("FeatureBranchRepository's findByDeployTimeFrame() not functioning as expected ",
 			featureBranchList.size(),2);

 		featureBranchList = featureBranchRepository.findByDeployTimeFrame(
 			featureBranchTwo.getFirstCommitTimeStamp(),featureBranchTwo.getDeployTimeStamp());

 		assertEquals("FeatureBranchRepository's findByDeployTimeFrame() not functioning as expected ",
 			featureBranchList.size(),1);
 		assertEquals("FeatureBranchRepository's findByDeployTimeFrame() not functioning as expected ",
 			featureBranchList.get(0).getId(),featureBranchTwo.getId());
 	}

 	@Test
 	public void testFindByTimeFrame()
 	{
 		featureBranchRepository.save(featureBranchOne);
 		featureBranchRepository.save(featureBranchTwo);
 		featureBranchRepository.save(featureBranchThree);

 		List<FeatureBranch> featureBranchList = featureBranchRepository.findByTimeFrame(
 			featureBranchOne.getFirstCommitTimeStamp(), featureBranchThree.getDeployTimeStamp());

 		assertEquals("FeatureBranchRepository's findByTimeFrame() not functioning as expected ",
 			featureBranchList.size(),3);

 		featureBranchList = featureBranchRepository.findByTimeFrame(
 			featureBranchOne.getFirstCommitTimeStamp(), featureBranchTwo.getDeployTimeStamp());

 		assertEquals("FeatureBranchRepository's findByTimeFrame() not functioning as expected ",
 			featureBranchList.size(),2);

 		featureBranchList = featureBranchRepository.findByTimeFrame(
 			featureBranchOne.getDeployTimeStamp(),featureBranchTwo.getDeployTimeStamp());

 		assertEquals("FeatureBranchRepository's findByTimeFrame() not functioning as expected ",
 			featureBranchList.size(),1);
 		assertEquals("FeatureBranchRepository's findByTimeFrame() not functioning as expected ",
 			featureBranchList.get(0).getId(),featureBranchTwo.getId());
 	}

}