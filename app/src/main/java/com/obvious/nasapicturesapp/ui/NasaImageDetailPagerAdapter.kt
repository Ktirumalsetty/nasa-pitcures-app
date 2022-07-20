package com.obvious.nasapicturesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.obvious.nasapicturesapp.databinding.ItemPictureDetailsBinding
import com.obvious.nasapicturesapp.databinding.NasaImageListItemBinding
import com.obvious.nasapicturesapp.model.Nasa
import com.obvious.nasapicturesapp.utils.loadFromUrl

class NasaImageDetailPagerAdapter(val onItemClick : ((Nasa) -> Unit?)? = null): ListAdapter<Nasa, NasaImageDetailPagerAdapter.NasaImagesVH>(NasaImageDiffUtil()) {

    inner class NasaImagesVH(private val binding:ItemPictureDetailsBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:Nasa){
            binding.apply {
                detailPhoto.loadFromUrl(item.url,binding.root.context)
                titleText.text = item.title
                dateText.text = item.date
                explanationText.text = item.explanation

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaImagesVH {

        return NasaImagesVH(ItemPictureDetailsBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: NasaImagesVH, position: Int) {
        holder.bind(getItem(position))
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