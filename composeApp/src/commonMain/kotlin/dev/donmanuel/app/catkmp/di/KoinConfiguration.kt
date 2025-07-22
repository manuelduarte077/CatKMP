package dev.donmanuel.app.catkmp.di

import dev.donmanuel.app.catkmp.targetModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            database,
            repositoryModule,
            useCaseModule,
            viewModelModule,
            targetModule
        )
    }
}