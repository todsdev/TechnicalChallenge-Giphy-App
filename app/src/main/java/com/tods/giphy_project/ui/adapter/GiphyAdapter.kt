package com.tods.giphy_project.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tods.giphy_project.data.model.giphy.GiphyModel
import com.tods.giphy_project.databinding.ItemGiphyBinding

class GiphyAdapter(private val context: Context): ListAdapter<GiphyModel, GiphyAdapter.GiphyViewHolder>(ItemDiff()) {

    inner class GiphyViewHolder(val binding: ItemGiphyBinding): RecyclerView.ViewHolder(binding.root)

    private val differ = AsyncListDiffer(this, ItemDiff())

    var giphies: List<GiphyModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        return GiphyViewHolder(
            ItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val giphy = giphies[position]
        holder.binding.apply {
            Glide.with(context)
                .load(giphy.images.downsized.url)
                .into(imageGiphy)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(giphy)
            }
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClickListener?.let {
                it(giphy)
            }
            true
        }
    }

    override fun getItemCount(): Int = giphies.size

    class ItemDiff : DiffUtil.ItemCallback<GiphyModel>() {
        override fun areItemsTheSame(oldItem: GiphyModel, newItem: GiphyModel): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.images.downsized.url == newItem.images.downsized.url &&
                    oldItem.images.original.url == newItem.images.original.url
        }

        override fun areContentsTheSame(oldItem: GiphyModel, newItem: GiphyModel): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.images.downsized.url == newItem.images.downsized.url &&
                    oldItem.images.original.url == newItem.images.original.url
        }
    }

    private var onItemClickListener: ((GiphyModel) -> Unit)? = null

    private var onItemLongClickListener: ((GiphyModel) -> Unit)? = null

    fun setOnClickListener(listener: (GiphyModel) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnLongClickListener(listener: (GiphyModel) -> Unit) {
        onItemLongClickListener = listener
    }
}