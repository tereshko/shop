angular.module('app', []).controller('indexPageController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/api/v1'

    $scope.page = 0;

    $scope.filltable = function (page) {
        $http.get(contextPath + '/products?page=' + page)
            .then(function (response) {
                $scope.ProductList = response.data.products;
                $scope.TotalPages = response.data.totalPages;
                $scope.CurrentPage = response.data.currentPage;
                console.log(response);
            });
    };


    $scope.removeProductById = function (id) {
        $http.delete(contextPath + parseInt(id)).then(function (reloadPage) {
                $scope.filltable($scope.page);
            }
        )
    };

    $scope.fillCart = function () {
        $http.get(contextPath + "/cart")
            .then(function (response) {
                $scope.CartList = response.data.cart;
                console.log(response);
            });
    }

    $scope.addToCart = function (id) {
        $http.get(contextPath + "/cart/add/" + parseInt(id)).then(function (reloadPage) {
            $scope.fillCart();
        })
    };

    $scope.clearCart = function () {
        $http.get(contextPath + "/cart/clear").then(function (reloadPage) {
            $scope.fillCart();
        })
    };

    $scope.incrementItem = function (id) {
        $http.get(contextPath + "/cart/increment/" + parseInt(id)).then(function (reloadPage) {
            $scope.fillCart();
        })
    };

    $scope.decrementItem = function (id) {
        $http.get(contextPath + "/cart/decrement/" + parseInt(id)).then(function (reloadPage) {
            $scope.fillCart();
        })
    };

    $scope.fillCart();
    $scope.filltable($scope.page);

});
