package com.tanay.alliancelogin

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


internal object RetrofitHelper {
    private const val BASE_URL = "http://10.254.254.16:80/"
    private lateinit var httpClient: OkHttpClient
    private lateinit var retrofit: Retrofit
    fun getAllService() : ApiHelper {
            httpClient = createOkHttpClient()
            retrofit = createRetrofit()
            return retrofit.create(ApiHelper::class.java)
        }

    // 0 = login type, 1 = other type
    private fun createOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(3, TimeUnit.SECONDS)
        httpClient.connectTimeout(3, TimeUnit.SECONDS)
        httpClient.callTimeout(3, TimeUnit.SECONDS)
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return httpClient.build()
    }

    /**
     * Creates a pre configured Retrofit instance
     */
    private fun createRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(httpClient)
            .build()
    }


}