package com.didemeren.coffeeshopretrofitrx.data.api


import com.didemeren.coffeeshopretrofitrx.model.Order
import com.didemeren.coffeeshopretrofitrx.model.OrderDTO
import com.didemeren.coffeeshopretrofitrx.model.OrderResponse
import com.didemeren.coffeeshopretrofitrx.model.OrdersResponse
import com.didemeren.coffeeshopretrofitrx.util.Constant
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


interface ApiService {

    //GET, DELETE, POST, PUT

    @Headers("User-Agent: Retrofit-Sample-App")
    @GET(Constant.ORDERS_GET_ALL)
    fun getOrders(@Header("Authorization") authorization: String)
            : Observable<OrdersResponse>

    @POST(Constant.ORDERS)
    fun createOrder(@Header("Authorization") authorization: String, @Body order: OrderDTO)
            : Single<OrderResponse>

    @DELETE(Constant.ORDERS_BY_ID)
    fun deleteOrder(@Path("id") id: String)
            : Single<OrdersResponse>

    @PUT(Constant.ORDERS)
    fun updateOrder(@Body order: Order)
            : Single<OrderResponse>
}