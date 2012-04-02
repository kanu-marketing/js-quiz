package jsquiz

class QuizTagLib {
    /**
     * Renders the body with an listQuiz.
     *
     * @attr happy whether to show a happy listQuiz ('true') or
     * a sad listQuiz ('false')
     */
    /*def listQuiz = { attrs, body ->
       out << body() << (attrs.happy == 'true' ? " :-)" : " :-(")
    }*/
    
    def listQuiz = { attrs, body ->
       def selected = (attrs.selected==null) ? attrs.options[0].id : Long.parseLong(attrs.selected)
       println "  %%%% attrs.selected = ${ attrs.selected}"
       println "  %%%% selected = ${selected}"
       attrs.options.each { opt ->
            println "  %%%% taglib  opt = ${opt} isSelected = ${opt.id==selected} selected.class = ${selected.getClass()}"
            if(opt.id==selected)
                out << '<li class="active"><input checked="yes" type="radio" name="answer" value="' + opt.id + '" id="answer" class="radio"/>' + opt.description +"</li>"
            else
                out << '<li><input type="radio" name="answer" value="' + opt.id + '" id="answer"  class="radio" />'+ opt.description +"</li>"
       }
       
    }
}
