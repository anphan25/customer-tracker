<%@page import="com.luv2code.springdemo.util.SortUtils"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.luv2code.springdemo.util.SortUtils" %>
<!DOCTYPE html>

<html>

<head>
<title>List Customers</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/> 
</head>
<body>
	<div id="wrapper">
		<div id="header">
		<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
		<input type="button" value="Add Customer"
			onclick="window.location.href='showFormForAdd'; return false;"
			class="add-button"
		/>
		
			<form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" value="${params.theSearchName}"/>
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>
		
			<table>
				<tr>
					<c:url var="sortLinkFirstName" value="/customer/list">
						<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
					</c:url>
					<c:url var="sortLinkLastName" value="/customer/list">
						<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
					</c:url>					
					<c:url var="sortLinkEmail" value="/customer/list">
						<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
					</c:url>
				 	<th><a href="${sortLinkFirstName}">First Name</a></th>
				 	<th><a href="${sortLinkLastName}">Last Name</a></th>
				 	<th><a href="${sortLinkEmail}">Email</a></th>
				 	<th>Action</th>
				</tr>
				<c:forEach var="tempCustomer" items="${customers}">
				<tr>
					<td>${tempCustomer.firstName}</td>
					<td>${tempCustomer.lastName}</td>
					<td>${tempCustomer.email}</td>
					<td>
						<a href="/web-customer-tracker/customer/showFormForUpdate?customerId=${tempCustomer.id}">Update</a>
						|
						<a href="/web-customer-tracker/customer/delete?customerId=${tempCustomer.id}"
							onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false"
						>Delete</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>