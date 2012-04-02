
<%@ page import="jsquiz.Quiz" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'quiz.label', default: 'Quiz')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-quiz" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-quiz" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list quiz">
			
				<g:if test="${quizInstance?.answare}">
				<li class="fieldcontain">
					<span id="answare-label" class="property-label"><g:message code="quiz.answare.label" default="Answare" /></span>
					
						<span class="property-value" aria-labelledby="answare-label"><g:link controller="quizOption" action="show" id="${quizInstance?.answare?.id}">${quizInstance?.answare?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${quizInstance?.options}">
				<li class="fieldcontain">
					<span id="options-label" class="property-label"><g:message code="quiz.options.label" default="Options" /></span>
					
						<g:each in="${quizInstance.options}" var="o">
						<span class="property-value" aria-labelledby="options-label"><g:link controller="quizOption" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${quizInstance?.question}">
				<li class="fieldcontain">
					<span id="question-label" class="property-label"><g:message code="quiz.question.label" default="Question" /></span>
					
						<span class="property-value" aria-labelledby="question-label"><g:fieldValue bean="${quizInstance}" field="question"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${quizInstance?.score}">
				<li class="fieldcontain">
					<span id="score-label" class="property-label"><g:message code="quiz.score.label" default="Score" /></span>
					
						<span class="property-value" aria-labelledby="score-label"><g:fieldValue bean="${quizInstance}" field="score"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${quizInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="quiz.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:link controller="quizType" action="show" id="${quizInstance?.type?.id}">${quizInstance?.type?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${quizInstance?.id}" />
					<g:link class="edit" action="edit" id="${quizInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
