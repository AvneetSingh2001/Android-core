package com.developerx.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber_data_table")
data class subscriber(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_id")
    val id: Int,

    @ColumnInfo(name = "subscriber_name")
    var name: String,

    //subscriber_email in migration 2 , now subscriber_mail in version 3
    @ColumnInfo(name = "subscriber_mail")
    var email: String,


    //migration 2
    @ColumnInfo(name= "subscriber_contact", defaultValue = "No Contact")
    var contact: String
) {

}