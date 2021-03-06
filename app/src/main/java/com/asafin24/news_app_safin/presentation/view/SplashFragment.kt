package com.asafin24.news_app_safin.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asafin24.news_app_safin.R
import com.asafin24.news_app_safin.databinding.FragmentSplashBinding
import com.asafin24.news_app_safin.presentation.APP
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            APP.navController.navigate(R.id.action_splashFragment_to_mainFragment)
        }

        return binding.root
    }

}