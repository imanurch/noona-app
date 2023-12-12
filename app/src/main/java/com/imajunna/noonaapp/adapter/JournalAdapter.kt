package com.imajunna.noonaapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imajunna.noonaapp.R
import com.imajunna.noonaapp.model.JournalModel

class JournalAdapter(private val context: Context?, private val dataList:ArrayList<JournalModel>, private val onItemClick: (JournalModel) -> Unit):RecyclerView.Adapter<JournalAdapter.ViewHolderClass>() {


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.card_history_journal, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvDate.text = currentItem.hari

        holder.itemView.setOnClickListener {
            // Call the onItemClick function and pass the clicked item data
            onItemClick(currentItem)
        }
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView){
        val rvDate:TextView = itemView.findViewById(R.id.rv_history_journal_date)

    }
}