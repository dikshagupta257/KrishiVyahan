package com.codingblocksmodules.agrome.util

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

//setting up connection interceptor to check if user is connected to network or internet
class NoConnectionInterceptor  : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!WifiService.instance.isConnectionOn()) {
            //if user is not connected to wifi throw the respective error
            throw NoConnectivityException()

        } else if(!WifiService.instance.isInternetAvailable()) {
            //if user is not connected to internet throw the respective error
            throw NoInternetException()

        } else {
            //if user is connected to network and internet then proceed further to make the network call with the help of client
            chain.proceed(chain.request())
        }
    }


    //Custom exception class if user is not connected to network
    class NoConnectivityException : IOException() {
        override val message: String
            get() = "No network available, please check your WiFi or Data connection"
    }

    //Custom exception class if user is not connected to internet
    class NoInternetException : IOException() {
        override val message: String
            get() = "No internet available, please check your connected WIFi or Data"
    }
}