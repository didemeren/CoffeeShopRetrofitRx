package com.didemeren.coffeeshopretrofitrx.model

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("_id")
    var id: String,
    @SerializedName("status")
    var status: Int,
    @SerializedName("name")
    var name: String
)