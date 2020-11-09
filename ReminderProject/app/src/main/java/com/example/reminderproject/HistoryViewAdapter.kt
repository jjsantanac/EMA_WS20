package com.example.reminderproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryViewAdapter(private val historyList: List<historyItem>) :
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

    }

    override fun getItemCount() = historyList.size


    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textview1: TextView = itemView.findViewById(R.id.title1)
        val textview2: TextView = itemView.findViewById(R.id.content1)


    }
}