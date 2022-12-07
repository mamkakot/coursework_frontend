package com.example.courseworkgum

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChoreModelAdapter constructor(var context: Context, var choreModels: ArrayList<ChoreModel>) : RecyclerView.Adapter<ChoreModelAdapter.ChoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoreViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.chore_card, parent, false)
        return ChoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChoreViewHolder, position: Int) {
        holder.textView?.text = choreModels[position].text
//        holder.imageView.setImageResource()
    }

    override fun getItemCount(): Int {
        return choreModels.count()
    }

    class ChoreViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = itemView.findViewById(R.id.textView)
    }
}