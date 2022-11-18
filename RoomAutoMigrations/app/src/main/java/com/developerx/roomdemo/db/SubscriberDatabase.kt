package com.developerx.roomdemo.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [subscriber::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = SubscriberDatabase.Migration2to3::class)
        ]
    )
abstract class SubscriberDatabase: RoomDatabase() {

    abstract val subscriberDAO: SubscriberDAO

    @RenameColumn(
        tableName = "subscriber_data_table",
        fromColumnName = "subscriber_email",
        toColumnName = "subscriber_mail"
    )
    class Migration2to3 : AutoMigrationSpec

    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase ?= null

        fun getInstance(context: Context) : SubscriberDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                        ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}