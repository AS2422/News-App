package com.asafin24.news_app_safin.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.asafin24.news_app_safin.R
import com.asafin24.news_app_safin.databinding.FragmentMainBinding
import com.asafin24.news_app_safin.presentation.adapters.CarouselAdapter
import com.asafin24.news_app_safin.presentation.adapters.CategoryAdapter
import com.asafin24.news_app_safin.presentation.adapters.NewsAdapter
import com.asafin24.news_app_safin.presentation.viewModel.MainFragmentViewModel
import kotlinx.android.synthetic.main.category_item.view.*


class MainFragment : Fragment(), CategoryAdapter.Listener {

    lateinit var binding: FragmentMainBinding
    private val carouselAdapter = CarouselAdapter()
    private val categoryAdapter = CategoryAdapter(this)
    private val newsAdapter = NewsAdapter()
    private var currentCategory = "General"
    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        init()

        //выход из приложения
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val fragmentExit = ExitFragment()
                fragmentExit.show(((activity as FragmentActivity).supportFragmentManager),"exitDialog")
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        //

        return binding.root
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        binding.carouselNews.adapter = carouselAdapter
        binding.rvCategoryNews.adapter = categoryAdapter
        binding.rvNews.adapter = newsAdapter

        getLiveData()

    }

    fun getLiveData() {
        viewModel.getNews(currentCategory)
        viewModel.data.observe(viewLifecycleOwner, Observer { list ->
            list.body().let {
                newsAdapter.setList(it!!.articles)
                carouselAdapter.setList(it.articles)
            }
        })
    }

    override fun onClickCategory(category: View) {
        category.tv_category_name.setTextColor(ContextCompat.getColor(requireContext(), R.color.categoryTextSelect))
        category.cv_category.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.categorySelect))

        var translateNameCategory = "general"

        when (category.tv_category_name.text.toString()) {
            "Общие" -> translateNameCategory = "general"
            "Наука" -> translateNameCategory = "science"
            "Спорт" -> translateNameCategory = "sports"
            "Авто" -> translateNameCategory = "business"
        }

        currentCategory = translateNameCategory
        getLiveData()
    }

    override fun unClickCategory(category: View) {
        category.tv_category_name.setTextColor(ContextCompat.getColor(requireContext(), R.color.categoryText))
        category.cv_category.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.background))
    }

}