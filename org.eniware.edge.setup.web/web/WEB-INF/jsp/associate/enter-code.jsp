<p class="lead"><fmt:message key="new-Edge.intro"/></p>

<c:set var="myEdgesURL" value='${networkLinks["eniwareuser"]}/u/sec/my-Edges'/>
<p><fmt:message key="Edge.setup.code.intro">
	<fmt:param value="${myEdgesURL}"/>
</fmt:message></p>

<p><fmt:message key="Edge.setup.restore.option">
	<fmt:param><setup:url value="/associate/restore"/></fmt:param>
</fmt:message></p>

<setup:url value="/associate/preview" var="action"/>
<form:form action="${action}" method="post" cssClass="form-horizontal">
	<form:errors cssClass="alert alert-error" element="div" htmlEscape="false"/>
	<fieldset>
		<c:set var="err"><form:errors path="verificationCode" cssClass="help-inline" element="span"/></c:set>
		<div class="control-group<c:if test='${not empty err}'> error</c:if>">
			<label class="control-label" for="${settingId}">
				<fmt:message key="Edge.setup.code.verificationCode"/>
			</label>
			<div class="controls">
				<fmt:message key='Edge.setup.code.verificationCode.placeholder' var="placeholder"/>
				<form:textarea path="verificationCode" placeholder="${placeholder}" rows="10" cssClass="span9" required="required"/>
				<c:out value="${err}" escapeXml="false"/>
			</div>
		</div>
	</fieldset>
	<div class="form-actions">
		<button type="submit" class="btn btn-primary"><fmt:message key='Edge.setup.code.verify'/></button>
	</div>
	<sec:csrfInput/>
</form:form>
