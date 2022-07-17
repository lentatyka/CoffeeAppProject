package com.example.coffeeapp.presentation.main.screens.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.R
import com.example.coffeeapp.data.main.menu.model.ShopMenu
import com.example.coffeeapp.databinding.ItemMenuBinding

class MenuAdapter(
    private val callback: (Int, Boolean) -> Boolean
) : ListAdapter<ShopMenu, MenuAdapter.MenuViewHolder>(DiffCallback) {

    inner class MenuViewHolder(
        private val binding: ItemMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShopMenu) {
            binding.menu = item
            binding.amount = item.amount
            binding.menuAddIb.setOnClickListener {
                if((callback(item.id, true)))
                    binding.amount = item.amount
            }
            binding.menuRemoveIb.setOnClickListener {
                if((callback(item.id, false)))
                    binding.amount = item.amount
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = DataBindingUtil.inflate<ItemMenuBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_menu,
            parent,
            false
        )
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<ShopMenu>() {
            override fun areItemsTheSame(oldItem: ShopMenu, newItem: ShopMenu): Boolean {
                return oldItem.amount == newItem.amount
            }

            override fun areContentsTheSame(oldItem: ShopMenu, newItem: ShopMenu): Boolean {
                return oldItem == newItem
            }
        }
    }
}