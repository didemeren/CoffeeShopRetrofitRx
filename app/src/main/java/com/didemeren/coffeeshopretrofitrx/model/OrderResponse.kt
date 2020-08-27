package com.didemeren.coffeeshopretrofitrx.model

import com.google.gson.annotations.SerializedName

data class OrderResponse (
    @SerializedName("data")
    var order: Order)