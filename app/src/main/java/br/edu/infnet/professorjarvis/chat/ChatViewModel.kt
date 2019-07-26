package br.edu.infnet.professorjarvis.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.professorjarvis.chat.model.ChatMessage

class ChatViewModel: ViewModel() {
    // lista de mensagens do chat
    val messages = MutableLiveData<List<ChatMessage>>()
}