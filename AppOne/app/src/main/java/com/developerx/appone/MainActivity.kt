package com.developerx.appone

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {

    companion object {
        var KEY_COUNT_VALUE : String = "123"
    }

    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn = findViewById<Button>(R.id.uploadBtn)
        textView = findViewById<TextView>(R.id.tv)

        btn.setOnClickListener {
            oneTimeWorkRequest()
        }
    }

    private fun oneTimeWorkRequest() {
        var instance = WorkManager.getInstance(applicationContext)

        val constraints = Constraints
            .Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        var data = Data.Builder().putInt(KEY_COUNT_VALUE, 123).build()


        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java).setConstraints(constraints).setInputData(data).build()

        val downloadRequest = OneTimeWorkRequest.Builder(DownloadingWorker::class.java).build()
        val filteringRequest = OneTimeWorkRequest.Builder(filteringWorker::class.java).build()
        val compressRequest = OneTimeWorkRequest.Builder(CompressWorker::class.java).build()


        val parallelWork : MutableList<OneTimeWorkRequest> = mutableListOf<OneTimeWorkRequest>()
        parallelWork.add(downloadRequest)
        parallelWork.add(filteringRequest)

        instance
            .beginWith(parallelWork)
            .then(compressRequest)
            .then(uploadRequest)
            .enqueue()

        instance.getWorkInfoByIdLiveData(uploadRequest.id).observe(this, Observer {
            textView.text = it.state.name
            if(it.state.isFinished) {
                val data = it.outputData
                val message = data.getString(UploadWorker.WORKER_KEY)
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}