package br.edu.infnet.professorjarvis.chat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){
        val mockMessages = mutableListOf<ChatMessage>(
            ChatMessage("O rato roeu a roupa do rei de Roma", "19:49"),
            ChatMessage("abacaxi", "21:20", true),
            ChatMessage("Há vida em Marte?", "21:29")
        )
        chat_list.adapter = MessageAdapter(mockMessages)
        chat_list.layoutManager = LinearLayoutManager(context)
    }


}
