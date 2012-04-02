<%@ page import="jsquiz.Setting" %>



<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'passedScore', 'error')} required">
	<label for="passedScore">
		<g:message code="setting.passedScore.label" default="Passed Score" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="passedScore" required="" value="${fieldValue(bean: settingInstance, field: 'passedScore')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'totalQuiz', 'error')} required">
	<label for="totalQuiz">
		<g:message code="setting.totalQuiz.label" default="Total Quiz" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="totalQuiz" required="" value="${fieldValue(bean: settingInstance, field: 'totalQuiz')}"/>
</div>

