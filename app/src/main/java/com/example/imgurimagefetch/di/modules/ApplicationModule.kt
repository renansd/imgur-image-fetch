package com.example.imgurimagefetch.di.modules

import com.example.imgurimagefetch.mainscreen.MainScreenActivity
import com.example.imgurimagefetch.mainscreen.MainScreenContract
import com.example.imgurimagefetch.mainscreen.MainScreenViewModel
import org.koin.dsl.module

val applicationModule = module {
    factory<MainScreenContract.ViewModel> { (view: MainScreenActivity?) -> MainScreenViewModel(view) }
}