spider.controller("JobDetailController", function ($scope, $location, $q, $routeParams, DataLoadService) {
    $scope.$on(
        "$routeUpdate",
        function () {
            $scope.render();
        }
    );
    $scope.render = function () {
        $scope.jobId = $routeParams.jobId;
        $scope.renderJob();
        $scope.renderScript();
    };
    $scope.renderJob = function () {
        $q.when(DataLoadService.get("rest/config/jobs/" + $scope.jobId)).then(function (response) {
            $scope.job = response.data;
            $scope.name = response.data.name;
        });
    };
    $scope.renderScript = function () {
        $q.when(DataLoadService.get("rest/config/job_scripts?job=" + $scope.jobId)).then(function (response) {
            $scope.jobBuilders = response.data["1"];
            $scope.jobChains = response.data["2"];
        });
    };
    $scope.selectScript = function (jobChain) {
        $scope.jobScript = jobChain;
    };
    $scope.selectScriptType = function (type) {
        $scope.currentType = type;
        $scope.jobScript = {};
    };
    $scope.saveScriptOrder = function() {
        $q.when(DataLoadService.put("rest/config/job_scripts/order", $scope.jobChains)).then(function (response) {
        });
    };
    $scope.deleteJobScript = function () {
        $q.when(DataLoadService.del("rest/config/job_scripts/" + $scope.jobScript.id)).then(function (response) {
            var jobScripts;
            if ($scope.jobScript.type == 1) {
                jobScripts = $scope.jobBuilders;
            } else {
                jobScripts = $scope.jobChains;
            }
            for (var i = 0; i < jobScripts.length; i++) {
                if (jobScripts[i].id == $scope.jobScript.id) {
                    jobScripts.splice(i, 1);
                    return;
                }
            }
        });
    };
    $scope.saveJobScript = function () {
        if ($scope.jobScript != null && $scope.jobScript.id > 0) {
            $q.when(DataLoadService.put("rest/config/job_scripts", $scope.jobScript)).then(function (response) {
            });
        } else {
            $scope.jobScript.jobId = $scope.job.id;
            $scope.jobScript.type = $scope.currentType;
            $q.when(DataLoadService.post("rest/config/job_scripts", $scope.jobScript)).then(function (response) {
            });
        }
    };
    $scope.showJob = function (job) {
        $location.url("/config/job/" + job.id);
    };

    $scope.saveJob = function () {
        $q.when(DataLoadService.put("rest/config/jobs", $scope.job)).then(function (response) {
            $scope.name = $scope.job.name;
        });
    };
    $scope.selectDefineScript = function(type) {
        $scope.selectScriptType(type)
        $q.when(DataLoadService.get("rest/config/scripts?type="+type)).then(function (response) {
            $scope.defineScripts = response.data;
        });
    };
    $scope.showModalExample = function() {
        $scope.jobScript.scriptParam = "";
        for (var i = 0; i < $scope.defineScripts.length; i++) {
            if ($scope.defineScripts[i].id == $scope.jobScript.scriptId) {
                $scope.jobScript.scriptParam = $scope.defineScripts[i].example;
                return;
            }
        }
    };
    $scope.saveRule = function () {
        $scope.rule.jobId = $scope.jobId;
        if ($scope.rule != null && $scope.rule.id > 0) {
            $q.when(DataLoadService.put("rest/config/rules", $scope.rule)).then(function (response) {
            });
        } else {
            $q.when(DataLoadService.post("rest/config/rules", $scope.rule)).then(function (response) {
            });
        }
    };
    $scope.init = function () {
        $scope.render();

        $scope.aceOption = {
            mode: "groovy",
            disableSearch: true,
            theme: 'idle_fingers'
        };
        $scope.category = category;
    };

    $scope.init();
});
