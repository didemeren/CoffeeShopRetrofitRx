package com.didemeren.coffeeshopretrofitrx.model

import com.google.gson.annotations.SerializedName

data class OrdersResponse (

    val status : Int,
    val message : String,
    val success : Boolean,
    @SerializedName("data") val orders : List<Order>
)