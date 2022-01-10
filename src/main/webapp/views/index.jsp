<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var ="css" value="/assets/css"/>
<spring:url var ="js" value="/assets/js"/>
<spring:url var ="images" value="/assets/images"/>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping- ${title}</title>

<script> 
window.menu ='${title}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap DataTables -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Add custom CSS here -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->
		<%@include file="/views/shared/navbar.jsp"%>


		<!-- Page content -->
		<div class="content">
			<!-- Loading the home content -->
			<c:if test="${userClickHome == true }">
				<%@include file="/views/home.jsp"%>
			</c:if>

			<!-- Load only when user click about -->
			<c:if test="${userClickAbout == true }">
				<%@include file="/views/about.jsp"%>
			</c:if>

			<!-- Load only when user click contact -->
			<c:if test="${userClickContact == true }">
				<%@include file="/views/contact.jsp"%>
			</c:if>
			
			<!-- Load only when user click all products or category products -->
			<c:if test="${userClickAllProducts == true or userClickCategoryProducts == true }">
				<%@include file="/views/listProducts.jsp"%>
			</c:if>
			
				
			<!-- Load only when user click show product -->
			<c:if test="${userClickShowProduct==true}">
				<%@include file="/views/singleProduct.jsp"%>
			</c:if>
			
			<!-- Load only when user clicks manage product -->
			<c:if test="${userClickManageProducts == true}">
				<%@include file="/views/manageProducts.jsp"%>
			</c:if>	

		</div>

		<!-- Footer comes here -->
		<%@include file="/views/shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>
		
			<!-- jQuery validator-->
		<script src="${js}/jquery.validate.js"></script>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>
		
		<!-- DataTable Plugin -->
		<script src="${js}/jquery.dataTables.js"></script>
		
		<!-- DataTable Bootstrap Script -->
		<script src="${js}/dataTables.bootstrap.js"></script>
		
			<!-- Bootbox -->
		<script src="${js}/bootbox.min.js"></script>

		<!-- self coded javascript -->
		<script src="${js}/myapp.js"></script>
	</div>

</body>

</html>
