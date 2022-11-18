package com.developerx.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.developerx.roomdemo.databinding.ActivityMainBinding
import com.developerx.roomdemo.db.SubscriberDatabase
import com.developerx.roomdemo.db.SubscriberRepository
import com.developerx.roomdemo.db.subscriber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SubscriberViewModel
    private lateinit var adapter: myRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)

        val factory = SubscriberViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)

        binding.myViewModel = viewModel
        binding.lifecycleOwner = this

        initRecyclerView()


        viewModel.message.observe(this, Observer {
            it.getContentIfNotHandled() ?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvSubscribers.layoutManager = LinearLayoutManager(this)
        adapter = myRecyclerViewAdapter({selectedItem: subscriber -> subscriberClicked(selectedItem)})
        binding.rvSubscribers.adapter = adapter
        displaySubscriberList()
    }
    fun displaySubscriberList() {
        viewModel.subscribers.observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    fun subscriberClicked(subscriber: subscriber) {
        //Toast.makeText(this, "${subscriber.name}", Toast.LENGTH_LONG).show()
        viewModel.initUpdateOrDelete(subscriber)
    }
}