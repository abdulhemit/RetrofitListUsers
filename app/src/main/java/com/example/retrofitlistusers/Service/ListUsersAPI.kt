package com.example.retrofitlistusers.Service

import com.example.retrofitlistusers.Model.UserList
import io.reactivex.Observable
import retrofit2.http.GET

interface ListUsersAPI {

    @GET("/api/users?page=2")
    fun getData():Observable<UserList>
}