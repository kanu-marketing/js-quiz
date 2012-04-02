
<%@ page import="jsquiz.Quiz" %>
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
		<div class="welcome">
			<p>曜碩科技校園徵才</p>
			<p>Welcome to iaSolution Online Quiz</p>
			<a name="home" id="home" href="welcome">START</a>
		</div>
	</div>
</div>


</body>
</html>
