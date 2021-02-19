angular.module('app').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/api/v1';

    $scope.showMyOrders = function () {
        $http({
            url: contextPath + '/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.MyOrders = response.data;
        });
    };

    $scope.showMyOrders();
});