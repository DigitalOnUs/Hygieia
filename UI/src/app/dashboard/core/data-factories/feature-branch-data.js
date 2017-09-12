/**
 * Gets FeatureBranch related data
 */
(function () {
    'use strict';

    angular
        .module(HygieiaConfig.module + '.core')
        .factory('featureBranchData', featureBranchData);

    function featureBranchData($http) {

        var filter = '?filter=';
        var start_time = '&start_time=';
        var end_time = '&end_time=';


        var testDetailRoute = 'test-data/feature_branch_detail.json';
        var featureBranchDetailRoute = '/api/feature_branches';

        return {
            details: details
        };

        // search for current builds
        function details(filter_param, start_time_param, end_time_param) {
            return $http.get(featureBranchDetailRoute + filter + filter_param + start_time + start_time_param + end_time + end_time_param)
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();