<%@ page import="jsquiz.AnswerProcess" %>



<div class="fieldcontain ${hasErrors(bean: answerProcessInstance, field: 'answer', 'error')} required">
	<label for="answer">
		<g:message code="answerProcess.answer.label" default="Answer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="answer" name="answer.id" from="${jsquiz.QuizOption.list()}" optionKey="id" required="" value="${answerProcessInstance?.answer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: answerProcessInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="answerProcess.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${jsquiz.User2.list()}" optionKey="id" required="" value="${answerProcessInstance?.user?.id}" class="many-to-one"/>
</div>

