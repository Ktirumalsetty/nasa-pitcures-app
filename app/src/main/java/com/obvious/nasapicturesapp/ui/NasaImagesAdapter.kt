package com.obvious.nasapicturesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.obvious.nasapicturesapp.databinding.NasaImageListItemBinding
import com.obvious.nasapicturesapp.model.Nasa
import com.obvious.nasapicturesapp.utils.loadFromUrl

class NasaImagesAdapter(val onItemClick : ((Int) -> Unit?)? = null): ListAdapter<Nasa, NasaImagesAdapter.NasaImagesVH>(NasaImageDiffUtil()) {

    inner class NasaImagesVH(private val binding:NasaImageListItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:Nasa,position: Int){
            binding.apply {
                imageThumbNail.loadFromUrl(item.url,binding.root.context)
                imageTitle.text = item.title
                root.setOnClickListener(View.OnClickListener {
                    onItemClick?.invoke(position)
                })
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaImagesVH {

        return NasaImagesVH(NasaImageListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: NasaImagesVH, position: Int) {
        holder.bind(getItem(position),position)
    }


    class NasaImageDiffUtil : DiffUtil.ItemCallback<Nasa>() {

        override fun areItemsTheSame(oldItem: Nasa, newItem: Nasa): Boolean {
            return oldItem.explanation == newItem.explanation
        }

        override fun areContentsTheSame(oldItem: Nasa, newItem: Nasa): Boolean {
            return oldItem == newItem
        }

    }
}