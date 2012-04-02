package jsquiz

import org.springframework.dao.DataIntegrityViolationException

class QuizOptionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [quizOptionInstanceList: QuizOption.list(params), quizOptionInstanceTotal: QuizOption.count()]
    }

    def create() {
        [quizOptionInstance: new QuizOption(params)]
    }

    def save() {
        def quizOptionInstance = new QuizOption(params)
        if (!quizOptionInstance.save(flush: true)) {
            render(view: "create", model: [quizOptionInstance: quizOptionInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'quizOption.label', default: 'QuizOption'), quizOptionInstance.id])
        redirect(action: "show", id: quizOptionInstance.id)
    }

    def show() {
        def quizOptionInstance = QuizOption.get(params.id)
        if (!quizOptionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizOption.label', default: 'QuizOption'), params.id])
            redirect(action: "list")
            return
        }

        [quizOptionInstance: quizOptionInstance]
    }

    def edit() {
        def quizOptionInstance = QuizOption.get(params.id)
        if (!quizOptionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizOption.label', default: 'QuizOption'), params.id])
            redirect(action: "list")
            return
        }

        [quizOptionInstance: quizOptionInstance]
    }

    def update() {
        def quizOptionInstance = QuizOption.get(params.id)
        if (!quizOptionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizOption.label', default: 'QuizOption'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (quizOptionInstance.version > version) {
                quizOptionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'quizOption.label', default: 'QuizOption')] as Object[],
                          "Another user has updated this QuizOption while you were editing")
                render(view: "edit", model: [quizOptionInstance: quizOptionInstance])
                return
            }
        }

        quizOptionInstance.properties = params

        if (!quizOptionInstance.save(flush: true)) {
            render(view: "edit", model: [quizOptionInstance: quizOptionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'quizOption.label', default: 'QuizOption'), quizOptionInstance.id])
        redirect(action: "show", id: quizOptionInstance.id)
    }

    def delete() {
        def quizOptionInstance = QuizOption.get(params.id)
        if (!quizOptionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizOption.label', default: 'QuizOption'), params.id])
            redirect(action: "list")
            return
        }

        try {
            quizOptionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'quizOption.label', default: 'QuizOption'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'quizOption.label', default: 'QuizOption'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
