package com.task.di

import com.squareup.moshi.Moshi
import com.task.BASE_URL
import com.task.BuildConfig
import com.task.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import com.task.data.remote.moshiFactories.MyStandardJsonAdapters
import com.task.data.remote.service.RecipesService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


private const val timeoutRead = 30   //In seconds
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val timeoutConnect = 30   //In seconds

val NetworkModule = module {

    fun provideHeaderInterceptor() = Interceptor { chain ->
        chain.proceed(chain.request().newBuilder()
                .header(contentType, contentTypeValue)
                .method(chain.request().method, chain.request().body)
                .build())
    }

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }

    fun provideHttpClient(
            logger: HttpLoggingInterceptor,
            headerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(headerInterceptor)
                .connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
                .readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
                .build()
    }

    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(MyKotlinJsonAdapterFactory())
                .add(MyStandardJsonAdapters.FACTORY)
                .build()
    }

    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()
    }

    fun  provideRecipesService(retrofit: Retrofit): RecipesService {
        return retrofit.create(RecipesService::class.java)
    }

    /*Koin is smart enough to guess the type,
     so just call get() after making sure that this type is provided*/

    single { provideHeaderInterceptor() }
    single { provideLoggingInterceptor() }
    single { provideHttpClient(get(),get()) }
    single { provideMoshi() }
    single { provideRetrofit(get(),get()) }

    // for dynamic generation
    single { ServiceGenerator(get()) }

}

/**
 * @property retrofit Retrofit injected using KOIN
 * @constructor for generating dynamic services
 */
class ServiceGenerator(private val retrofit: Retrofit) {
    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}
