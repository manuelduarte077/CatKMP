package dev.donmanuel.app.catkmp

import android.app.Application
import dev.donmanuel.app.catkmp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class CatApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@CatApp)
        }
    }
}