package com.capitalone.dashboard.repository;

import com.capitalone.dashboard.model.FeatureBranch;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeatureBranchRepository extends CrudRepository<FeatureBranch, ObjectId>, QueryDslPredicateExecutor<FeatureBranch> 
{
    
    FeatureBranch findById(ObjectId id);
    FeatureBranch findByCommitIdFirstCommit(String commitIdFirstCommit);
    FeatureBranch findByCommitIdThatTriggeredBuild(String commitIdThatTriggeredBuild);

    /*
        purpose : to find rows that have null values for commitIdFirstCommit property. 
    */
    @Query(value="{ 'commitIdFirstCommit' : {$exists: false} }")
    List<FeatureBranch> findByNull();

}
