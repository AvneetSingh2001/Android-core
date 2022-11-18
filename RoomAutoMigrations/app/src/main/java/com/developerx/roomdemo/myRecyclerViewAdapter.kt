package com.developerx.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.developerx.roomdemo.databinding.ActivityMainBinding.inflate
import com.developerx.roomdemo.databinding.ItemListBinding
import com.developerx.roomdemo.db.subscriber

class myRecyclerViewAdapter(private val clickListener: (subscriber) -> Unit) : RecyclerView.Adapter<MyViewHolder>() {
    private var subscribers = ArrayList<subscriber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_list, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribers[position], clickListener)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }

    fun setList(subscribersList: List<subscriber>) {
        subscribers.clear()
        subscribers.addAll(subscribersList)
    }

}

class MyViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriber: subscriber, clickListener: (subscriber) -> Unit) {
        binding.tvName.text = subscriber.name
        binding.tvEmail.text = subscriber.email
        binding.subsDetails.setOnClickListener {
            clickListener(subscriber)
        }
    }
}