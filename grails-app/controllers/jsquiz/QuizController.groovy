package jsquiz

import org.springframework.dao.DataIntegrityViolationException

class QuizController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [quizInstanceList: Quiz.list(params), quizInstanceTotal: Quiz.count()]
    }

    def create() {
        [quizInstance: new Quiz(params)]
    }

    def save() {
        def quizInstance = new Quiz(params)
        if (!quizInstance.save(flush: true)) {
            render(view: "create", model: [quizInstance: quizInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'quiz.label', default: 'Quiz'), quizInstance.id])
        redirect(action: "show", id: quizInstance.id)
    }

    def show() {
        def quizInstance = Quiz.get(params.id)
        if (!quizInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'quiz.label', default: 'Quiz'), params.id])
            redirect(action: "list")
            return
        }

        [quizInstance: quizInstance]
    }

    def edit() {
        def quizInstance = Quiz.get(params.id)
        if (!quizInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quiz.label', default: 'Quiz'), params.id])
            redirect(action: "list")
            return
        }

        [quizInstance: quizInstance]
    }

    def update() {
        def quizInstance = Quiz.get(params.id)
        if (!quizInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quiz.label', default: 'Quiz'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (quizInstance.version > version) {
                quizInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'quiz.label', default: 'Quiz')] as Object[],
                          "Another user has updated this Quiz while you were editing")
                render(view: "edit", model: [quizInstance: quizInstance])
                return
            }
        }

        quizInstance.properties = params

        if (!quizInstance.save(flush: true)) {
            render(view: "edit", model: [quizInstance: quizInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'quiz.label', default: 'Quiz'), quizInstance.id])
        redirect(action: "show", id: quizInstance.id)
    }

    def delete() {
        def quizInstance = Quiz.get(params.id)
        if (!quizInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'quiz.label', default: 'Quiz'), params.id])
            redirect(action: "list")
            return
        }

        try {
            quizInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'quiz.label', default: 'Quiz'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'quiz.label', default: 'Quiz'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    def intra() {
       [quizInstance: new Quiz(params)] 
    }
    
    def intraSave() {
        // TODO create options 
        def options = []
        params.option.each {
            if(it!=null&&it!="")
                options.add(new QuizOption(description:it))
        }
        options.each { 
            it.save() 
            println " **** options = ${it}"
        }
        
        // TODO create quiz
        println "params.answer = ${params.answer}"
        params.answare = options.get(Integer.parseInt(params.answer))
        params.options = options
        println "params.answare = ${params.answare}"
        def q = new Quiz(params)
        q.save(flush:true)

        println " intra save new Quiz : ${q}"
        redirect(action: "list")
            return
       
    }
}
