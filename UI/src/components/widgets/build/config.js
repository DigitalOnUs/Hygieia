/**
 * Build widget configuration
 */
(function () {
    'use strict';
    angular
        .module(HygieiaConfig.module)
        .controller('BuildWidgetConfigController', BuildWidgetConfigController);
    BuildWidgetConfigController.$inject = ['modalData', '$scope', 'collectorData', '$uibModalInstance'];
    function BuildWidgetConfigController(modalData, $scope, collectorData, $uibModalInstance) {
        var ctrl = this,
        widgetConfig = modalData.widgetConfig;
        
        // $scope.timeFrames = [7,14,30];
        $scope.timeFrames = [
            {value : 7 , name : "Last 7 Days"},
            {value : 10 , name : "Last 10 Days"},
            {value : 14 , name : "Last 14 Days"},
            {value : 30 , name : "Last 30 Days"},
        ]
        // $scope.filterOptions = ["deploy" , "first commit", "Both"];

        $scope.filterOptions = [
            {value : "deploy" , name : "Deploy"},
            {value : "first_commit" , name : "First Commit"},
            {value : "none" , name : "Both"}
        ]

        $scope.buildtimeFrames = [
            {value : 7 , name : "Last 7 Days"},
            {value : 10 , name : "Last 10 Days"},
            {value : 14 , name : "Last 14 Days"},
            {value : 30 , name : "Last 30 Days"},
        ]

        // public variables
        ctrl.buildDurationThreshold = 3;
        ctrl.buildConsecutiveFailureThreshold = 5;
    
        ctrl.end_time = moment().unix()*1000; 
        var myStart = moment().subtract(7, 'Days').unix()*1000;
        // ctrl.start_time = myStart; 
        // ctrl.filter = "deploy";       

        $scope.getJobs = function (filter) {
        	return collectorData.itemsByType('build', {"search": filter, "size": 20}).then(function (response){
        		return response;
        	});
        }
        
        loadSavedBuildJob();
        // set values from config
        if (widgetConfig) {
            if (widgetConfig.options.buildDurationThreshold) {
                ctrl.buildDurationThreshold = widgetConfig.options.buildDurationThreshold;
            }
            if (widgetConfig.options.consecutiveFailureThreshold) {
                ctrl.buildConsecutiveFailureThreshold = widgetConfig.options.consecutiveFailureThreshold;
            }
            // if (widgetConfig.start_time) {
            //     ctrl.start_time = widgetConfig.start_time;
            // }
            if (widgetConfig.end_time) {
                ctrl.end_time = widgetConfig.end_time;
            }
        }
        // public methods
        ctrl.submit = submitForm;

        // method implementations
        function loadSavedBuildJob(){
        	var buildCollector = modalData.dashboard.application.components[0].collectorItems.Build,
            savedCollectorBuildJob = buildCollector ? buildCollector[0].description : null;
            if(savedCollectorBuildJob) { 
            	$scope.getJobs(savedCollectorBuildJob).then(getBuildsCallback) 
            }
        }
        
        function getBuildsCallback(data) {
            ctrl.collectorItemId = data[0];
        }

        function submitForm(valid, collector) {
            if (valid) {
                var form = document.buildConfigForm;
                var postObj = {
                    name: 'build',
                    options: {
                        id: widgetConfig.options.id,
                        buildDurationThreshold: parseFloat(form.buildDurationThreshold.value),
                        consecutiveFailureThreshold: parseFloat(form.buildConsecutiveFailureThreshold.value),
                        // build_period: parseInt(form.BuildTimePeriod.value),
                        start_time: moment().subtract(parseInt(form.TimePeriod.value), 'Days').unix()*1000,
                        filter: form.Filter.value,
                        end_time : moment().unix()*1000,
                    },
                    componentId: modalData.dashboard.application.components[0].id,
                    collectorItemId: collector.id,
                };
                // pass this new config to the modal closing so it's saved
                $scope.dummy = form.timePeriod;
                $uibModalInstance.close(postObj);
            }
        }
    }
})();
