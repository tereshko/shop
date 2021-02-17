angular.module('app', ['ngStorage']).controller('indexPageController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/api/v1'

    $scope.page = 0;
    $scope.authorized = false;
    $scope.registration = false;

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

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token.token;
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.authorized = true;
                    $scope.last_name = response.data.user.last_name;
                    $scope.first_name = response.data.user.first_name;
                    $scope.filltable($scope.page);
                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    };

    $scope.showMyOrders = function () {
        $http({
            url: contextPath + '/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.MyOrders = response.data;
        });
    };

    $scope.createOrder = function () {
        $http.post(contextPath + '/orders/create', $scope.address)
            .then(function (response) {
                $scope.addressForm.$setPristine();
                $scope.address = null;
                $scope.showMyOrders();
                $scope.fillCart();
            });
    }

    $scope.registrationForm = function () {
        $scope.registration = true;
    }
    $scope.authorizedForm = function () {
        $scope.registration = false;
    }

    $scope.tryToRegisterUser = function () {
        $http.post(contextPath + '/registration', $scope.user)
            .then(function successCallback(response) {
                // if (response.data.token.token) {
                //     $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token.token;
                //     $scope.user.username = null;
                //     $scope.user.password = null;
                //     $scope.authorized = true;
                //     $scope.last_name = response.data.user.last_name;
                //     $scope.first_name = response.data.user.first_name;
                //     console.log($scope.last_name);
                //     console.log($scope.first_name);
                //     $scope.filltable($scope.page);
                // }
                $scope.authorized = false;
                $scope.registration = false;

            }, function errorCallback(response) {
                window.alert("Error");
            });
    };



    // $scope.fillCart();
    // $scope.filltable($scope.page);

});
