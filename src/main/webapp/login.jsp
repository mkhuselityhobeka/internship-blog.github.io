<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</style>

</head>
<form:form ng-controller="loginController">
 
<div class="form-group col-sm-10">
      <label for="username" class="sr-onlyl"> Username</label>   
            <input type="text" ng-model="loginForm.email" class="form-control" required"/>
            <span ng-messages=""></span>
  </div>
 
<div class="form-group col-sm-10">
      <label for="password" class="sr-onlyl">Password</label>   
            <input type="text"  ng-model="loginForm.password" class="form-control" required/>
  </div>
<br><br><br><br><br><br><br><br>
<button  id="SubmitButton" type="submit" ng-click="Login()">Login here</button><br><a  href="#!/register">please register  here</a><br><a  href="#!/ForgotPassword">Forgot password</a> 


</form:form>
</body>
</html>