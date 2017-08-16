var list  = angular.module("myapp", ['ngRoute']);
	   list.config(function($routeProvider){
	    	$routeProvider   	
	    	         .when('/accountList', {
	    	        	 templateUrl :'list/Accounts',
	    	        	 controller : 'listCRL'  	        	 
    	         })    	        
    })
    .otherwise({
    	redirectTo : "/index.jsp",
    })
    
     .controller('listCRL', function($scope, $http){
 	    $http.get('http://localhost:8080/basic-web-app/rest/accounts')
	    .then(function(response){
	 	$scope.listAccount = response.data; 
       });

});
