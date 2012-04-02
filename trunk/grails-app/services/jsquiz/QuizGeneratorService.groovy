package jsquiz

import java.util.Random

class QuizGeneratorService {
//    private int EXAM_TOTAL_QUIZ_NUM = 10
    int getTotalQuiz(){
        return Setting.list()[0].totalQuiz
    }
    def generate() {
        def r = new Random()
        //println "call QuizGeneratorService.generate()"
        def q = Quiz.list()
        def result = [:]
        def count = 0
        while(q.size()>0 && count < getTotalQuiz()){
           //println "q.size() = ${q.size()}"
           def tmp = r.nextInt(q.size())
           //println "r.nextInt(q.size()) = ${tmp}"
           result."${q[tmp].id}" = -1
           q.remove(tmp)
           count ++
        }
        
        println "generate result : ${result}"
        return result
    }
}
