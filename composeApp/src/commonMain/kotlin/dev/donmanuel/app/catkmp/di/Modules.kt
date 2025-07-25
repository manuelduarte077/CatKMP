package dev.donmanuel.app.catkmp.di

import dev.donmanuel.app.catkmp.app_presentation.cat.CatViewModel
import dev.donmanuel.app.catkmp.app_presentation.login.LoginViewModel
import dev.donmanuel.app.catkmp.app_presentation.login.SignupViewModel
import dev.donmanuel.app.catkmp.data.local.LocalDatabase
import dev.donmanuel.app.catkmp.data.local.repository.FavoriteCatRepositoryData
import dev.donmanuel.app.catkmp.data.remote.repository.CatRepositoryData
import dev.donmanuel.app.catkmp.data.remote.repository.LoginRepositoryData
import dev.donmanuel.app.catkmp.data.remote.repository.SignupRepositoryData
import dev.donmanuel.app.catkmp.domain.repository.CatRepository
import dev.donmanuel.app.catkmp.domain.repository.FavoriteCatRepository
import dev.donmanuel.app.catkmp.domain.repository.LoginRepository
import dev.donmanuel.app.catkmp.domain.repository.SignupRepository
import dev.donmanuel.app.catkmp.domain.usecase.CatListUseCase
import dev.donmanuel.app.catkmp.domain.usecase.FavoriteCatUseCase
import dev.donmanuel.app.catkmp.domain.usecase.LoginUseCase
import dev.donmanuel.app.catkmp.domain.usecase.SignupUseCase
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::CatViewModel)
    viewModelOf(::SignupViewModel)
}

val useCaseModule = module {
    single { LoginUseCase(get()) }
    single { CatListUseCase(get()) }
    single { FavoriteCatUseCase(get()) }
    single { SignupUseCase(get()) }
}

val repositoryModule = module {
    single<LoginRepository> { LoginRepositoryData(get()) }
    single<CatRepository> { CatRepositoryData(get()) }
    single<FavoriteCatRepository> { FavoriteCatRepositoryData(get()) }
    single<SignupRepository> { SignupRepositoryData(get()) }
}

val database = module {
    single { LocalDatabase(get()) }
}
