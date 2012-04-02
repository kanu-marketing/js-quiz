package jsquiz

class Quiz {
    static hasMany = [options : QuizOption]
    String question
    List options
    QuizOption answare
    QuizType type
    Integer score = 10
    static constraints = {
    }
    
    boolean isCorrect(long answer){
        return answare.id == answer
    } 
    
    Integer getScore(def answer){
        if(answare.id == Long.parseLong(answer))
            return score
        else
            return 0
    }
    
    public String toString(){
        return question
    }
}
