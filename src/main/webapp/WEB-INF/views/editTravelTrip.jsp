<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
        .simpletablestyle,  th, td {
            border: 1px solid black; padding:15px;
        }
        
        .simpleformstyle,  th, td {
            border: 0px;
        }
</style>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Travel Trips</title>
</head>
<body>
<br/>
<form:form commandName="editTravelTrip" id="reg" action="editTravelTrip.html">
	<h2>Edit Travel Trip</h2>
    <p>Enforces annotation-based constraints defined on the model class.</p>
    <table class="simpleformstyle">
    	<tbody>
    		<tr>
    			<td><form:label path="id">ID:</form:label></td>
    			<td><form:input path="id" disabled="true"/></td>
    			<td><form:errors class="invalid" path="id"/></td>
    		</tr>
    		<tr>
    			<td><form:label path="country">Country:</form:label></td>
    			<td><form:input path="country"/></td>
    			<td><form:errors class="invalid" path="country"/></td>
    		</tr>
    		<tr>
    			<td><form:label path="city">City:</form:label></td>
    			<td><form:input path="city"/></td>
    			<td><form:errors class="invalid" path="city"/></td>
    		</tr>
    		<tr>
    			<td><form:label path="fromDate">From:</form:label></td>
    			<td><form:input path="fromDate"/></td>
    			<td><form:errors class="invalid" path="fromDate"/></td>
    		</tr>
    		<tr>
    			<td><form:label path="toDate">To:</form:label></td>
    			<td><form:input path="toDate"/></td>
    			<td><form:errors class="invalid" path="toDate"/></td>
    		</tr>
    		<tr>
    			<td><form:label path="business">Business:</form:label></td>
    			<td><form:checkbox path="business"/></td>
    			<td><form:errors class="invalid" path="business"/></td>
    		</tr>    		
    	</tbody>
    </table>
    <table>
    	<tr><td><input type="submit" value="Update" class="register"> </td></tr>    	
    </table>
</form:form>
</body>
</html>