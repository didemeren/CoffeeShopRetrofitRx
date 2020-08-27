package com.didemeren.coffeeshopretrofitrx.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

class CustomBindings{
    companion object {

        @BindingAdapter("setCustomerStatus")
        @JvmStatic
        fun bindCustomerOrderStatus(view: TextView, status: Int) {
            view.text = if(status == 0) "Waiting to be Prepared" else "Ready";
        }

        @BindingAdapter("setShopStatus")
        @JvmStatic
        fun bindShopOrderStatus(view: TextView, status: Int) {
            view.text = if(status == 0) "Click to Prepare" else "Prepared";
        }

    }
}