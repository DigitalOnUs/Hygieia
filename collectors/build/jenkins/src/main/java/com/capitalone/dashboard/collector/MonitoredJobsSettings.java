package com.capitalone.dashboard.collector;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Temporary Bean to hold settings specific to the Hudson collector.
 */
@Component
@ConfigurationProperties(prefix = "jenkins")
public class MonitoredJobsSettings {

    private Set monitoredJobsSet;

    public MonitoredJobsSettings() {
        this.monitoredJobsSet = Sets.newHashSet(
            "ExampleWorkspace/ExampleProject/Create_Environment",
            "ExampleWorkspace/ExampleProject/Reference_Application_Build",
            "ExampleWorkspace/ExampleProject/Reference_Application_Code_Analysis",
            "ExampleWorkspace/ExampleProject/Reference_Application_Deploy",
            "ExampleWorkspace/ExampleProject/Reference_Application_Performance_Tests",
            "ExampleWorkspace/ExampleProject/Reference_Application_Regression_Tests",
            "ExampleWorkspace/ExampleProject/Reference_Application_Unit_Tests",
            "ExampleWorkspace/Project_Management/Generate_Project"
            );
    }

    public Set getMonitoredJobsSet() {
        return monitoredJobsSet;
    }
}