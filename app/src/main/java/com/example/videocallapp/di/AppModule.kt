package com.example.videocallapp.di

import com.example.videocallapp.VideoCallingApp
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    factory {
        val app = androidContext().applicationContext as  VideoCallingApp
        app.client
    }
    viewModeOf(::ConnectViewModel)
    viewModelOf(::VideoCallViewModel)

}