package com.example.mooka_customer.network

import com.example.mooka_customer.Config
import com.example.mooka_customer.network.lib.networkCall
import com.example.mooka_customer.network.model.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Repository {
    fun getAllUsers() = networkCall<ListResponse<User>, List<User>> {
        client = ManagemenApi.apiService.getAllUsers()
    }

    fun getAllUmkms() = networkCall<ListResponse<UMKM>, List<UMKM>> {
        client = ManagemenApi.apiService.getAllUmkms()
    }

    fun getAllProducts() = networkCall<ListResponse<Product>, List<Product>> {
        client = ManagemenApi.apiService.getAllProducts()
    }

    fun getProductDetail(id: Int) = networkCall<Product, Product> {
        client = ManagemenApi.apiService.getProductDetail(id)
    }

    fun getUmkmDetail(id: Int) = networkCall<UMKM, UMKM> {
        client = ManagemenApi.apiService.getUmkmDetail(id)
    }

    fun getAllCarts(userId: Int) = networkCall<ListResponse<Cart>, List<Cart>> {
        client = ManagemenApi.apiService.getAllCarts(userId)
    }

    fun postToCart(userId: Int, umkmId: Int, productId: Int) = networkCall<Cart, Cart> {
        client = ManagemenApi.apiService.addToCart(userId, umkmId, productId)
    }

    fun register (name:String, email:String, noTelfon:String, password:String) = networkCall<User, User> {
        client = ManagemenApi.apiService.register(name, noTelfon, password, email)
    }

    fun checkout(userId: String, pengrimanId: Int?, donasi:Int) = networkCall<ResponseCheckout, ResponseCheckout> {
        client = ManagemenApi.apiService.checkout(userId, pengrimanId, donasi)
    }

    fun getAllJenisPengiriman() = networkCall<ListResponse<JenisPengiriman>, List<JenisPengiriman>> {
        client = ManagemenApi.apiService.getAllJenisPengiriman()
    }

    fun getAllNotifications(userId: Int) = networkCall<ListResponse<Notification>, List<Notification>> {
        client = ManagemenApi.apiService.getAllNotifications(userId)
    }

    fun getUserDetail (userId: Int) = networkCall<User, User> {
        client = ManagemenApi.apiService.getUserDetail(userId)
    }

    fun cekPembayaran (custCode : String) = networkCall<ResponseCekPembayaran, ResponseCekPembayaran> {
        client=ManagemenApi.apiService.checkPembayaran(custCode)
    }

    fun buatPembayaran (custCode: String, nama: String, amount: Int) = networkCall<ResponseBuatPembayaran, ResponseBuatPembayaran> {
        client = ManagemenApi.apiService.buatPembayaran(custCode, nama, amount)
    }

    fun getAllTagihans(userId: Int) = networkCall<ListResponse<Tagihan>, List<Tagihan>> {
        client = ManagemenApi.apiService.getAllTagihans(userId)
    }
}


object ManagemenApi {
    var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var API_BASE_URL: String = Config.API_BASE_URL
    var httpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
    var builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
    var retrofit = builder
        .client(httpClient.build())
        .build()

    var apiService: ApiService = retrofit.create(ApiService::class.java)
    val PREFNAME = "mooka"
}
