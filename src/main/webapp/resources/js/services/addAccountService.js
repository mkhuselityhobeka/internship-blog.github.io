'use strict';
add.factory('AccountService',['$http','$q', function($http,$p,$scope){
	data :{
		
	}
	return{
	
	
		addAccount : function(){
			return $http.post('http://localhost:8080/basic-web-app/rest/accounts')
			.then(function(response){
				 return response.data;

			}),
			
			  function(errResponse){
				console.error('Error while fetching Accounts');
				return $q.reject(errResponse);
				}
			}
		}
}]);