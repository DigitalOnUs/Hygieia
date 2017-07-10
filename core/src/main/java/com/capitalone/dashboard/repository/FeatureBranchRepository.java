package com.capitalone.dashboard.repository;

import com.capitalone.dashboard.model.FeatureBranch;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeatureBranchRepository extends CrudRepository<FeatureBranch, ObjectId>, QueryDslPredicateExecutor<FeatureBranch> 
{
    /* 
        Case 1: Find all features for which the First Git Commit Timestamp falls within a given timeframe.
    */
    @Query(value="{'firstCommitTimeStamp' : { $gte : ?0 , $lte : ?1 }}")
    List<FeatureBranch> findByFirstCommitTimeFrame(long timeStamp1, long timeStamp2);


    /* 
        Case 2: Find all features for which the deploy Timestamp falls within a given timeframe.
    */
    @Query(value="{'deployTimeStamp' : { $gte : ?0 , $lte : ?1 }}")
    List<FeatureBranch> findByDeployTimeFrame(long timeStamp1, long timeStamp2);


    /* 
        Case 3: Find all features for which the First Git Commit Timestamp and deploy Timestamp falls within a given timeframe.
    */
    @Query(value="{'firstCommitTimeStamp' : { $gte : ?0} , 'deployTimeStamp' :{ $lte : ?1 } }")
    List<FeatureBranch> findByTimeFrame(long timeStamp1, long timeStamp2);

    
    FeatureBranch findById(ObjectId id);
    FeatureBranch findByCommitIdFirstCommit(String commitIdFirstCommit);
    FeatureBranch findByCommitIdThatTriggeredDeploy(String commitIdThatTriggeredDeploy);

    /*
        purpose : to find rows that have null values for commitIdFirstCommit property. 
    */
    @Query(value="{ 'commitIdFirstCommit' : {$exists: false} }")
    List<FeatureBranch> findByNull();

}
