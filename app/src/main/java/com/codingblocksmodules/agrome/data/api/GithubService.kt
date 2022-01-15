package com.codingblocksmodules.agrome.data.api

import com.codingblocksmodules.agrome.data.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//to make calls to various api endpoints
interface GithubService {
    @GET("/get_lab_details/")
    suspend fun getLabDetails(): Response<LabItem>

    @GET("/get_transport_details/")
    suspend fun getTransportDetails(): Response<TransportItem>

    @GET("/get_insuarance_details/")
    suspend fun getInsuranceDetails(): Response<InsuranceItem>

    @GET("/get_shop_details/")
    suspend fun getShopDetails(): Response<ShopItem>

    @GET("/get_seller_details/")
    suspend fun getSellerDetails(): Response<SellerItem>

    @FormUrlEncoded
    @POST("/crop_season/")
    fun getCropRecommendation(
        @Field("nitrogen") Nitrogen: Int?,
        @Field("phosphorus") Phosphorus: Int?,
        @Field("potassium") Potassium: Int?,
        @Field("temperature") Temperature: Float?,
        @Field("humidity") Humidity: Float?,
        @Field("ph") Ph: Float?,
        @Field("rainfall") Rainfall: Float?,
    ):Call<CropRecommendationResponse>

    @FormUrlEncoded
    @POST("/get_nutrition_list/")
    fun getNutrients(
        @Field("crop_name")Crop:String?
    ):Call<List<NutrientsItem>>
}