package com.proyecto.dogapi.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.dogapi.R
import com.proyecto.dogapi.utils.extension.inflate
import com.proyecto.dogapi.utils.extension.loadUrl
import kotlinx.android.synthetic.main.item_dog_list_details.view.*

class DogListDetailsAdapter constructor(val listener: (String)->Unit):
    RecyclerView.Adapter<DogListDetailsAdapter.ViewHolder>(){
        private var images: ArrayList<String> = ArrayList()

        fun addList(images: List<String>){
            this.images.addAll(images)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogListDetailsAdapter.ViewHolder{
            return ViewHolder(
                parent.inflate(R.layout.item_dog_list_details)
            )
        }

        override fun getItemCount(): Int{
            return images.count()
        }

        override fun onBindViewHolder(holder: DogListDetailsAdapter.ViewHolder, position: Int){
            holder.itemView.iv_image_dog.loadUrl(images[position])
        }
        class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}