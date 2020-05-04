<%@page import="com.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/admin.js"></script>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-6">
		<h1>Admin Management V10.1</h1>
		
		<form id="formAdmin" name="formAdmin">
 			Admin Username: 
 			<input id="adminUsername" name="adminUsername" type="text"
				 class="form-control form-control-sm">
				 
 		<br> Admin Password:
 		<input id="adminPassword" name="adminPassword" type="text"
 			class="form-control form-control-sm">
 		
 		<br> Admin Reports:
 		<input id="adminReports" name="adminReports" type="text"
 			class="form-control form-control-sm">
 			

	    <br>
 		<input id="btnSave" name="btnSave" type="button" value="Save"
 			class="btn btn-primary">
 		<input type="hidden" id="hidAdminIDSave"
 			name="hidAdminIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br> 
<div id="divAdminGrid">
	 <%
 		Admin adminObj = new Admin();
 		out.print(adminObj.readAdmin());
	 %>
	 
</div>

			</div>
		 </div>
	</div>

</body>
</html>