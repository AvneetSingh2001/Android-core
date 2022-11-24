package com.developerx.networking

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AlbumServices {

    @GET("/albums")
    suspend fun getAlbums(): Response<Album>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId:Int): Response<Album>

    @POST("/albums")
    suspend fun updateAlbum(@Body album: AlbumItem): Response<AlbumItem>
}