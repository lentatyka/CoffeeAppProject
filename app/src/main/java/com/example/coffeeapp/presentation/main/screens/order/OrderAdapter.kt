package com.example.coffeeapp.presentation.main.screens.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.R
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.databinding.ItemOrderBinding

class OrderAdapter(
    private val list: ArrayList<MenuItem>,
    private val callback:(Int, Boolean) -> Boolean
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(
        private val binding: ItemOrderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MenuItem){
            binding.menu = item
            binding.amount = item.amount
            binding.orderAddIb.setOnClickListener {
                if((callback(item.id, true)))
                    binding.amount = item.amount
            }
            binding.orderRemoveIb.setOnClickListener {
                if((callback(item.id, false)))
                    binding.amount = item.amount
            }
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

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size
}