package com.capitalone.dashboard.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capitalone.dashboard.model.FeatureBranch;
import com.capitalone.dashboard.repository.FeatureBranchRepository;

public class FeatureBranchRepositoryTests extends FongoBaseRepositoryTest {

	private static FeatureBranch featureOne;
	private static FeatureBranch featureTwo;
	private static FeatureBranch featureThree;

	@Autowired
	private FeatureBranchRepository featureBranchRepository;

	@Before
	public void setUp() {

		featureOne = new FeatureBranch();
		featureOne.setCommitIdFirstCommit("G001_FC");
		featureOne.setCommitIdThatTriggeredBuild("G011_TB");
		featureOne.setGitRepoUrl("api.github.com/repo/commits/G001-G011");
		featureOne.setDeployTimeStamp(1499153944L);		//2017/07/04 :: 7:39:04
		featureOne.setFirstCommitTimeStamp(1499253944L); 	// 2017/07/05 :: 11:25:44
		
		featureTwo = new FeatureBranch();
		featureTwo.setCommitIdFirstCommit("G002_FC");
		featureTwo.setCommitIdThatTriggeredBuild("G022_TB");
		featureTwo.setGitRepoUrl("api.github.com/repo/commits/G002-G022");
		featureTwo.setDeployTimeStamp(1499353944L);	// 2017/07/06 :: 15:12:24
		featureTwo.setFirstCommitTimeStamp(1499453944L); //2017/07/07 :: 18:59:04

		featureThree = new FeatureBranch();
		featureThree.setCommitIdFirstCommit("G003_FC");
		featureThree.setCommitIdThatTriggeredBuild("G033_TB");
		featureThree.setGitRepoUrl("api.github.com/repo/commits/G003-G033");
		featureThree.setDeployTimeStamp(1499553944L);	// 2017/07/08 22:45:54
		featureThree.setFirstCommitTimeStamp(1499653944L); //2017/07/10 02:32:24
	}

	@After
	public void tearDown() {
		featureOne = null;
		featureTwo = null;
		featureThree = null;
		featureBranchRepository.deleteAll();
	}

	@Test
	public void validateSave() {
		featureBranchRepository.save(featureOne);
		featureBranchRepository.save(featureTwo);

		assertTrue("Save function validation for the FeatureBranchRepository has failed",
				featureBranchRepository.findAll().iterator().hasNext());
	}


	@Test
	public void testFindByCommitIdThatTriggeredBuild()
	{
		featureBranchRepository.save(featureOne);
		// featureBranchRepository.save(featureTwo);
		String commitIdFirstCommit = featureOne.getCommitIdFirstCommit();

		FeatureBranch testCompare = featureBranchRepository.findByCommitIdThatTriggeredBuild("G011_TB");

		assertEquals("Expected FeatureBranch Commit ID did not match ; findByCommitIdThatTriggeredBuild() not functioning as required ",
			testCompare.getCommitIdFirstCommit(), commitIdFirstCommit);
	} 	

	@Test
	public void testFindByCommitIdFirstCommit()
	{
		featureBranchRepository.save(featureOne);
		// featureBranchRepository.save(featureTwo);
		String commitIdThatTriggeredBuild = featureOne.getCommitIdThatTriggeredBuild();

		FeatureBranch testCompare = featureBranchRepository.findByCommitIdFirstCommit("G001_FC");

		assertEquals("Expected FeatureBranch Commit ID did not match ; findByCommitIdFirstCommit() not functioning as required ",
			testCompare.getCommitIdThatTriggeredBuild(), commitIdThatTriggeredBuild);		
	}

	@Test
	public void testFindByDeployTimeStampGTE()
	{
		featureBranchRepository.save(featureOne);
		featureBranchRepository.save(featureTwo);
		featureBranchRepository.save(featureThree);


		List<FeatureBranch> listFeatureBranch = featureBranchRepository.findByDeployTimeStampGTE(1234567L);

		int num_elems = listFeatureBranch.size();

		assertEquals("FeatureBranch => findByDeployTimeStampGTE() not functioning as required ",
			num_elems,3);

	}

	@Test
	public void testFindByDeployTimeStampLTE()
	{
		featureBranchRepository.save(featureOne);
		featureBranchRepository.save(featureTwo);
		featureBranchRepository.save(featureThree);


		List<FeatureBranch> listFeatureBranch = featureBranchRepository.findByDeployTimeStampLTE(1499453944L);

		int num_elems = listFeatureBranch.size();

		assertEquals("FeatureBranch => findByDeployTimeStampLTE() not functioning as required ",
			num_elems,2);
	}

	@Test
	public void testFindByFirstCommitTimeStampGTE()
	{
		featureBranchRepository.save(featureOne);
		featureBranchRepository.save(featureTwo);
		featureBranchRepository.save(featureThree);


		List<FeatureBranch> listFeatureBranch = featureBranchRepository.findByFirstCommitTimeStampGTE(1499453944L);

		int num_elems = listFeatureBranch.size();

		assertEquals("FeatureBranch => findByFirstCommitTimeStampGTE() not functioning as required ",
			num_elems,2);
	}

	@Test
	public void testFindByFirstCommitTimeStampLTE()
	{
		featureBranchRepository.save(featureOne);
		featureBranchRepository.save(featureTwo);
		featureBranchRepository.save(featureThree);


		List<FeatureBranch> listFeatureBranch = featureBranchRepository.findByFirstCommitTimeStampLTE(1499553944L);

		int num_elems = listFeatureBranch.size();

		assertEquals("FeatureBranch => findByFirstCommitTimeStampLTE() not functioning as required ",
			num_elems,2);
	}

	
}