'use strict';

angular.module('MazeControllers', ['ngRoute'])
    .controller('MazeCtrl', ['$scope', '$http',
        function($scope, $http) {
            $scope.rows = 0;
            $scope.columns = 0;
            $scope.mazeText = '';
            $scope.maze = [];
            $scope.blah = null;
            $scope.steps = 0;
            $scope.mazeSolved = false;
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
                if($scope.mazeText && !$scope.mazeSolved) {
                    $http({
                        method: "POST",
                        url: "http://localhost:8080/solve",
                        headers: {
                            'Content-Type': 'text/plain'
                        },
                        data: $scope.mazeText
                    }).then(function mySuccess(response) {
                        $scope.mazeSolved = true;
                        $scope.steps = response.data.steps;
                        $scope.populateMazeFromText(response.data.solvedMaze);
                    }, function myError(response) {
                        $scope.maze = response.statusText;
                    });
                }
                else if (!$scope.mazeText){
                    window.alert("Please upload a maze for solving.")
                }
            };

            $scope.parseFile = function(event){
                var f = event.target.files[0],
                    r = new FileReader();

                r.onloadend = function(e) {
                    $scope.mazeText = e.target.result;
                    $scope.steps = 0;
                    $scope.mazeSolved = false;
                    $scope.populateMazeFromText($scope.mazeText);
                    $scope.$apply();
                }

                r.readAsText(f);
            };
        }
    ])
    .directive('customOnChange', function() {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var onChangeHandler = scope.$eval(attrs.customOnChange);
                element.bind('change', onChangeHandler);
            }
        };
    });