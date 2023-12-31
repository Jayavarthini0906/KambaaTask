package com.kambaa.mytaskjayavarthini

data class ProductDataItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String,
    var isChecked: Boolean
)