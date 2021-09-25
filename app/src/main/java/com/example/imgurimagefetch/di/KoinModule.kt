package com.example.imgurimagefetch.di

import com.example.imgurimagefetch.di.modules.applicationModule
import com.example.imgurimagefetch.di.modules.networkModule

val koinModule = listOf(
    applicationModule,
    networkModule
)