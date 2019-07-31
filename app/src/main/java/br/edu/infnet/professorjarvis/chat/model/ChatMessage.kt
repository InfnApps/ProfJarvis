package br.edu.infnet.professorjarvis.chat.model

import java.text.SimpleDateFormat

class ChatMessage(val text: String,
                  val timestamp: Long,
                  val fromUser: Boolean = false){
    val moment:String
    get() {
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(timestamp)
    }

    override fun toString(): String {
        return "$text\n$timestamp\n$fromUser"
    }
}