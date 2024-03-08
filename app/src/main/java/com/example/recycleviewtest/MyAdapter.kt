package com.example.recycleviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val mData: ArrayList<HistoryBodyData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BodyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    fun updateItems(newData: List<HistoryBodyData>){
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
        bodyViewHolder.bind(data as HistoryBodyData)
    }

    inner class BodyViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        private val textViewBody: TextView = itemView.findViewById(R.id.textViewBody)
        //private val textViewSum: TextView = itemView.findViewById(R.id.textViewSum)
        private val imageViewService: ImageView = itemView.findViewById(R.id.imageViewService)

        fun bind(item: HistoryBodyData) {
            textViewTitle.text = item.title
            textViewBody.text = item.body
            //textViewSum.text = item.endText
            imageViewService.setImageResource(item.icon)
        }
    }

}