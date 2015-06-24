spider.controller("TargetController", function ($scope, $location, $q, DataLoadService) {
    $scope.$on(
        "$routeUpdate",
        function () {
            $scope.render();
        }
    );
    $scope.render = function () {
        $q.when(DataLoadService.get("rest/config/targets")).then(function (response) {
            $scope.targets = response.data;
        });
    };
    $scope.showJob = function (target) {
        $location.path("/config/job");
        $location.search("targetId", target.id);
    };
    $scope.showTarget = function (target) {
        $scope.currentTarget = target;
        $scope.saveBtnDisable = false;
    };
    $scope.newTarget = function () {
        $scope.currentTarget = {};
        $scope.saveBtnDisable = false;
    };
    $scope.deleteTarget = function (target) {
        $q.when(DataLoadService.del("rest/config/targets/"+target.id)).then(function (response) {
            for (var i = 0; i < $scope.targets.length; i++) {
                if ($scope.targets[i].id == target.id) {
                    $scope.targets.splice(i, 1);
                    return;
                }
            }
        });
    };
    $scope.saveTarget = function () {
        $scope.saveBtnDisable = true;
        if ($scope.currentTarget != null && $scope.currentTarget.id > 0) {
            $q.when(DataLoadService.put("rest/config/targets", $scope.currentTarget)).then(function (response) {
                $scope.saveBtnDisable = false;
            });
        } else {
            $q.when(DataLoadService.post("rest/config/targets", $scope.currentTarget)).then(function (response) {
                $scope.currentTarget.id = response.data;
                $scope.saveBtnDisable = false;
            });
        }
    };
    $scope.init = function () {
        $scope.render();
    };

    $scope.init();
});
