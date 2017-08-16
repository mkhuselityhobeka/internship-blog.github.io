
//list.factory('AccountService',['$http' ,'$q', function($http,$q) {
//	return{
//		findAllAccounts : function(){
//			return  $http.get('http://localhost:8080/basic-web-app/rest/accounts')
//			       .then(function(response){
//			    	 return response.data;  
//			    	   
//			       }),
//			       function(errResponse){
//					console.error('Error while fetching Accounts');
//					return $q.reject(errResponse);
//					}
//				
//			}
//		
//		
//	}
//	
//}]);