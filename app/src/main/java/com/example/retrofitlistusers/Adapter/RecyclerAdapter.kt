package com.example.retrofitlistusers.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlistusers.Model.UserList
import com.example.retrofitlistusers.databinding.RecyclerRowBinding
import com.squareup.picasso.Picasso

class RecyclerAdapter(val list: UserList): RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {
    class RecyclerHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        Picasso.get().load(list.data.get(position).avatar).into(holder.binding.image)
        holder.binding.email.setText(list.data.get(position).email)
        holder.binding.fistName.setText(list.data.get(position).first_name + " ")
        holder.binding.lastName.setText(list.data.get(position).last_name)
        holder.binding.supportText.setText(list.support.text)
    }

    override fun getItemCount(): Int {
        return list.data.size
    }
}