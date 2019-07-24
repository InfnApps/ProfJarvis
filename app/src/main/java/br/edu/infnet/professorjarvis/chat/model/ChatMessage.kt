package br.edu.infnet.professorjarvis.chat.model

class ChatMessage(val text: String,
                  val moment: String,
                  val fromUser: Boolean = false)