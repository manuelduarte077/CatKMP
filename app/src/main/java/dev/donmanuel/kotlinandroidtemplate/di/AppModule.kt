package dev.donmanuel.kotlinandroidtemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import dev.donmanuel.kotlinandroidtemplate.data.local.BeerDatabase
import dev.donmanuel.kotlinandroidtemplate.data.local.BeerEntity
import dev.donmanuel.kotlinandroidtemplate.data.remote.BeerApi
import dev.donmanuel.kotlinandroidtemplate.data.remote.BeerRemoteMediator

/**
 * AppModule es un modulo de Dagger Hilt que provee dependencias
 * a traves de la aplicacion
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /** Proveedor de la base de datos de cervezas */
    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): BeerDatabase {
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            "beers.db"
        ).build()
    }

    /** Proveedor de la API de cervezas */
    @Provides
    @Singleton
    fun provideBeerApi(): BeerApi {
        return Retrofit.Builder()
            .baseUrl(BeerApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    /**
     * Proveedor del paginador de cervezas
     * @param beerDb Base de datos de cervezas
     * @param beerApi API de cervezas
     * @return Paginador de cervezas
     */
    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(beerDb: BeerDatabase, beerApi: BeerApi): Pager<Int, BeerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                beerDb = beerDb,
                beerApi = beerApi
            ),
            pagingSourceFactory = {
                beerDb.dao.pagingSource()
            }
        )
    }
}