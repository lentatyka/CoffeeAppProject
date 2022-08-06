package com.example.coffeeapp.presentation.main.screens.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.R
import com.example.coffeeapp.databinding.ItemShopsBinding
import com.example.coffeeapp.domain.main.shop.model.Shop

class ShopLocationAdapter(
    private val callback: (Long)->Unit
):ListAdapter<Shop, ShopLocationAdapter.ShopLocationViewHolder>(DiffCallback) {

    class ShopLocationViewHolder(
        private val  binding: ItemShopsBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(item: Shop){
            binding.location = item
        }

        fun updateDistance(distance: Int){
           binding.distance = distance
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

    override fun onBindViewHolder(
        holder: ShopLocationViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isEmpty())
        super.onBindViewHolder(holder, position, payloads)
        else{
            holder.updateDistance(payloads[0] as Int)
        }
    }

    override fun onBindViewHolder(holder: ShopLocationViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            callback(item.id)
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Shop>(){
            override fun areItemsTheSame(oldItem: Shop, newItem: Shop): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Shop, newItem: Shop): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: Shop, newItem: Shop): Any? {
                return if(oldItem.distance != newItem.distance) newItem.distance else null
            }
        }
    }
}