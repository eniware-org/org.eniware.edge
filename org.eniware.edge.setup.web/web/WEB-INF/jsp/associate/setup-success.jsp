<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<p class="lead"><fmt:message key="Edge.setup.success.intro"/></p>

<table class="table">
	<tr><th><fmt:message key="Edge.setup.identity.service"/></th><td>${details.host}</td></tr>
	<tr><th><fmt:message key="Edge.setup.identity.EdgeId"/></th><td>${details.networkId}</td></tr>
	<tr><th><fmt:message key="Edge.setup.identity.username"/></th><td>${details.username}</td></tr>
	<c:if test="${not empty user}">
		<tr>
			<th><fmt:message key="Edge.setup.success.user.password"/></th>
			<td>
				<p class="text-error"><code>${user.password}</code></p>
				<div class="alert">
					<fmt:message key='Edge.setup.success.user.intro'>
						<fmt:param><setup:url value="/a/user/change-password">
							<spring:param name="old" value="${user.password}"/>
						</setup:url></fmt:param>
					</fmt:message>
				</div>
			</td>
		</tr>
	</c:if>
</table>

<c:set var="myEdgesURL" value="${association.eniwareUserServiceURL}/u/sec/my-Edges"/>
<c:choose>
	<c:when test="${empty details.networkCertificateStatus}">
		<p>
			<fmt:message key="Edge.setup.success.visit">
				<fmt:param value="${myEdgesURL}"/>
				<fmt:param><setup:url value='/a/certs'/></fmt:param>
			</fmt:message>
		</p>
	</c:when>
	<c:when test="${details.networkCertificateStatus == 'Active'}">
		<p>
			<fmt:message key="Edge.setup.success.active">
				<fmt:param value="${myEdgesURL}"/>
			</fmt:message>
		</p>
	</c:when>
	<c:when test="${details.networkCertificateStatus == 'Requested'}">
		<p>
			<fmt:message key="Edge.setup.success.requested">
				<fmt:param value="${myEdgesURL}"/>
				<fmt:param><setup:url value='/a/certs'/></fmt:param>
			</fmt:message>
		</p>
	</c:when>
	<c:when test="${not empty csr}">
		<h2><fmt:message key='certs.csr.title'/></h2>
		<p><fmt:message key='certs.csr.intro'/></p>
		<pre class="cert well">${csr}</pre>
	</c:when>
</c:choose>

