package com.didemeren.coffeeshopretrofitrx.data.api

import android.os.Build
import com.didemeren.coffeeshopretrofitrx.model.Order
import com.didemeren.coffeeshopretrofitrx.model.OrderDTO
import com.didemeren.coffeeshopretrofitrx.model.OrderResponse
import com.didemeren.coffeeshopretrofitrx.model.OrdersResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class Repo(private val apiService: ApiService) {
    fun getOrders(): Observable<OrdersResponse> {
        return apiService.getOrders(authorization = Build.ID)
    }

    fun createOrder(order: OrderDTO): Single<OrderResponse> {
        return apiService.createOrder(authorization = Build.ID, order = order)
    }

    fun deleteOrder(id: String): Single<OrdersResponse> {
        return apiService.deleteOrder(id = id)
    }

    fun updateOrder(order: Order): Single<OrderResponse> {
        return apiService.updateOrder(order = order)
    }
}