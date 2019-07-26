package br.edu.infnet.professorjarvis.chat

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.professorjarvis.R
import br.edu.infnet.professorjarvis.chat.model.ChatMessage
import br.edu.infnet.professorjarvis.commom.jarvisMessageBackground
import br.edu.infnet.professorjarvis.commom.userMessageBackground
import kotlinx.android.synthetic.main.message_card.view.*

class MessageAdapter(var items:List<ChatMessage> = mutableListOf())
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = items.size

    fun setData(newMessages: List<ChatMessage>){
        items = newMessages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater
                            .from(parent.context)
                            .inflate(R.layout.message_card, parent, false)
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = items[position]
        if (holder is MessageViewHolder){
            //configura texto da mensagem
            holder.bodyTextView.text = message.text
            //configura hora de envio
            holder.momentTextView.text = message.moment

            // posição da mensagem na tela (esquerda ou direita)
            holder.card.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT).apply {
                gravity = if (message.fromUser) Gravity.END else Gravity.START
            }
            // cor de fundo da mensagem
            holder.card.setCardBackgroundColor(
                Color.parseColor(
                    if (message.fromUser) userMessageBackground
                    else jarvisMessageBackground))
            }
        }


    class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val bodyTextView: TextView = itemView.findViewById(R.id.message_textview)
        val momentTextView: TextView = itemView.findViewById(R.id.moment_textview)
        val card: CardView = itemView.findViewById(R.id.root_card)
    }
}