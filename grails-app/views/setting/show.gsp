
<%@ page import="jsquiz.Setting" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'setting.label', default: 'Setting')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-setting" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-setting" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list setting">
			
				<g:if test="${settingInstance?.passedScore}">
				<li class="fieldcontain">
					<span id="passedScore-label" class="property-label"><g:message code="setting.passedScore.label" default="Passed Score" /></span>
					
						<span class="property-value" aria-labelledby="passedScore-label"><g:fieldValue bean="${settingInstance}" field="passedScore"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${settingInstance?.totalQuiz}">
				<li class="fieldcontain">
					<span id="totalQuiz-label" class="property-label"><g:message code="setting.totalQuiz.label" default="Total Quiz" /></span>
					
						<span class="property-value" aria-labelledby="totalQuiz-label"><g:fieldValue bean="${settingInstance}" field="totalQuiz"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${settingInstance?.id}" />
					<g:link class="edit" action="edit" id="${settingInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
