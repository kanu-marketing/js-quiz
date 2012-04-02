
<%@ page import="jsquiz.User2" %>

<!doctype html>
<html>
	<head>
		<g:set var="entityName" value="${message(code: 'quiz.label', default: 'Quiz')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'ia_main.css')}" type="text/css" />
        <g:javascript src="jquery-1.5.1.js" />

<script type="text/javascript">
    $(document).ready(function() {
        $(document).keydown(function(e) {
             if(e.keyCode == 81 ){
                 window.location.assign($('#home').attr('href'));
             }
        });
});
</script>

	</head>
	<body>
    

<div id="wrap">
	<div id="header">
		<h1>iaSolution</h1>
	</div>
	<div id="main">
		<form>
			<div class="result">
                <g:if test="${isPass}">
                    <div class="pass">
					Congratulation! 恭喜你獲得大獎!
                    <g:if test="${userInstance}">
					<ul>
                    
						<li>Your ID : <g:fieldValue bean="${userInstance}" field="id"/></li>
						<li>Your Score : <g:fieldValue bean="${userInstance}" field="score"/></li>
					</ul>
                    </g:if>
				</div>
                </g:if>
                <g:else>
                    <div class="fail">
					Thank you! 希望你再接再厲!
                    <g:if test="${userInstance}">
					<ul>
                    
						<li>Your ID : <g:fieldValue bean="${userInstance}" field="id"/></li>
						<li>Your Score : <g:fieldValue bean="${userInstance}" field="score"/></li>
					</ul>
                    </g:if>
				</div>
                </g:else>
                <ul>
				
                </ul>
			</div>
			<div class="btns">
                <a name="home" id="home" href="/jsQuiz/quizDispatcher/"><input type="button" value="Home" /></a>
			</div>
		</form>
	</div>
</div>
	</body>
</html>
