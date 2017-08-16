<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myapp">
<head>

<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-resource.js"></script>



<title>JumpCo Labs Blog</title>
 <style>
.topnav {
	overflow : hidden;
	background-color :rgb(192,192,192);
	opacity:0.6;
	
}

.topnav a{
	float:left;
	display:block;
	padding:14px 16px;
	font-size:17px;
    text-decoration: none;
}

.topnav a:hover {
   background-color:white;
   opacity:0.6;	
}
</style>  
</head>
<body>
    <div class="topnav">     
           <ul>
             <li>            
               <a  herf="#!/home">Home</a>
               <a  herf="#!/AboutUs">About Us</a>            
               <a  href="#!/addAccounts">Add Account</a>
               <a  href="#!/addBlog">Add Blog</a>                                                 
               <a  href="#!/accountList">List of Accounts</a> 
               <a  href="#!/login">Sign here</a>                             
             </li>   
             <div class="form-group col-sm-10">
            <label for="search" class="sr-onlyl">Search</label>   
            <input type="text"  ng-model="searchText" class="form-control" placeholder="Search accounts ..."/>  
            </div>      
           </ul>                    
         </div>        
         <div ng-view></div>       
    <br><br>
</body>
  <script type="text/javascript">
     var myApp  = angular.module('myapp', ['ngRoute','ngResource']);
	    myApp.config(function($routeProvider,$locationProvider){
	    	$routeProvider      	
	      			 .when('/home',{
	        		   templateUrl: 'home.jsp',
	        		  controller : 'homeController'  	        	 
    		 						 })   
        
       				 .when('/AboutUs',{
	        			 templateUrl: 'about.jsp',
	        			 controller : 'aboutController"'  	        	 
    		 			 })   
    		 			 
    		 			 
	    	         .when('/accountList',{
	    	        	 templateUrl:  'accountList.jsp',
	    	        	 controller : 'listController'  	        	 
    	     		    })  
    	         
    	         .when('/addAccounts',{
    	        	 templateUrl:  'addAccount.jsp',
    	        	 controller : 'addController'  	        	 
	       			  })
	         .when('/addBlog',{
    	        	 templateUrl:  'addBlog.jsp',
    	        	 controller : 'addBlogController'  	        	 
	      		   })
	      		     .when('/login',{
    	        	 templateUrl:  'login.jsp',
    	        	 controller : 'loginController'  	        	 
	      		   })
	      		   .otherwise({
	      			   redirectTo :("/home")
	      		   })
    });      
	
	    
	    
	    /*
        List Controller to get list
        */
        myApp.controller('listController', function($scope, $http){
 	    $http.get('http://localhost:8080/Internship-Blog/rest/accounts')
	    .then(function(response){
	 	$scope.listAccount = response.data.accounts; 
        });
    
 	    $scope.search = function(accList){
 	    	if($scope.searchText == undefined){
 	    		return true;
 	    	}else
 	    		if(accList.name.toLowerCase().indexOf($scope.searchText).toLowerCase != -1){
 	    			return true;
 	    		}
 	    	     return false
 	    }
 	    
});  
	    
	    
            /*
        Add Account controller for add account page
        */
        myApp.controller('addController', function($scope, $http){
              	$scope.AddAccount = function(){
        		var accForm = {
            			name : name, 
            			email : "",
						password : ""
            	}
         	     $http({
         	    	 method :'POST',
         	    	 url: 'http://localhost:8080/Internship-Blog/rest/accounts',
         	    	 data: angular.toJson($scope.accForm),
                	     }).then(function(response){
         	    	 $scope.accForm = response.data;        	 
         });
     }
 });        
         
        /*
        Account service to store persistent data 
        */
        
        
        
        myApp.factory("sessionService", function($http){
        	 var session ={};
        	 session.login = function(data){
        		 return $http.post("/Internship-Blog/#!/login","username="+data.email +"&password="+data.password,{
        			 hearders: {'Content-Type': 'application/x-www-form-urlencoded'}
        		 }).then(function(data){
        			 alert("welcome");
         			localStorage.setItem("session",{});
        		 }),function(){
        			 alert("unsuccessful login");

     				  }
        		 };
          	        	
      
        session.logout = function(){ 	
 			localStorage.removeItem("session");
        	}
        
        session.isLoggedIn  = function(){
        	localStorage.getItem("session")!==null
            return session;

  				 };
        return session;
        });
         
 

myApp.factory("accountService",function($resource){
	
	  
       	var service={};
       
	    
           service.userExists = function(email,password,account,success,failure){    	
        	var Account =$resource('/Internship-Blog/rest/accounts');
        	var data =Account.get({email:account.email, password:account.password}, function(){
        		  var accounts = data.accounts;
         	     if(accounts.length!==0){
             	     success;

         	     }else{
         	    	 failure;  	   
         	     }    	
        	},
        	failure);
         };
        	return service;
      });    
    
 
/*
        log in controller , Authenticate if user has account or not 
        */
        myApp.controller('loginController',function($scope,sessionService,accountService,$location){
      	  $scope.Login = function(){
      				accountService.userExists($scope.account ,function(account){
          			sessionService.login($scope.account).then(function(){
              			$location.path("/home.jsp");

          			   });

      				},
      				function(){
      					alert("Error Loggin in user");
      				});
      		  };
               	  
        }) ;   
        
        
        
        
        
        
        
              
        myApp.controller('homeController', function($scope){
            	$scope.message = "JumpCo labs";
        	
        });
        myApp.controller('aboutController', function($scope){
        	$scope.message = "About JumpCo labs";
    	
    });
        
        
    
       
        
</script>  

</html>