package br.com.ocean_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("search/repositories")
    fun buscarRepositorios( @Query("q") query: String): Call<GitHubResult>


}