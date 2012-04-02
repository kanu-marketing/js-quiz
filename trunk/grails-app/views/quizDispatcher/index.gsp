
<%@ page import="jsquiz.Quiz" %>
<!doctype html>
<html>
	<head>
		<g:set var="entityName" value="${message(code: 'quiz.label', default: 'Quiz')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}" type="text/css" />
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'ia_main.css')}" type="text/css" />
        
        <g:javascript src="jquery-1.5.1.js" />

<script type="text/javascript">
    $(document).ready(function() {
        $(document).keydown(function(e) {
            //alert(e.keyCode);
            if(e.keyCode == 87){
                 var prev=$('li.active').prev();
                 //alert("prev : "+prev.length);
                 if(prev.length > 0){
                     $('li.active').removeClass('active');
                     prev.addClass('active');
                 }
             }
             if(e.keyCode == 83){
                 var next=$('li.active').next();
                 //alert("next : "+next.length);
                 if(next.length > 0){
                     $('li.active').removeClass('active');
                     next.addClass('active');
                 }
             }
             
             if(e.keyCode == 68 ){
                document.getElementById("myForm").submit();
             }
             <g:if test="${prevQuizId}">
             if(e.keyCode == 65 ){
                window.location.assign($('#prev').attr('href'));
             }
             </g:if>
             if(e.keyCode == 81 ){
                $('li.active').find('input').attr('checked', true);           
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
		<g:form name="myForm" action="reportQuiz">
            <g:hiddenField name="q" value="${quizInstance?.id}" />
			<div class="ques">
				<h3> (${currentQuizNum}/${totalQuizNum}) Q: <g:fieldValue bean="${quizInstance}" field="question"/> </h3>
   
			    
				<ol>
                <g:if test="${quizInstance?.options}">
                    <g:listQuiz options="${quizInstance.options}" selected="${currentSelected}" />
                </g:if>
				</ol>
			</div>
			<div class="btns">
                <g:if test="${prevQuizId}">
                    <!--<input type="button" value="Back" />-->
                    <a name="prev" id="prev" href="requestQuiz?req=${prevQuizId}"><input type="button" value="Back"/></a>
                </g:if>
				
                <g:actionSubmit value="Next" action="reportQuiz"/>
			</div>
		</g:form>
	</div>
</div>

</body>
</html>
