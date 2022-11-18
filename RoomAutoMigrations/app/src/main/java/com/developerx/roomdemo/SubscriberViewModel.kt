package com.developerx.roomdemo

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerx.roomdemo.db.SubscriberRepository
import com.developerx.roomdemo.db.subscriber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

class SubscriberViewModel(val repository: SubscriberRepository): ViewModel() {

    val subscribers = repository.subscribers

    private var isUpdateOrDelete = false
    private lateinit var updateOrDeleteSubscriber: subscriber

    private var statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
            get() = statusMessage

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val inputContact = MutableLiveData<String>()

    val createOrUpdateBtnTxt = MutableLiveData<String>()
    val clearOrDeleteBtnTxt = MutableLiveData<String>()

    init {
        createOrUpdateBtnTxt.value = "Save"
        clearOrDeleteBtnTxt.value = "Clear"
    }


    fun createOrUpdate() {

        if(inputName.value.isNullOrBlank()) {
            statusMessage.value = Event("Enter Name!")
        }else if(inputEmail.value.isNullOrBlank()) {
            statusMessage.value = Event("Enter Email!")
        }else if(inputContact.value.isNullOrBlank()) {
            statusMessage.value = Event("Enter Email!")
        } else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = Event("Enter Valid Email!")
        } else {
            if (isUpdateOrDelete) {
                updateOrDeleteSubscriber.name = inputName.value!!
                updateOrDeleteSubscriber.email = inputEmail.value!!
                updateOrDeleteSubscriber.contact = inputContact.value!!
                update(updateOrDeleteSubscriber)
            } else {
                val name = inputName.value!!
                val email = inputEmail.value!!
                val contact = inputContact.value!!
                insert(subscriber(0, name, email, contact))
                inputName.value = ""
                inputEmail.value = ""
                inputContact.value = ""
            }
        }
    }

    fun clearOrDelete() {
        if(isUpdateOrDelete) {
            delete(updateOrDeleteSubscriber)
        }else {
            deleteAll()
        }

    }


    fun insert(subscriber: subscriber) = viewModelScope.launch(Dispatchers.IO) {
            val isInserted = repository.insert(subscriber)
        withContext(Dispatchers.Main) {
            if(isInserted > -1) {
                statusMessage.value = Event("Subscriber inserted at ${isInserted}")
            }else {
                statusMessage.value = Event("Error Occurred!!")
            }
        }
        }

    fun update(subscriber: subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val isUpdated = repository.update(subscriber)
        withContext(Dispatchers.Main) {
            if(isUpdated > 0) {
                inputName.value = ""
                inputEmail.value = ""
                inputContact.value = ""
                isUpdateOrDelete = false
                createOrUpdateBtnTxt.value = "Save"
                clearOrDeleteBtnTxt.value = "Clear"
                statusMessage.value = Event("${isUpdated} Subscriber updated")
            }else {
                statusMessage.value = Event("Error Occurred!!")
            }

        }

    }

    fun delete(subscriber: subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val deletedCount = repository.delete(subscriber)
        withContext(Dispatchers.Main) {
            if(deletedCount > 0) {
                inputName.value = ""
                inputEmail.value = ""
                inputContact.value = ""
                isUpdateOrDelete = false
                createOrUpdateBtnTxt.value = "Save"
                clearOrDeleteBtnTxt.value = "Clear"

                statusMessage.value = Event("${deletedCount} Subscriber deleted")
            }else {
                statusMessage.value = Event("Error Occurred!!")
            }
        }
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        val deletedCount =repository.deleteAll()
        withContext(Dispatchers.Main) {
            if(deletedCount > 0){
                statusMessage.value = Event("${deletedCount} Subscriber deleted")
            }else {
                statusMessage.value = Event("Error Occurred!!")
        }
        }
    }

    
    fun initUpdateOrDelete(subscriber: subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        inputContact.value = subscriber.contact
        isUpdateOrDelete = true
        updateOrDeleteSubscriber = subscriber
        createOrUpdateBtnTxt.value = "Update"
        clearOrDeleteBtnTxt.value = "Delete"
    }
}