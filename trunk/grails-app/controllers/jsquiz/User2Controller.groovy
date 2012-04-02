package jsquiz

import org.springframework.dao.DataIntegrityViolationException

class User2Controller {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [user2InstanceList: User2.list(params), user2InstanceTotal: User2.count()]
    }

    def create() {
        [user2Instance: new User2(params)]
    }

    def save() {
        def user2Instance = new User2(params)
        if (!user2Instance.save(flush: true)) {
            render(view: "create", model: [user2Instance: user2Instance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'user2.label', default: 'User2'), user2Instance.id])
        redirect(action: "show", id: user2Instance.id)
    }

    def show() {
        def user2Instance = User2.get(params.id)
        if (!user2Instance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user2.label', default: 'User2'), params.id])
            redirect(action: "list")
            return
        }

        [user2Instance: user2Instance]
    }

    def edit() {
        def user2Instance = User2.get(params.id)
        if (!user2Instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user2.label', default: 'User2'), params.id])
            redirect(action: "list")
            return
        }

        [user2Instance: user2Instance]
    }

    def update() {
        def user2Instance = User2.get(params.id)
        if (!user2Instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user2.label', default: 'User2'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (user2Instance.version > version) {
                user2Instance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user2.label', default: 'User2')] as Object[],
                          "Another user has updated this User2 while you were editing")
                render(view: "edit", model: [user2Instance: user2Instance])
                return
            }
        }

        user2Instance.properties = params

        if (!user2Instance.save(flush: true)) {
            render(view: "edit", model: [user2Instance: user2Instance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'user2.label', default: 'User2'), user2Instance.id])
        redirect(action: "show", id: user2Instance.id)
    }

    def delete() {
        def user2Instance = User2.get(params.id)
        if (!user2Instance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user2.label', default: 'User2'), params.id])
            redirect(action: "list")
            return
        }

        try {
            user2Instance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'user2.label', default: 'User2'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user2.label', default: 'User2'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
