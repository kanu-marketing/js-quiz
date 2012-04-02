<%@ page import="jsquiz.QuizType" %>



<div class="fieldcontain ${hasErrors(bean: quizTypeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="quizType.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${quizTypeInstance?.name}"/>
</div>

