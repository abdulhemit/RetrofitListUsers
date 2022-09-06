package com.example.retrofitlistusers.Model

data class UserList (
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<data>,
    val support: support
)
data class data(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)
data class support(
    val url: String,
    val text: String
)