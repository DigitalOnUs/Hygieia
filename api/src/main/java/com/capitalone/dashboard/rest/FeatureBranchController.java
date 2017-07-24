package com.capitalone.dashboard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.capitalone.dashboard.service.FeatureBranchService;
import com.capitalone.dashboard.model.FeatureBranch;

import java.util.List;
import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class FeatureBranchController {
    
    private final FeatureBranchService featureBranchService;

    @Autowired
    public FeatureBranchController(FeatureBranchService featureBranchService) {
        this.featureBranchService = featureBranchService;
    }

    @RequestMapping(value = "/feature_branches", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeatureBranch>> getFeatureBranchesWithinTimeFrame(
        @RequestParam("filter") String filter, @RequestParam("start_time") Long startTime, 
        @RequestParam("end_time") Long endTime) {

        List<FeatureBranch> featureBranchList;
        HttpStatus httpStatus = HttpStatus.OK;
        
        switch(filter){

            case "first_commit":
                featureBranchList = featureBranchService.getFeatureBranchByFirstCommitTimeFrame(startTime, endTime);
                break;

            case "deploy" :
                featureBranchList = featureBranchService.getFeatureBranchByDeployTimeFrame(startTime, endTime);
                break;

            case "none" :
                featureBranchList = featureBranchService.getFeatureBranchByTimeFrame(startTime, endTime);
                break;

            default :
                httpStatus = HttpStatus.BAD_REQUEST;
                featureBranchList = new ArrayList();
                break;
        }
 
        return ResponseEntity
                .status(httpStatus)
                .body(featureBranchList);
    }
}

