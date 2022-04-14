package com.asafin24.data.api

import com.asafin24.domain.models.Article
import com.asafin24.domain.models.NewsModel
import com.asafin24.domain.repository.ApiInterface
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    val api = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    suspend fun getDataService(category: String) : Response<NewsModel> {
        return api.getNews(category)
    }

}

