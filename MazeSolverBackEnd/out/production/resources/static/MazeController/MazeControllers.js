'use strict';

angular.module('MazeControllers', ['ngRoute'])
    .controller('MazeCtrl', ['$scope', '$http',
        function($scope, $http) {
            $scope.rows = 0;
            $scope.columns = 0;
            $scope.textAreaText = '';
            $scope.mazeText = '';
            $scope.maze = [];
            $scope.solveOrRefresh = 'Solve';
            $scope.getArrayOfSize = function(x){
                return new Array(x);
            };

            $scope.textChanged = function(){
                $scope.populateMazeFromText($scope.textAreaText);
            };

            $scope.populateMazeFromText = function(text){
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
                $scope.maze = [];
                if($scope.solveOrRefresh == 'Solve'){
                    $http({
                        method : "POST",
                        url : "http://localhost:8080/solve",
                        headers: {
                            'Content-Type': 'text/plain'
                        },
                        data : $scope.textAreaText,
                        transformResponse: [function (data) {
                            return data;
                        }]                    
                    }).then(function mySuccess(response) {
                        $scope.populateMazeFromText(response.data);
                    }, function myError(response) {
                        $scope.maze = response.statusText;
                    });
                    $scope.solveOrRefresh = 'Refresh'
                }
                else{
                    location.reload();
                }
            }
    }]);