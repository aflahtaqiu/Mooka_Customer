package com.example.mooka_customer.network.model

import com.example.mooka_customer.network.lib.DataResponse

data class ListResponse<T>(val items: List<T>) : DataResponse<List<T>> {
    override fun retrieveData(): List<T> = items
}