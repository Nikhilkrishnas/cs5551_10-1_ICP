var angularApp = angular.module('detailsApplication', []);
angularApp.controller('detailsController',function($scope,$http){
    $scope.getDetails=function(){
       $scope.imgurl= $scope.urllink;
    $http({
        method: 'GET',
        url:'http://localhost:3000/getDetails?url='+$scope.urllink
        }).then(function successCallBack(response){
        $scope.result=response.data.rawData.outputs[0].data.colors;
        //var x="";
        //for(var results in result){
          //  x=x+results.w3c.name+":"+results.w3c.hex+"\t"+results.valueOf;
        //}
      //  $scope.details=x;
        },function errorCallBack(response){
            console.log(response);
        })
    }
});