package br.edu.infnet.professorjarvis.chat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import br.edu.infnet.professorjarvis.R
import br.edu.infnet.professorjarvis.chat.model.ChatMessage
import kotlinx.android.synthetic.main.fragment_chat.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ChatFragment : Fragment() {

    private lateinit var chatViewModel: ChatViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            chatViewModel = ViewModelProviders.of(it).get(ChatViewModel::class.java)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpRecyclerView()
        subscribe()
        chatViewModel.messages.value = listOf<ChatMessage>(
            ChatMessage("O rato roeu a roupa do rei de Roma", "19:49"),
            ChatMessage("abacaxi", "21:20", true),
            ChatMessage("Há vida em Marte?", "21:29")
        )
    }

    private fun setUpListeners(){
        send_message_button.setOnClickListener {
            val message = chat_edittext.text.toString()
            val oldMessages = chatViewModel.messages.value
            chatViewModel.messages.value = oldMessages?.plus(
                            ChatMessage(message, "22:43", true))
        }
    }

    private fun setUpRecyclerView(){
        chat_list.adapter = MessageAdapter()
        chat_list.layoutManager = LinearLayoutManager(context)
    }

    private fun subscribe(){
        chatViewModel.messages.observe(this, Observer {messages->
            val messageAdapter = chat_list.adapter
            if (messageAdapter is MessageAdapter){
                messageAdapter.setData(messages)
            }
        })
    }


}
