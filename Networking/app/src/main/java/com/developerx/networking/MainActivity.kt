package com.developerx.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retServices: AlbumServices
    private lateinit var tvData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvData = findViewById<TextView>(R.id.tvData)

        retServices = RetrofitInstance.getRetrofitInstance().create(AlbumServices::class.java)

        //getRequestWithQueryParameter()
        updateAlbum()

    }

    private fun getRequestWithQueryParameter() {
        val responseLiveData: LiveData<Response<Album>> = liveData {
            val response = retServices.getSortedAlbums(3)
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if(albumList != null) {
                while(albumList.hasNext()) {
                    val albumItem = albumList.next()
                    val result = "Album Id: ${albumItem.id}" + "\n" +
                            "Album Title: ${albumItem.title}" + "\n" +
                            "User Id: ${albumItem.userId}" + "\n\n\n\n"

                    tvData.append(result)
                }
            }

        })
    }

    private fun updateAlbum() {
        val album = AlbumItem(0, "Album Item 101", 3)

        val postLiveData: LiveData<Response<AlbumItem>> = liveData {
            val response: Response<AlbumItem> = retServices.updateAlbum(album)
            emit(response)
        }

        postLiveData.observe(this, Observer {
            val albumItem: AlbumItem? = it.body()
            val result = "Album Id: ${albumItem?.id}" + "\n" +
                    "Album Title: ${albumItem?.title}" + "\n" +
                    "User Id: ${albumItem?.userId}" + "\n\n\n\n"
            tvData.append(result)
        })
    }
}