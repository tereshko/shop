angular.module('app').controller('productsController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/api/v1';

    $scope.page = 0;

    $scope.filltable = function (page) {
        $http.get(contextPath + '/products?page=' + page)
            .then(function (response) {
                $scope.ProductList = response.data.products;
                $scope.TotalPages = response.data.totalPages;
                $scope.CurrentPage = response.data.currentPage;
            });
    };

    $scope.addToCart = function (id) {
        $http.get(contextPath + "/cart/add/" + parseInt(id)).then(function (reloadPage) {
        })
    };

    $scope.clearCart = function () {
        $http.get(contextPath + "/cart/clear").then(function (reloadPage) {
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

    $scope.filltable($scope.page);
});