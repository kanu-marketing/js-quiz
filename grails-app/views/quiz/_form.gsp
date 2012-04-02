<%@ page import="jsquiz.Quiz" %>



<div class="fieldcontain ${hasErrors(bean: quizInstance, field: 'answare', 'error')} required">
	<label for="answare">
		<g:message code="quiz.answare.label" default="Answare" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="answare" name="answare.id" from="${jsquiz.QuizOption.list()}" optionKey="id" required="" value="${quizInstance?.answare?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: quizInstance, field: 'options', 'error')} ">
	<label for="options">
		<g:message code="quiz.options.label" default="Options" />
		
	</label>
	<g:select name="options" from="${jsquiz.QuizOption.list()}" multiple="multiple" optionKey="id" size="5" value="${quizInstance?.options*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: quizInstance, field: 'question', 'error')} ">
	<label for="question">
		<g:message code="quiz.question.label" default="Question" />
		
	</label>
	<g:textField name="question" value="${quizInstance?.question}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: quizInstance, field: 'score', 'error')} required">
	<label for="score">
		<g:message code="quiz.score.label" default="Score" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="score" required="" value="${fieldValue(bean: quizInstance, field: 'score')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: quizInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="quiz.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="type" name="type.id" from="${jsquiz.QuizType.list()}" optionKey="id" required="" value="${quizInstance?.type?.id}" class="many-to-one"/>
</div>

