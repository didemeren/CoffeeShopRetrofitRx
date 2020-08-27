package com.didemeren.coffeeshopretrofitrx.ui.shop

import android.graphics.Color
import android.graphics.Color.RED
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.didemeren.coffeeshopretrofitrx.data.api.RepoProvider
import com.didemeren.coffeeshopretrofitrx.model.Order
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class ShopViewModel : ViewModel() {

    private val repo =  RepoProvider.provideRepository()
    private val disposable = CompositeDisposable()

    private val _title = MutableLiveData<String>().apply {
        value = "Welcome Barista!"
    }
    val title: LiveData<String> = _title

    private val _orderList = MutableLiveData<ArrayList<Order>>()
    val orderList: LiveData<ArrayList<Order>> = _orderList

    fun getOrders(){
        disposable.add(
            repo.getOrders()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { result ->
                        _orderList.value = result.orders as ArrayList<Order>
                    },
                    { error -> Log.e("getOrders", error.message!!) }
                )
        )
    }

    fun onReadyOrder(position: Int) {
        val item = _orderList.value?.get(position)
        item?.let {
            item.status = 1
            disposable.add(
                repo.updateOrder(item)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe (
                        { result ->
                            _orderList.value?.set(position, result.order)
                            _orderList.value = _orderList.value
                        },
                        { error -> Log.e("onReadyOrder", error.message!!) }
                    )
            )
        }
    }

    fun onDeleteOrder(position: Int) {
        val item = _orderList.value?.get(position)
        item?.let {
            item.status = 1
            disposable.add(
                repo.deleteOrder(item.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe (
                        { _ ->
                            _orderList.value?.removeAt(position)
                            _orderList.value = _orderList.value
                        },
                        { error -> Log.e("onReadyOrder", error.message!!) }
                    )
            )
        }
    }

    val listener = object : RecyclerViewClickListener {
        override fun onClick(position: Int) {
            onReadyOrder(position)
        }
    }

    val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT,
            ItemTouchHelper.LEFT
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            var position = viewHolder.adapterPosition
            onDeleteOrder(position)
        }
    }



}