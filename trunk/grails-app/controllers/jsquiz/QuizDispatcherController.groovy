package jsquiz

class QuizDispatcherController {

    def quizGeneratorService
    def index() {
        render(view:"welcome")
    }
    
    def requestQuiz() {
        def result = null
        if(params.req!=null)
            result = Quiz.get(Long.parseLong(params.req))
        println "   @@@@@ currentSelected = " + session.answer.get(params.req)
        render(view:"index",model:[quizInstance:result,
                                   prevQuizId:getPrevQuizId(session.answer,result.id),
                                   currentSelected:session.answer.get(params.req),
                                   currentQuizNum:getNumFinishedQuiz(session.answer,result.id),
                                   totalQuizNum:session.answer.size()])
    }
    
    //private int TH = 10
    
    int getScoreThreshold(){
        return Setting.list()[0].passedScore
    }
    
    def reportQuiz() { 
        println "reportQuiz : ${params}"
        if(params.q!=null && params.answer!=null)
            session?.answer?."${params.q}" = params.answer
         if(isFinishAllQuiz(session)){
            def score = getScore(session)
            println "isFinishAllQuiz -> getScore = ${score}"
            def user = new User2(score:score)
            user.save(flush:true)
            println "user -> ${user}"
            session.answer?.each { k , v ->
                //def a = new Answer(user:user,question:Quiz.get(Long.parseLong(k)),answer:QuizOption.get(Long.parseLong(v)))
                def a = new AnswerProcess(user:user,answer:QuizOption.get(Long.parseLong(v)))
                a.save()
                if(a.hasErrors())
                    a.error.each { println "save error : ${it}" }
            }
            def isPass = (score>=getScoreThreshold()) ? true : false
            render(view:"result",model:[userInstance:user,isPass:isPass])
         }else{
            def result = getQuiz(session)
            println "get new quiz : ${result}"
            def pid = getPrevQuizId(session.answer,result.id)
            def finishedNumQuiz = getNumFinishedQuiz(session.answer,result.id)
            println " ^^^^ pid = ${pid}"
            render(view:"index",model:[quizInstance:result,
                                       prevQuizId:pid,
                                       currentQuizNum:finishedNumQuiz,
                                       totalQuizNum:session.answer.size()
                                       ])
         }
    }
    
    def welcome() { 
        session.answer = quizGeneratorService.generate()
        def result = getQuiz(session)
        render(view:"index",model:[quizInstance:result,currentQuizNum:1,totalQuizNum:session.answer.size()])
    }
    
    Float getScore(def session){
        def quiz = null
        def score = 0
        session?.answer?.each { k , v ->
            if(v!=-1){
                quiz = Quiz.get(Long.parseLong(k))
                if(quiz != null )
                    score += quiz.getScore(v)
            }
        }
        return score
    }
    //FIXME
    def getPrevQuizId(def quizs , long currentQuizId){
        def qid = quizs.findIndexOf{ it.key==String.valueOf(currentQuizId) }
        println " &&&& qid = ${qid} : currentQuizId = ${currentQuizId} , quizs = ${quizs} size : ${quizs.size()}"
        if(qid>0&& qid < quizs.size()){
            println " get a prev quiz id : ${quizs[qid-1]}"
            //FIXME
            int count 
            def result = null
            def prev = null
            quizs.each { k, v ->
                if(count==qid && result==null)
                    result = prev
                prev = k
                count++
            }

            return result
        }else
            return null
    }
    
    def getNumFinishedQuiz(def quizs , long currentQuizId){
        def qid = quizs.findIndexOf{ it.key==String.valueOf(currentQuizId) }
        return qid+1 // the index is start at zero
    }
    
    Quiz getQuiz(def session) { 
        def result
        session?.answer?.each { k , v ->
                    println "${k} , ${v}"  
                    println v.getClass()
                    println '${v} != -1 : ' + (v==-1)
                    if(v==-1 && result == null){
                        println "select quiz : ${Long.parseLong(k)}"
                        result  = Quiz.get(Long.parseLong(k))
                    }
        }
        return result
    }
    
    
    
    boolean isFinishAllQuiz(def session){
        // return session.answer.size()<=MAX_QUIZ
        def result = true
        session?.answer?.each { k , v ->
            if(v==-1){ result = false }
        }
        return result
    }
}
