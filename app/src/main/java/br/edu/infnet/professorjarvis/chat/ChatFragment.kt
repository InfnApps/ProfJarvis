package br.edu.infnet.professorjarvis.chat


import android.content.Context
import android.os.AsyncTask
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
import java.nio.charset.MalformedInputException
import java.util.*

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
        val timestamp = Date().time

        if (chatViewModel.messages.value == null){
            // carrega mensagens do arquivo
            //chatViewModel.messages.value = loadMessages(BACKUP_FILENAME)
            LoadMessagesTask().execute(BACKUP_FILENAME, "dsd", "dsdsd", "ddds")
        }
    }

    private fun setUpListeners(){
        send_message_button.setOnClickListener {
            val message = chat_edittext.text.toString()
            // limpa o campo de mensagens
            chat_edittext.setText("")
            val oldMessages = chatViewModel.messages.value
            chatViewModel.messages.value = oldMessages?.plus(
                            ChatMessage(message, Date().time, true))
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

    override fun onStop() {
        super.onStop()
        // armazena um backup das mensagens
        saveMessages()
    }


    // Output para escrever
    private fun saveMessages(){
        //fos: FileOutputStream
        val messages = chatViewModel.messages.value
        if (messages != null && messages.size > 0) {
            context?.openFileOutput(BACKUP_FILENAME, Context.MODE_PRIVATE).use { fos ->
                fos?.bufferedWriter().use { writer ->

                    messages.forEach { msg ->
                        writer?.appendln("#\n$msg")
                    }
                }
            }
        }
    }

    // Input para ler
    private fun loadMessages(filename: String): List<ChatMessage>{
        val messagesList = mutableListOf<ChatMessage>()
        context?.let{
            it.openFileInput(filename).use {fis->
                fis.bufferedReader().use {reader->
                    val lines  = reader.readLines()
                    var index = 0
                    while (index < lines.size){
                        if (lines[index++] == MESSAGE_DIVIDER){
                            val text = lines[index++]
                            val timestamp = lines[index++].toLong()
                            val fromUser = lines[index++].toBoolean()
                            messagesList.add(ChatMessage(text, timestamp, fromUser))
                        } else {
                            throw MalFormedFileException()
                        }
                    }
                }
            }
        }
        return  messagesList
    }


    inner class LoadMessagesTask(): AsyncTask<String, Void, List<ChatMessage>>(){
        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg args: String?): List<ChatMessage> {
            // fora da thread principal
            Thread.sleep(2000)
            val filename = args[0]
            return if (filename != null){
                loadMessages(filename)
            } else {
                listOf<ChatMessage>()
            }
        }

        override fun onPostExecute(result: List<ChatMessage>?) {
            super.onPostExecute(result)
            result?.let {
                chatViewModel.messages.value = loadMessages(BACKUP_FILENAME)
            }
            progressBar.visibility = View.GONE
        }

    }

}
