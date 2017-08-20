'use strict';

angular.module('MazeControllers', ['ngRoute'])
    .controller('MazeCtrl', ['$scope', '$http',
        function($scope, $http) {
            $scope.rows = 0;
            $scope.columns = 0;
            $scope.mazeText = '';
            $scope.maze = [];
            $scope.blah = null;
            $scope.getArrayOfSize = function(x){
                return new Array(x);
            };

            $scope.populateMazeFromText = function(text){
                $scope.maze = [];
                var mazeRows = text.split('\n');
                for(var i = 0; i < mazeRows.length; i++){
                    var cells = [];
                    for(var j = 0; j < mazeRows[i].length; j++){
                        cells.push(mazeRows[i].charAt(j))
                    }
                    $scope.maze.push(cells);
                }
            };

            $scope.solve = function(){
                if($scope.mazeText != '') {
                    $http({
                        method: "POST",
                        url: "http://localhost:8080/solve",
                        headers: {
                            'Content-Type': 'text/plain'
                        },
                        data: $scope.mazeText,
                        transformResponse: [function (data) {
                            return data;
                        }]
                    }).then(function mySuccess(response) {
                        $scope.populateMazeFromText(response.data);
                    }, function myError(response) {
                        $scope.maze = response.statusText;
                    });
                }
                else{
                    $scope.alert("Please upload a maze for solving.")
                }
            };

            $scope.parseFile = function(){
                var f = event.target.files[0],
                    r = new FileReader();

                r.onloadend = function(e) {
                    $scope.mazeText = e.target.result;
                    $scope.populateMazeFromText($scope.mazeText);
                    $scope.$apply();
                    //send your binary data via $http or $resource or do anything else with it
                }

                r.readAsText(f);
            };
        }
    ])
    .directive('dataFile', function() {
        return {
            require:"ngModel",
            restrict: 'A',
            link: function($scope, el, attrs, ngModel){
                el.bind('change', function(event){
                    var files = event.target.files;
                    var file = files[0];

                    ngModel.$setViewValue(file);
                    $scope.$apply();
                });
            }
        };
    });
