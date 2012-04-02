package jsquiz

class ResumeController {

    def order = ['school','major','gender','age']
    def index() { 
        for(int i=0;i<order.size();i++){
            if(params."${order[i]}"!=null)
                session[order[i]] = params."${order[i]}"
        }

        if(session.isNew())
            println "no session"
        else { 
            println "yes session"
            for(int i=0;i<order.size();i++){
                if(session[order[i]]==null){
                    render(contentType:"application/json") {
                        message=order[i]
                    }
                    return;
                }
            }
            
            // TODO create a user
            def ps = [
                      name:session.getId(),
                      school : session['school'],
                      major : session['major'],
                      age : session['age']
                      ]
            def userInstance = new User(ps)
            if (!userInstance.save(flush: true)) {
                    render(contentType:"application/json") {
                        message="create a user"
                        user=userInstance
                    }
                    //TODO redirect to Quiz mode
            }else
                    render(contentType:"application/json") {
                        message="create a fail user"
                        datas = session
                    }
        }
    }
    
    def test() {
        println params.getClass()
        def q = [123:1,4455:2,4546456:4]
        []
    }
    
      
}
