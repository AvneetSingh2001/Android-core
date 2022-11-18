package com.developerx.roomdemo.db

class SubscriberRepository(private val dao: SubscriberDAO) {

    var subscribers = dao.getAllSubscriber()

    suspend fun insert(subscriber: subscriber): Long {
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: subscriber): Int{
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: subscriber): Int {
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }


}