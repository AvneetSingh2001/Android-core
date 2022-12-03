package com.developerx.appone

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class filteringWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        try {


            for (i in 1 until 10000) {
                Log.e("filter", "Filetring $i")
            }

            return Result.success()
        }catch (e: Exception) {
            return Result.failure()
        }
    }

}