package mx.openpay.challenge.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.openpay.challenge.BuildConfig
import mx.openpay.challenge.data.ChallengeConstant
import mx.openpay.challenge.data.network.ApiClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ChallengeConstant.BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val urlWithKey = chain.request().url.newBuilder()
                        .addQueryParameter(
                            ChallengeConstant.API_KEY_NAME,
                            ChallengeConstant.API_KEY_VALUE
                        )
                        .build()
                    val requestWithKey = chain.request().newBuilder()
                        .url(urlWithKey)
                        .build()
                    chain.proceed(requestWithKey)
                }
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit): ApiClient =
        retrofit.create(ApiClient::class.java)
}
