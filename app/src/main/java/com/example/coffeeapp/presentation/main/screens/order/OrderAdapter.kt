package com.example.coffeeapp.presentation.main.screens.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.R
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.databinding.ItemOrderBinding

class OrderAdapter(
    private val callback: (Int, Boolean) -> Unit
) : ListAdapter<MenuItem, OrderAdapter.OrderViewHolder>(DiffCallback) {

    inner class OrderViewHolder(
        private val binding: ItemOrderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MenuItem) {
            binding.menu = item
            binding.amount = item.amount
            binding.orderAddIb.setOnClickListener {
                callback(item.id, true)
            }
            binding.orderRemoveIb.setOnClickListener {
                callback(item.id, false)
            }
        }

        fun updateAmount(amount: Int) {
            binding.amount = amount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = DataBindingUtil.inflate<ItemOrderBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_order,
            parent,
            false
        )
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onBindViewHolder(
        holder: OrderViewHolder,
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