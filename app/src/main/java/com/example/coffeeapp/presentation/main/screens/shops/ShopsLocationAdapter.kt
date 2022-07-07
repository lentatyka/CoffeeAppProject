package com.example.coffeeapp.presentation.main.screens.shops

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.R
import com.example.coffeeapp.databinding.ItemShopsBinding
import com.example.coffeeapp.domain.main.shops.ShopLocation

class ShopsLocationAdapter(
    private val callback: (Long)->Unit
):ListAdapter<ShopLocation, ShopsLocationAdapter.ShopLocationViewHolder>(DiffCallback) {

    class ShopLocationViewHolder(
        private val  binding: ItemShopsBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(item: ShopLocation){
            binding.location = item
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopLocationViewHolder {
        val binding = DataBindingUtil.inflate<ItemShopsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_shops,
            parent,
            false
        )
        return ShopLocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopLocationViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            callback(item.id.toLong())
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<ShopLocation>(){
            override fun areItemsTheSame(oldItem: ShopLocation, newItem: ShopLocation): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ShopLocation, newItem: ShopLocation): Boolean {
                return oldItem == newItem
            }
        }
    }
}