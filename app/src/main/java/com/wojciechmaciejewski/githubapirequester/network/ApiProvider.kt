package com.wojciechmaciejewski.githubapirequester.network


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


interface ApiProvider<T> {
    companion object{
        private val BASE_URL ="https://api.github.com"
    }
    fun provideApi(): T {
        return getApiService().create(getClassOfT())
    }



    fun getClassOfT(): Class<T>

    private fun getApiService(): Retrofit {
        val client = OkHttpClient.Builder();

        val logging = HttpLoggingInterceptor();
        logging.level = HttpLoggingInterceptor.Level.BODY;
        client.addInterceptor ( HeaderInterceptorToken())
        client.addInterceptor (logging)

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client.build())
                .build();
    }


    class HeaderInterceptorToken() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val newReq =  request.newBuilder()
                        .addHeader(Values.CONTENT_HEADER, Values.acceptType)
                        .addHeader(Values.ACCEPT_HEADER, Values.acceptTypeRecive)
                        .build()

            return chain.proceed(newReq)
        }
    }

     object Values {
        val CONTENT_HEADER = "Content-Type"
        val ACCEPT_HEADER = "Accept"
        val acceptTypeRecive = "application/json"
        val acceptType = "application/vnd.github.v3+json"
    }
}


