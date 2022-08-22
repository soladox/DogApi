package com.proyecto.dogapi.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.dogapi.R
import com.proyecto.dogapi.utils.extension.inflate
import com.proyecto.dogapi.utils.extension.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_dog_list.view.*

class DogListAdapter constructor(val listener: (String) -> Unit):
    RecyclerView.Adapter<DogListAdapter.ViewHolder>(){
        private var message: List<String> = ArrayList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogListAdapter.ViewHolder{
            return ViewHolder(
                parent.inflate(R.layout.item_dog_list)
            )
        }

    override fun getItemCount(): Int {
        return message.size
    }

    override fun onBindViewHolder(holder: DogListAdapter.ViewHolder, position: Int){
        holder.itemView.tv_breed.text = message[position]
    }

    fun setList(message: List<String>){
        this.message = message
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        init{
            itemView.setSafeOnClickListener {
                listener(message[adapterPosition])
            }
        }
    }
}