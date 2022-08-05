package com.example.coffeeapp.presentation.main.screens.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.R
import com.example.coffeeapp.data.main.order.model.OrderItemDto
import com.example.coffeeapp.databinding.ItemOrderBinding

class OrderAdapter(
    private val addAmount: (Int, Int) -> Unit,
    private val subAmount: (Int, Int) -> Unit
) : ListAdapter<OrderItemDto, OrderAdapter.OrderViewHolder>(DiffCallback) {

    inner class OrderViewHolder(
        private val binding: ItemOrderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = getItem(position)
            binding.menu = item
            binding.amount = item.amount
            binding.orderAddIb.setOnClickListener {
                addAmount(item.id, getItem(position).amount)
            }
            binding.orderRemoveIb.setOnClickListener {
                subAmount(item.id, getItem(position).amount)
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

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) = holder.bind(position)


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
        val DiffCallback = object : DiffUtil.ItemCallback<OrderItemDto>() {
            override fun areItemsTheSame(
                oldItemDto: OrderItemDto,
                newItemDto: OrderItemDto
            ): Boolean {
                return oldItemDto.id == newItemDto.id
            }

            override fun areContentsTheSame(
                oldItemDto: OrderItemDto,
                newItemDto: OrderItemDto
            ): Boolean {
                return oldItemDto == newItemDto
            }

            override fun getChangePayload(
                oldItemDto: OrderItemDto,
                newItemDto: OrderItemDto
            ): Any? {
                return if (oldItemDto.amount != newItemDto.amount) newItemDto.amount else null
            }
        }
    }
}