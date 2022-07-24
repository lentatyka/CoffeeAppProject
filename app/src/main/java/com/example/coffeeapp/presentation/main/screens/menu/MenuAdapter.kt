package com.example.coffeeapp.presentation.main.screens.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.R
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.databinding.ItemMenuBinding

class MenuAdapter(
    private val callback: (Int, Boolean) -> Unit
) : ListAdapter<MenuItem, MenuAdapter.MenuViewHolder>(DiffCallback) {

    inner class MenuViewHolder(
        private val binding: ItemMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MenuItem) {
            binding.menu = item
            binding.amount = item.amount
            binding.menuAddIb.setOnClickListener {
                callback(item.id, true)
            }
            binding.menuRemoveIb.setOnClickListener {
                callback(item.id, false)
            }
        }

        fun updateAmount(amount: Int) {
            binding.amount = amount
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

    override fun onBindViewHolder(
        holder: MenuViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else
            holder.updateAmount(payloads[0] as Int)
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<MenuItem>() {
            override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: MenuItem, newItem: MenuItem): Any? {
                return if (oldItem.amount != newItem.amount) newItem.amount else null
            }
        }
    }
}