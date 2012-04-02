<%@ page import="jsquiz.User2" %>



<div class="fieldcontain ${hasErrors(bean: user2Instance, field: 'score', 'error')} required">
	<label for="score">
		<g:message code="user2.score.label" default="Score" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="score" required="" value="${fieldValue(bean: user2Instance, field: 'score')}"/>
</div>

