package com.didemeren.coffeeshopretrofitrx.ui.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.didemeren.coffeeshopretrofitrx.R
import com.didemeren.coffeeshopretrofitrx.databinding.FragmentShopBinding


class ShopFragment : Fragment() {

    private lateinit var binding: FragmentShopBinding
    private lateinit var viewModel: ShopViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shop,container,false)
        viewModel = ViewModelProvider(this).get(ShopViewModel::class.java)
        binding.shopViewModel = viewModel
        viewModel.getOrders()

        val adapter = ShopOrderAdapter(viewModel.listener)
        val itemTouchHelper = ItemTouchHelper(viewModel.simpleItemTouchCallback)

        binding.shopOrderList.adapter = adapter
        itemTouchHelper.attachToRecyclerView(binding.shopOrderList)

        viewModel.orderList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}