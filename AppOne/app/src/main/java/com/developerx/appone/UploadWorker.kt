package com.developerx.appone

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class UploadWorker(context: Context, params: WorkerParameters): Worker(context, params) {
    companion object {
        val WORKER_KEY = "workey_key"
    }

    override fun doWork(): Result {
        var input = inputData.getInt(MainActivity.KEY_COUNT_VALUE, 0)
        try {
            for (i in 1 until input) {
                Log.i("Works", "uploading $i")
            }

            val time = SimpleDateFormat("dd/MM/yy hh:mm:ss")
            val currentDate = time.format(Date())

            val data = Data.Builder().putString(WORKER_KEY, currentDate).build()

            return Result.success(data)
        } catch(e: Exception) {
            return Result.failure()
        }
    }
}