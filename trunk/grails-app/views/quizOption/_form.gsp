<%@ page import="jsquiz.QuizOption" %>



<div class="fieldcontain ${hasErrors(bean: quizOptionInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="quizOption.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${quizOptionInstance?.description}"/>
</div>

