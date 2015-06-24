/**
 * Define all spider portal angular module
 * Created by jie.huang on 2015/5/13.
 */

var fox = angular.module("Fox", ["ngRoute", "DataLoad", "ui.ace", "ui.tree", "validation", "validation.rule"]);

/**
 * define all uri route rules
 */
fox.config(["$routeProvider",
    function ($routeProvider) {
        $routeProvider.when(
            "/monitor/system", {
                templateUrl: 'views/monitor/system.html',
                reloadOnSearch: false
            }
        ).when(
            "/config/job", {
                templateUrl: 'views/config/job.html',
                reloadOnSearch: false
            }
        ).when(
            "/config/job/:jobId", {
                templateUrl: 'views/config/job_detail.html',
                reloadOnSearch: false
            }
        ).when(
            "/config/script", {
                templateUrl: 'views/config/script.html',
                reloadOnSearch: false
            }
        ).when(
            "/monitor/task", {
                templateUrl: 'views/monitor/task_stats.html',
                reloadOnSearch: false
            }
        ).when(
            "/monitor/client_sys_error", {
                templateUrl: 'views/monitor/client_sys_error.html',
                reloadOnSearch: false
            }
        ).when(
            "/monitor/message", {
                templateUrl: 'views/monitor/message.html',
                reloadOnSearch: false
            }
        ).when(
            "/version", {
                templateUrl: 'views/version.html',
                reloadOnSearch: false
            }
        ).when(
            "/help", {
                templateUrl: 'views/help.html',
                reloadOnSearch: false
            }
        ).when(
            "/about", {
                templateUrl: 'views/about.html',
                reloadOnSearch: false
            }
        );
    }]);
