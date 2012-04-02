import jsquiz.*
import org.apache.shiro.crypto.hash.Sha512Hash
class BootStrap {

    def init = { servletContext ->
        if(QuizType.count()==0){
            new QuizType(name:"quiz").save()
            new QuizType(name:"survey").save()
        }
        
        if(Setting.count()==0){
            new Setting(passedScore:80,totalQuiz:10).save(flush:true)
        }
        
        if(QuizOption.count()==0){
          def opts = []
          opts.add(new QuizOption(description:"Log.a"))
          opts.add(new QuizOption(description:"Log.b"))
          opts.add(new QuizOption(description:"Log.c"))
          opts.add(new QuizOption(description:"Log.d"))
          opts.each{it.save()}
          new Quiz(question:"請問Log訊息形式, 哪一種是除錯訊息?",
                   type:QuizType.findByName("quiz"),
                   answare:opts[3],
                   options:opts
                   ).save()
          
          opts = []
          opts.add(new QuizOption(description:"2個"))
          opts.add(new QuizOption(description:"4個"))
          opts.add(new QuizOption(description:"6個"))
          opts.add(new QuizOption(description:"8個"))
          opts.each{it.save()}
          new Quiz(question:"Options Menu 最多可以有幾個?",
                   type:QuizType.findByName("quiz"),
                   answare:opts[1],
                   options:opts
                   ).save()  
                   
          opts = []
          opts.add(new QuizOption(description:"單選、可以"))
          opts.add(new QuizOption(description:"單選、不可以"))
          opts.add(new QuizOption(description:"複選、可以"))
          opts.add(new QuizOption(description:"複選、不可以"))
          opts.each{it.save()}
          def q = new Quiz(question:"Spinner 是單選還是複選? 當列表超過螢幕a大小時是否可以捲動?",
                   type:QuizType.findByName("quiz"),
                   answare:opts[1],
                   options:opts
                   )
          q.save()
          if(q.hasErrors()){
            q.errors.each {println it }
          }
       }
       if(ShiroRole.count()==0){
            new ShiroRole()
       }
       if(ShiroUser.count()==0){
            def admin = new ShiroUser(username:"admin",passwordHash:"iasolution".encodeAsSHA256())
            admin.addToRoles(new ShiroRole(name:"ADMIN"))
            admin.save(flush:true)
            if(admin.hasErrors()){ admin.errors.each {println it } }
       }
        
    }
    def destroy = {
    }
}
