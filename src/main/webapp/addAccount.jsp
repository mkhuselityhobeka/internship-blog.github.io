<html>
<head>
<style>
#SubmitButton {
 	background-color: white; 
    border: none;
    color: black;
    padding: 16px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s;
    transition-duration: 0.4s;
    cursor: pointer;
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
   }

#SubmitButton:hover {
    background-color: #4CAF50;
    color: white;
}

body {
   background-color:white;
   opacity:0.6;
}
</style>
</head>
<body>
<form class="form-horizontal"ng-controller="addController" novalidate name="accountForm">
  
<div class="form-group col-sm-10" >
      <label for="name" class="sr-onlyl">Name</label>   
            <input type="text" ng-model="accForm.name" class="form-control"/>
  </div>
  
 <div class="form-group col-sm-10" ng-class="{'has-error': accountForm.email.$invalid && accountForm.email.$dirty ,'has-success': accountForm.email.$valid}">
      <label for="email" class="sr-onlyl"> username</label>   
            <input type="email"  ng-model="accForm.email" class="form-control" name="email" ng-model="accForm.email" required />         
            <span class="help-block" ng-if="accountForm.email.$invalid && accountForm.email.$dirty">Please enter a valid emailaddress eg mkhuselityhobeka@gmail.com</span>
  </div> 
  
<div class="form-group col-sm-10"  ng-class="{'has-error': accountForm.password.$invalid && accountForm.password.$dirty ,'has-success': accountForm.password.$valid}" >
		 
      <label for="password" class="sr-onlyl">Password</label>   
            <input type="password" name="password" ng-model="accForm.password" class="form-control" required ng-maxlenth="8" ng-minlength="5"  />
                 <p class="help-block" ng-if="accountForm.password.$invalid && accountForm.password.$dirty">password must have a maximun length of 8 chaacters and minimum length of 5 characters</p>           
</div>
 
  
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
      <button  id="SubmitButton" class="btn btn-default" type="submit" ng-disabled="accountForm.email.$invalid || accountForm.password.$invalid" ng-click="AddAccount()">Add Account</button>
      
      </form>

</body>
</html>