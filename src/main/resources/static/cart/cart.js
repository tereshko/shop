angular.module('app').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8989/api/v1';

    $scope.showCart = function () {
        $http.get(contextPath + "/cart")
            .then(function (response) {
                $scope.CartList = response.data.cart;
            });
    }

    $scope.addToCart = function (id) {
        $http.get(contextPath + "/cart/add/" + parseInt(id)).then(function (reloadPage) {
            $scope.showCart();
        })
    };

    $scope.clearCart = function () {
        $http.get(contextPath + "/cart/clear").then(function (reloadPage) {
            $scope.showCart();
        })
    };

    $scope.createOrder = function () {
        $http.post(contextPath + '/orders/create', $scope.address)
            .then(function (response) {
                $scope.addressForm.$setPristine();
                $scope.address = null;
            });
    }

    $scope.goToOrderSubmit = function () {
        $location.path('/order_confirmation');
    }

    $scope.incrementItem = function (id) {
        $http.get(contextPath + "/cart/increment/" + parseInt(id)).then(function (reloadPage) {
            $scope.showCart();
        })
    };

    $scope.decrementItem = function (id) {
        $http.get(contextPath + "/cart/decrement/" + parseInt(id)).then(function (reloadPage) {
            $scope.showCart();
        })
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            return true;
        } else {
            return false;
        }
    };


    $scope.showCart();

});