/**
 * Created by Administrator on 2015/6/10.
 */
fox.controller("MenuController", function ($scope, $location) {
    $scope.$on(
        "$routeChangeSuccess",
        function () {
            $scope.path = $location.path();
        }
    );
    $scope.init = function () {
        $scope.menus = menus;
        $scope.path = $location.path();
    };
    $scope.init();
});