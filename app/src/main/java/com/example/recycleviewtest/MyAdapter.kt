package com.example.recycleviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val mData: ArrayList<Contact>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BodyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    fun updateItems(newData: List<Contact>){
        mData.clear()
        mData.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mData[position]
        val bodyViewHolder = holder as BodyViewHolder
        bodyViewHolder.bind(data)
    }

    inner class BodyViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val textViewContact: TextView = itemView.findViewById(R.id.textViewContact)

        fun bind(item: Contact) {
            textViewContact.text = item.name
        }
    }

}