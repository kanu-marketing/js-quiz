package jsquiz

class AnswerProcess {
    static belongsTo = [user:User2,answer:QuizOption]
    User2 user
    QuizOption answer
    static constraints = {
    }
}
