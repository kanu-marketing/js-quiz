<%@ page import="jsquiz.Quiz" %>


<div class="fieldcontain ${hasErrors(bean: quizInstance, field: 'question', 'error')} ">
	<label for="question">
		<g:message code="quiz.question.label" default="Question" />
	</label>
	<g:textField name="question" value="${quizInstance?.question}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: quizInstance, field: 'options', 'error')} ">
	<label for="options">
		<g:message code="quiz.options.label" default="Options" />		
	</label>
        <li>(0)<g:textField name="option" value=""/></li>
        <li>(1)<g:textField name="option" value=""/></li>
        <li>(2)<g:textField name="option" value=""/></li>
        <li>(3)<g:textField name="option" value=""/></li>
        <li>(4)<g:textField name="option" value=""/></li>
    
</div>

<div class="fieldcontain ${hasErrors(bean: quizInstance, field: 'answare', 'error')} required">
	<label for="answare">
		<g:message code="quiz.answare.label" default="Answare" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="answer" required="" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: quizInstance, field: 'score', 'error')} required">
	<label for="score">
		<g:message code="quiz.score.label" default="Score" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="score" required="" value="${fieldValue(bean: quizInstance, field: 'score')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: quizInstance, field: 'type', 'error')} required">
    <g:hiddenField name="type.id" value="1" />
</div>

