package com.example.assessmentnewstest.models

data class FilterCategories(
    var id: Int,
    var category: String,
    var isSelected : Boolean = false
) {

}