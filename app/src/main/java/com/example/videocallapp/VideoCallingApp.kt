package com.example.videocallapp

import android.app.Application
import com.example.videocallapp.di.appModule
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.UserType
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VideoCallingApp: Application() {
    private var currentName: String? = null
    var client: StreamVideo?  = null

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@VideoCallingApp)
            modules(appModule)
        }
    }

    fun initVideoClient(username: String){
        if (client = null || username != currentName){
            StreamVideo.removeClient()
            currentName = username

            client = StreamVideoBuilder(
                context = this,
                apiKey = "",
                    user = User(
                    id = username,
                    name = username,
                    type = UserType.Guest
                )
            ).build()
        }
    }
}