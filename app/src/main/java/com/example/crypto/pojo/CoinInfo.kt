package com.example.crypto.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("Name")
    @Expose
    val name: String? = null,
    @SerializedName("FullName")
    @Expose
    val FullName: String? = null,
    @SerializedName("ImageUrl")
    @Expose
    val ImageUrl: String? = null
)