package com.capitalone.dashboard.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capitalone.dashboard.service.FeatureBranchService;
import com.capitalone.dashboard.model.FeatureBranch;

import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class FeatureBranchController {
    
    private final FeatureBranchService featureBranchService;

    @Autowired
    public FeatureBranchController(FeatureBranchService featureBranchService) {
        this.featureBranchService = featureBranchService;
    }

    @RequestMapping(value = "/featurebranch/{timestamp1}/{timestamp2}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeatureBranch>> getFeatureBranchesWithinTimeFrame(
        @PathVariable Long timestamp1, @PathVariable Long timestamp2) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(featureBranchService.getFeatureBranchByTimeFrame(timestamp1, timestamp2));
    }

    @RequestMapping(value = "/featurebranch/firstcommit/{timestamp1}/{timestamp2}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeatureBranch>> getFeatureBranchesWithinFirstCommitTimeFrame(
        @PathVariable Long timestamp1, @PathVariable Long timestamp2) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(featureBranchService.getFeatureBranchByFirstCommitTimeFrame(timestamp1, timestamp2));
    }

    @RequestMapping(value = "/featurebranch/deploy/{timestamp1}/{timestamp2}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeatureBranch>> getFeatureBranchesWithinDeployTimeFrame(
        @PathVariable Long timestamp1, @PathVariable Long timestamp2) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(featureBranchService.getFeatureBranchByDeployTimeFrame(timestamp1, timestamp2));
    }
}

