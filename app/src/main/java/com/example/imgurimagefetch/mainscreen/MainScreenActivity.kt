package com.example.imgurimagefetch.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imgurimagefetch.R
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class MainScreenActivity : AppCompatActivity(), MainScreenContract.View {
    private val viewModel: MainScreenContract.ViewModel? by inject(MainScreenContract.ViewModel::class.java) { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getCatImages()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onError() {
        TODO("Not yet implemented")
    }
}