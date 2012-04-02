package jsquiz

import org.springframework.dao.DataIntegrityViolationException

class AnswerProcessController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [answerProcessInstanceList: AnswerProcess.list(params), answerProcessInstanceTotal: AnswerProcess.count()]
    }

    def create() {
        [answerProcessInstance: new AnswerProcess(params)]
    }

    def save() {
        def answerProcessInstance = new AnswerProcess(params)
        if (!answerProcessInstance.save(flush: true)) {
            render(view: "create", model: [answerProcessInstance: answerProcessInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'answerProcess.label', default: 'AnswerProcess'), answerProcessInstance.id])
        redirect(action: "show", id: answerProcessInstance.id)
    }

    def show() {
        def answerProcessInstance = AnswerProcess.get(params.id)
        if (!answerProcessInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'answerProcess.label', default: 'AnswerProcess'), params.id])
            redirect(action: "list")
            return
        }

        [answerProcessInstance: answerProcessInstance]
    }

    def edit() {
        def answerProcessInstance = AnswerProcess.get(params.id)
        if (!answerProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answerProcess.label', default: 'AnswerProcess'), params.id])
            redirect(action: "list")
            return
        }

        [answerProcessInstance: answerProcessInstance]
    }

    def update() {
        def answerProcessInstance = AnswerProcess.get(params.id)
        if (!answerProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answerProcess.label', default: 'AnswerProcess'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (answerProcessInstance.version > version) {
                answerProcessInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'answerProcess.label', default: 'AnswerProcess')] as Object[],
                          "Another user has updated this AnswerProcess while you were editing")
                render(view: "edit", model: [answerProcessInstance: answerProcessInstance])
                return
            }
        }

        answerProcessInstance.properties = params

        if (!answerProcessInstance.save(flush: true)) {
            render(view: "edit", model: [answerProcessInstance: answerProcessInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'answerProcess.label', default: 'AnswerProcess'), answerProcessInstance.id])
        redirect(action: "show", id: answerProcessInstance.id)
    }

    def delete() {
        def answerProcessInstance = AnswerProcess.get(params.id)
        if (!answerProcessInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'answerProcess.label', default: 'AnswerProcess'), params.id])
            redirect(action: "list")
            return
        }

        try {
            answerProcessInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'answerProcess.label', default: 'AnswerProcess'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'answerProcess.label', default: 'AnswerProcess'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
