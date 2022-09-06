package com.example.retrofitlistusers.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitlistusers.Adapter.RecyclerAdapter
import com.example.retrofitlistusers.Model.UserList
import com.example.retrofitlistusers.R
import com.example.retrofitlistusers.Service.ListUsersAPI
import com.example.retrofitlistusers.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var list: UserList
    val BASE_URL= "https://reqres.in"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        compositeDisposable = CompositeDisposable()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ListUsersAPI::class.java)

        compositeDisposable.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handlerResponse)
        )
    }

    private fun handlerResponse(listuser:UserList){
        list = listuser
        list.let {
            recyclerAdapter = RecyclerAdapter(it)
            binding.recyclerview.adapter = recyclerAdapter
        }
    }
}