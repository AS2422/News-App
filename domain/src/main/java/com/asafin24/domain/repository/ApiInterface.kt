package com.asafin24.domain.repository

import com.asafin24.domain.models.Article
import com.asafin24.domain.models.NewsModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines?country=ru&language=ru&apiKey=c9b88c491bfd484aa0258912e96c1dab")

    suspend fun getNews(@Query("category") category: String) : Response<NewsModel>

}