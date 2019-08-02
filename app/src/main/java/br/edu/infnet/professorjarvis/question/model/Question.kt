package br.edu.infnet.professorjarvis.question.model

class Question(val text: String,
               val options: List<String>,
               val correctOption: Int,
               val category: String = "Android",
               var hits: Int = 0,
               var misses: Int= 0)


fun test(){
    val q = Question("", listOf(), 0)
}