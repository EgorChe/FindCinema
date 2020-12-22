package ru.course.findcinema.di

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import ru.course.findcinema.data.FavoritesDao
import ru.course.findcinema.data.FavoritesDaoImpl
import ru.course.findcinema.data.network.MoviesApi

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    fun provideMoviesApi(): MoviesApi = Retrofit.Builder()
        .baseUrl("https://kinopoiskapiunofficial.tech/")
        .client(OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder().run {
                header("X-API-KEY", "541251fa-c296-4fc4-af05-7ae52e3e6401")
                build()
            }
            chain.proceed(request)
        }.build())
        .addConverterFactory(Json(builderAction = {
            ignoreUnknownKeys = true
        }).asConverterFactory("application/json".toMediaType()))
        .build()
        .create()

    @Provides
    fun provideFavoritesDao(preferences: SharedPreferences): FavoritesDao =
        FavoritesDaoImpl(preferences)

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("data", Context.MODE_PRIVATE)
}

