package br.edu.infnet.professorjarvis.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.professorjarvis.R
import br.edu.infnet.professorjarvis.chat.model.ChatMessage
import kotlinx.android.synthetic.main.message_card.view.*

class MessageAdapter(val items:MutableList<ChatMessage> = mutableListOf())
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater
                            .from(parent.context)
                            .inflate(R.layout.message_card, parent, false)
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = items[position]
        if (holder is MessageViewHolder){
            holder.bodyTextView.text = message.text
            holder.momentTextView.text = message.moment
            if (message.fromUser){
                //holder.card.layoutParams.
            }
        }
    }

    class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val bodyTextView: TextView = itemView.findViewById(R.id.message_textview)
        val momentTextView: TextView = itemView.findViewById(R.id.moment_textview)
        val card: CardView = itemView.findViewById(R.id.root_card)
    }
}