package com.capitalone.dashboard.repository;

import com.capitalone.dashboard.model.FeatureBranch;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeatureBranchRepository extends CrudRepository<FeatureBranch, ObjectId>, QueryDslPredicateExecutor<FeatureBranch> 
{
    @Query(value="{'deployTimeStamp' : { $gte : ?0 }}")
    List<FeatureBranch> findByDeployTimeStampGTE(long deployTimeStamp);

    @Query(value="{'deployTimeStamp' : { $lte : ?0 }}")
    List<FeatureBranch> findByDeployTimeStampLTE(long deployTimeStamp);

    @Query(value="{'firstCommitTimeStamp' : { $gte : ?0 }}")
    List<FeatureBranch> findByFirstCommitTimeStampGTE(long firstCommitTimeStamp);

    @Query(value="{'firstCommitTimeStamp' : { $lte : ?0 }}")
    List<FeatureBranch> findByFirstCommitTimeStampLTE(long firstCommitTimeStamp);

    FeatureBranch findByCommitIdFirstCommit(String commitIdFirstCommit);
    FeatureBranch findByCommitIdThatTriggeredBuild(String commitIdThatTriggeredBuild);

}
