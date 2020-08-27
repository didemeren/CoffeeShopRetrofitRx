package com.didemeren.coffeeshopretrofitrx.ui.customer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.didemeren.coffeeshopretrofitrx.R
import com.didemeren.coffeeshopretrofitrx.databinding.ListItemCustomerBinding
import com.didemeren.coffeeshopretrofitrx.model.Order

class CustomerOrderAdapter : RecyclerView.Adapter<CustomerOrderAdapter.ViewHolder>() {

    private var items: MutableList<Order>

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ListItemCustomerBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Order) {
            binding.order = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ListItemCustomerBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_customer,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun getItem(position: Int ): Order {
        return items[position]
    }

    fun submitList(items: MutableList<Order>) {
        this.items = items
        notifyDataSetChanged()
    }

    init {
        items = ArrayList()
    }

}