/**
 * Created by jie.huang on 2015/5/20.
 */
spider.controller("ScriptController", function ($scope, $location, $q, DataLoadService) {
    $scope.$on(
        "$routeUpdate",
        function () {
            $scope.render();
        }
    );
    $scope.render = function () {
        $q.when(DataLoadService.get("rest/config/scripts")).then(function (response) {
            $scope.scripts = response.data;
        });
    };
    $scope.showScript = function (script) {
        $scope.script = script;
    };
    $scope.newScript = function () {
        $scope.script = {};
    };
    $scope.showExample = function (script) {
        $q.when(DataLoadService.get("rest/config/scripts/" + script.id + "/example")).then(function (response) {
            console.log("FFF");
            console.log(response);
            $scope.example = response.data;
            console.log($scope.example);
            $scope.currentScript = script;
        });
    };
    $scope.saveExample = function () {
        $q.when(DataLoadService.put("rest/config/scripts/" + $scope.currentScript.id + "/example", $scope.example)).then(function (response) {
        });
    };
    $scope.saveScript = function () {
        if ($scope.script != null && $scope.script.id > 0) {
            $q.when(DataLoadService.put("rest/config/scripts", $scope.script)).then(function (response) {
            });
        } else {
            $q.when(DataLoadService.post("rest/config/scripts", $scope.script)).then(function (response) {
                $scope.script.id = response.data;
            });
        }
    };
    $scope.getType = function (code) {
        return $scope.type[code]
    };
    $scope.getParamType = function (code) {
        return $scope.paramType[code]
    };
    $scope.deleteScript = function (script) {
        $q.when(DataLoadService.del("rest/config/scripts/" + script.id)).then(function (response) {
            for (var i = 0; i < $scope.scripts.length; i++) {
                if ($scope.scripts[i].id == script.id) {
                    $scope.scripts.splice(i, 1);
                    return;
                }
            }
        });
    };
    $scope.init = function () {
        $scope.render();
        $scope.aceOption = {
            mode: "groovy",
            disableSearch: true,
            theme: 'idle_fingers'
        };
        $scope.paramType = paramType;
        $scope.type = scriptType;
    };

    $scope.init();
});
