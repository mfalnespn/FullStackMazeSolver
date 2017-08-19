'use strict';

// Declare app level module which depends on views, and components
angular.module('mazeApp', ['ngRoute','MazeControllers'])
    .config(['$locationProvider', '$routeProvider', function($locationProvider) {
    $locationProvider.hashPrefix('!');
}]);
