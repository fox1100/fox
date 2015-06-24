spider.controller("JobController", function ($scope, $location, $q, DataLoadService) {
    $scope.$on(
        "$routeUpdate",
        function () {
            $scope.render();
        }
    );
    $scope.render = function () {
        $scope.condition = $location.search();
        $scope.renderJob();
    };
    $scope.renderTargets = function () {
        $q.when(DataLoadService.get("rest/config/targets")).then(function (response) {
            $scope.targets = response.data;
            if($scope.targets!=null && $scope.targets.length>0) {
                for(var i=0;i<$scope.targets.length;i++) {
                    if($scope.targets[i].id==$scope.condition.targetId) {
                        $scope._target = $scope.targets[i];
                        break;
                    }
                }
            }
        });
    };
    $scope.renderJob = function () {
        $q.when(DataLoadService.get("rest/config/jobs", $scope.condition)).then(function (response) {
            $scope.jobs = response.data;
        });
    };
    $scope.search = function () {
        $location.search($scope.condition);
    };
    $scope.showJob = function (job) {
        $location.url("/config/job/" + job.id);
    };
    $scope.deleteJob = function (job) {
        var jobId = job.id;
        $q.when(DataLoadService.del("rest/config/jobs/" + jobId)).then(function (response) {
            for (var i = 0; i < $scope.jobs.length; i++) {
                if ($scope.jobs[i].id == jobId) {
                    $scope.jobs.splice(i, 1);
                    return;
                }
            }
        });
    };
    $scope.updateStatus = function (job, status) {
        $q.when(DataLoadService.put("rest/config/jobs/" + job.id + "/status?status=" + status)).then(function (response) {
            job.status = status;
        });
    };
    $scope.newJob = function () {
        $scope.currentJob = {};
        $scope.currentJob.targetId = $scope._target.id;
    };
    $scope.saveJob = function () {
        if ($scope.currentJob != null && $scope.currentJob.id > 0) {
            $q.when(DataLoadService.put("rest/config/jobs", $scope.currentJob)).then(function (response) {
            });
        } else {
            $q.when(DataLoadService.post("rest/config/jobs", $scope.currentJob)).then(function (response) {
                $scope.currentJob.id = response.data;
            });
        }
    };
    $scope.getCategory = function (code) {
        return $scope.category[code];
    };
    $scope.getStatus = function (code) {
        return $scope.jobStatus[code];
    };
    $scope.init = function () {
        $scope.condition = $location.search();
        $scope.render();
        $scope.renderTargets();
        $scope.category = category;
        $scope.jobStatus = jobStatus;
    };

    $scope.init();
});
