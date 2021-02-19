angular.module('app').controller('orderResultController', function ($scope, $http, $location, $routeParams) {
    const contextPath = 'http://localhost:8989/api/v1';

    $scope.showOrder = function () {
        $http({
            url: contextPath + '/orders/' + $routeParams.orderId,
            method: 'GET'
        }).then(function (response) {
            $scope.OrderInfo = response.data;
        });
    };

    $scope.showOrder();
});