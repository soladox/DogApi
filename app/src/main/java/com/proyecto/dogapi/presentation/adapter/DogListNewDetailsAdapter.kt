package com.proyecto.dogapi.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.dogapi.R
import com.proyecto.dogapi.utils.extension.inflate
import com.proyecto.dogapi.utils.extension.loadUrl
import com.proyecto.dogapi.utils.extension.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_new_dog_list_details.view.*

class DogListNewDetailsAdapter constructor(val listener: (String)->Unit): RecyclerView.Adapter<DogListNewDetailsAdapter.ViewHolder>(){
    private var images: ArrayList<String> = ArrayList()

    fun addList(images: List<String>){
        this.images.addAll(images)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogListNewDetailsAdapter.ViewHolder{
        return ViewHolder(
            parent.inflate(R.layout.item_new_dog_list_details)
        )
    }

    override fun getItemCount(): Int {
        return images.count()
    }

    override fun onBindViewHolder(holder: DogListNewDetailsAdapter.ViewHolder, position: Int) {
        holder.itemView.iv_new_image_dog.loadUrl(images[position])
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
            itemView.setSafeOnClickListener {
                listener(images[adapterPosition])
            }
        }
    }
}