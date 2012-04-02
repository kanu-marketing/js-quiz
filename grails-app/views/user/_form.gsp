<%@ page import="jsquiz.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'age', 'error')} required">
	<label for="age">
		<g:message code="user.age.label" default="Age" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="age" required="" value="${fieldValue(bean: userInstance, field: 'age')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'major', 'error')} required">
	<label for="major">
		<g:message code="user.major.label" default="Major" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="major" name="major.id" from="${jsquiz.Major.list()}" optionKey="id" required="" value="${userInstance?.major?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${userInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'school', 'error')} required">
	<label for="school">
		<g:message code="user.school.label" default="School" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="school" name="school.id" from="${jsquiz.School.list()}" optionKey="id" required="" value="${userInstance?.school?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'score', 'error')} required">
	<label for="score">
		<g:message code="user.score.label" default="Score" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="score" required="" value="${fieldValue(bean: userInstance, field: 'score')}"/>
</div>

