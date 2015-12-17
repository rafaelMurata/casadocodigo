<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<sec:authentication property="principal" var="user"/>
<title="Casa do Cóodigo">
<body>
<h3> <a href="${pageContext.request.contextPath}/produtos?locale=pt" >Português </a> </h3>
<h3> <a href="${pageContext.request.contextPath}/produtos?locale=en_US" >Inglês </a> </h3>
<h1><spring:message code="users.welcome" arguments="${user.name}"></spring:message></h1>
	<h3> <a href="${pageContext.request.contextPath}/produtos/form" >Cadastrar produtos  </a> </h3>
	<h3><a href="${pageContext.request.contextPath}/produtos" > Produtos  </a> </h3>
	<h3><a href="${pageContext.request.contextPath}/shopping" >Shopping  </a> </h3>
</body>
</title>