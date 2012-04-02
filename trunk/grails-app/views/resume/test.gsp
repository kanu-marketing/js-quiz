<html>
<head>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}" type="text/css" />
<g:javascript src="jquery-1.5.1.js" />

<script type="text/javascript">
    $(document).ready(function() {
        $(document).keydown(function(e) {
            alert(e.keyCode);
            if(e.keyCode == 87){
                 var prev=$('li.active').prev();
                 alert("prev : "+prev.length);
                 if(prev.length > 0){
                     $('li.active').removeClass('active');
                     prev.addClass('active');
                     alert("prev");
                     //prev.find('a').click();
                 }
             }
             if(e.keyCode == 83){
                 var next=$('li.active').next();
                 alert("next : "+next.length);
                 if(next.length > 0){
                     $('li.active').removeClass('active');
                     next.addClass('active');
                     alert("next");
                     //next.find('a').click();
                 }
             }
        });
});
</script>

</head>

<body>
<div id="test">
<ul>
<li class="active"><a id="home" href="home.html">Home</a></li>
<li><a id="my_work" href="my_work.html">My work</a></li>
<li><a id="blog" href="blog.html">Blog</a></li>
<li><a id="news" href="news.html">News</a></li>
</ul>
</div>
</body>
</html>