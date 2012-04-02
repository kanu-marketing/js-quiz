package jsquiz

import org.springframework.dao.DataIntegrityViolationException

class QuizTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [quizTypeInstanceList: QuizType.list(params), quizTypeInstanceTotal: QuizType.count()]
    }

    def create() {
        [quizTypeInstance: new QuizType(params)]
    }

    def save() {
        def quizTypeInstance = new QuizType(params)
        if (!quizTypeInstance.save(flush: true)) {
            render(view: "create", model: [quizTypeInstance: quizTypeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'quizType.label', default: 'QuizType'), quizTypeInstance.id])
        redirect(action: "show", id: quizTypeInstance.id)
    }

    def show() {
        def quizTypeInstance = QuizType.get(params.id)
        if (!quizTypeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizType.label', default: 'QuizType'), params.id])
            redirect(action: "list")
            return
        }

        [quizTypeInstance: quizTypeInstance]
    }

    def edit() {
        def quizTypeInstance = QuizType.get(params.id)
        if (!quizTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizType.label', default: 'QuizType'), params.id])
            redirect(action: "list")
            return
        }

        [quizTypeInstance: quizTypeInstance]
    }

    def update() {
        def quizTypeInstance = QuizType.get(params.id)
        if (!quizTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizType.label', default: 'QuizType'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (quizTypeInstance.version > version) {
                quizTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'quizType.label', default: 'QuizType')] as Object[],
                          "Another user has updated this QuizType while you were editing")
                render(view: "edit", model: [quizTypeInstance: quizTypeInstance])
                return
            }
        }

        quizTypeInstance.properties = params

        if (!quizTypeInstance.save(flush: true)) {
            render(view: "edit", model: [quizTypeInstance: quizTypeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'quizType.label', default: 'QuizType'), quizTypeInstance.id])
        redirect(action: "show", id: quizTypeInstance.id)
    }

    def delete() {
        def quizTypeInstance = QuizType.get(params.id)
        if (!quizTypeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizType.label', default: 'QuizType'), params.id])
            redirect(action: "list")
            return
        }

        try {
            quizTypeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'quizType.label', default: 'QuizType'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'quizType.label', default: 'QuizType'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
