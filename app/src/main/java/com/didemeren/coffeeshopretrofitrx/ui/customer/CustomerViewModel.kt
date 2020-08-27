package com.didemeren.coffeeshopretrofitrx.ui.customer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.didemeren.coffeeshopretrofitrx.data.api.RepoProvider
import com.didemeren.coffeeshopretrofitrx.model.Order
import com.didemeren.coffeeshopretrofitrx.model.OrderDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.math.floor

class CustomerViewModel : ViewModel() {


    private val repo =  RepoProvider.provideRepository()
    private val disposable = CompositeDisposable()

    private val _title = MutableLiveData<String>().apply {
        value = "Welcome Customer!"
    }
    val title: LiveData<String> = _title

    private val _luckyCoffee = MutableLiveData<String>().apply {
        value = "Americano"
    }
    val luckyCoffee: LiveData<String> = _luckyCoffee


    private val _orderList = MutableLiveData<ArrayList<Order>>()
    val orderList: LiveData<ArrayList<Order>> = _orderList

    fun getOrders(){
        disposable.add(
            repo.getOrders()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { result ->
                        _orderList.value = result.orders as ArrayList<Order> },
                    { error -> Log.e("getOrders", error.message!!) }
                )
        )
    }

    fun onGiveOrder() {
        disposable.add(
            repo.createOrder(OrderDTO(0, _luckyCoffee.value!!))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { result ->
                        _orderList.value?.add(result.order)
                        _orderList.value = _orderList.value

                        val i: Int = floor(Math.random() * 4).toInt()
                        _luckyCoffee.value = _coffeeList[i]
                    },
                    { error -> Log.e("onGiveOrder", error.message!!) }
                )
        )

    }

    private var _coffeeList = mutableListOf(
        "Americao",
        "Espresso",
        "Macchiato",
        "Cappucino"
    )


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}

