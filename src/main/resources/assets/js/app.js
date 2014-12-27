var app = angular.module('myApp', []);

app.controller('MyController', function($scope) {
  $scope.person = { name: "Testing" };

  var updateClock = function() {
    $scope.clock = new Date();
  };

  var timer = setInterval(function() {
    $scope.$apply(updateClock);
  }, 1000);
  updateClock();

});

app.controller('HelloController', function($scope, $http){
    $scope.greeting = "";

    $scope.helloWorld = function(){
        var param = "";
        if($scope.name !== undefined){
            param = "?name="+$scope.name;
        }else{

        }
        $http({
             url: '/api/hello-world'+param
           }).success(function(data, status, headers, config) {
              $scope.greeting = data.content;
           }).error(function(data, status, headers, config) {
              // Do nothing
           });
    }

});


