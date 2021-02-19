angular.module('app').controller('orderConfirmationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8989/api/v1';

    $scope.showCart = function () {
        $http.get(contextPath + "/cart")
            .then(function (response) {
                $scope.CartList = response.data.cart;
                console.log(response);
            });
    }

    $scope.submitOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            params: {
                address: $scope.order_info.address
            }
        }).then(function (response) {
            $location.path('/order_result/' + response.data.id);
        });
    }

    $scope.createOrder = function () {
        $http.post(contextPath + '/orders/create', $scope.address)
            .then(function (response) {
                $scope.addressForm.$setPristine();
                $scope.address = null;
                console.log(response);
                $location.path('/order_result/' + response.data.id);
            });
    }

    $scope.showCart();
});