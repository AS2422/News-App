package com.asafin24.news_app_safin.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.asafin24.data.api.ApiService
import com.asafin24.domain.models.NewsModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val apiService = ApiService()
    val data = MutableLiveData<Response<NewsModel>>()

    fun getNews(category: String) {
        viewModelScope.launch {
            data.value = apiService.getDataService(category)
        }
    }


}