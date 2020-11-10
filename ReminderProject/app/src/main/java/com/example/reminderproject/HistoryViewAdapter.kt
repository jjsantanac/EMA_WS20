package com.example.reminderproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryViewAdapter(private val historyList: List<historyItem>,private val listener:OnImageClickListener) :
    RecyclerView.Adapter<HistoryViewAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.historyview,
            parent, false
        )

        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = historyList[position]

        holder.textview1.text = currentItem.content
        holder.textview2.text = currentItem.title


        holder.removebutton.setOnClickListener {
            historyList.toMutableList().remove(currentItem)
        }
    }

    override fun getItemCount() = historyList.size




    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val textview1: TextView = itemView.findViewById(R.id.title1)
        val textview2: TextView = itemView.findViewById(R.id.content1)

        val removebutton: ImageView =itemView.findViewById(R.id.removeHistoryitem)

        init{
            removebutton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val position=adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onImageClick(position)
            }

        }
    }

    interface OnImageClickListener{
        fun onImageClick(position: Int)
    }
}