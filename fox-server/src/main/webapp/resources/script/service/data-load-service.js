/**
 * Created by huang_jie on 3/3/15.
 */
angular.module("DataLoad", [])
    .factory("DataLoadService", ['$http',
        function ($http) {
            return {


            get: function (url) {
                    return $http({method: 'GET', url: REST_API + url,
                        //headers: {
                        //    'Content-Type':'text/plain'
                        //}
                        transformResponse: appendTransform($http.defaults.transformResponse, function(value) {
                            console.log("FFFFFFFFFFFFFFFFFFFFFFFFFFFF")
                            return value;
                        })
                    });
                },
                get:function(url,params){
                    return $http({
                        method: 'GET',
                        url: REST_API + url,
                        params: params
                    });
                },
                put: function (url, params) {
                    return $http({
                        method: 'PUT',
                        url: REST_API + url,
                        data: params,
                        contentType: "application/json; charset=utf-8"
                    });
                },
                post: function (url, params) {
                    return $http({
                        method: 'POST',
                        url: REST_API + url,
                        data: params,
                        contentType: "application/json; charset=utf-8"
                    });
                },
                del: function (url) {
                    return $http({
                        method: 'DELETE',
                        url: REST_API + url
                    });
                }
            }
        }]
);
function appendTransform(defaults, transform) {

    // We can't guarantee that the default transformation is an array
    defaults = angular.isArray(defaults) ? defaults : [defaults];

    // Append the new transformation to the defaults
    return defaults.concat(transform);
}