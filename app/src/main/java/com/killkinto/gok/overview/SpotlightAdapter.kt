package com.killkinto.gok.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.killkinto.gok.data.model.Spotlight
import com.killkinto.gok.databinding.SpotlightItemBinding

class SpotlightAdapter :
    ListAdapter<Spotlight, SpotlightAdapter.SpotlightViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightViewHolder {
        return SpotlightViewHolder(
            SpotlightItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SpotlightViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SpotlightViewHolder(private var binding: SpotlightItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(spotlight: Spotlight) {
            binding.spotlight = spotlight
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Spotlight>() {
        override fun areItemsTheSame(oldItem: Spotlight, newItem: Spotlight) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Spotlight, newItem: Spotlight) =
            oldItem == newItem

    }
}