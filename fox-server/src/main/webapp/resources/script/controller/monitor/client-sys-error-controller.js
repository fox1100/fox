/**
 * Created by muyan on 15/5/25.
 */
spider.controller("ClientSysErrorController", function ($scope, $location, $q, DataLoadService) {
    $scope.$on(
        "$routeUpdate",
        function () {
            $scope.render();
        }
    );
    $scope.render = function () {
        var url = "rest/monitor/exceptions/system";
        if ($location.search()['time'] != null && typeof $location.search()['time'] != 'undefined') {
            url = url + "?time=" + $location.search()['time'];
        }
        $scope.total = 0;
        $q.when(DataLoadService.get(url)).then(function (response) {
            $scope.exceptions = response.data;
            for (var i = 0; i < $scope.exceptions.length; i++) {
                $scope.total = $scope.total + $scope.exceptions[i].count;
            }
        });
        $scope.renderClient();
    };
    $scope.showException = function(exception) {
        $scope.exceptionDetail = '';
        $q.when(DataLoadService.get("rest/monitor/exceptions/detail?errorMsg=" + exception.errorMsg+"&time="+exception.time)).then(function (response) {
            $scope.exceptionDetail = response.data.errorDetail;
        });
    };
    $scope.renderClient = function (msg) {
        var url = "rest/monitor/exceptions/client";
        var param = "";
        if ($location.search()['time'] != null && typeof $location.search()['time'] != 'undefined') {
            param = param + "time=" + $location.search()['time'];
        }
        if (msg != null) {
            param = param + "&errorMsg=" + msg;
        }
        if (param != '') {
            url = url + "?" + param;
        }
        $q.when(DataLoadService.get(url)).then(function (response) {
            $scope.clients = response.data;
            $scope.series = [];
            for (var i = 0; i < $scope.clients.length; i++) {
                $scope.series.push({name: $scope.clients[i].clientIp, y: $scope.clients[i].count, drilldown: true});
            }
            if ($scope.chart.series != null) {
                $scope.chart.series[0].remove(false);
            }
            $scope.chart.setTitle(null, {text: msg}, false);
            $scope.chart.addSeries({
                name: 'client',
                type: 'pie',
                data: $scope.series
            });
            $scope.chart.redraw(true);
        });

    };
    $scope.search = function () {
        if ($scope.date != null) {
            $location.search({time: $scope.date});
        }
    };
    $scope.calRate = function (a, b) {
        return ((a / b) * 100).toFixed(2);
    };
    $scope.init = function () {
        $scope.render();

        $scope.chart = new Highcharts.Chart({
            chart: {
                renderTo: 'chart',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                events: {
                    drilldown: function (e) {
                        if (!e.seriesOptions) {
                            var chart = this;
                            chart.showLoading('Load data ...');
                            var url = "rest/monitor/exceptions/client/"+ e.point.name;
                            if ($location.search()['time'] != null && typeof $location.search()['time'] != 'undefined') {
                                url = url+ "?time=" + $location.search()['time'];
                            }
                            $q.when(DataLoadService.get(url)).then(function (response) {
                                var series = [];
                                for (var i = 0; i < response.data.length; i++) {
                                    //series.push([response.data[i].errorMsg,response.data[i].count]);
                                    series.push({name: response.data[i].errorMsg, y: response.data[i].count});
                                }
                                chart.hideLoading();
                                chart.addSeriesAsDrilldown(e.point, {name: e.point.name,type:'pie',data:series});
                            });
                        }
                    }
                }
            },
            credits: {
                enabled: false
            },
            title: {
                text: '异常在所有客户端上的分布'
            },
            tooltip: {
                formatter: function() {
                    return '<span style="color:{point.color};font-weight: bold">'+ this.point.name +'</span>: '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+ Highcharts.numberFormat(this.y, 0, ',') +')';
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },

            series: [{
                name: 'client',
                type: 'pie'
            }],
            drilldown: {
                series: []
            }
        });
    };

    $scope.init();
});
