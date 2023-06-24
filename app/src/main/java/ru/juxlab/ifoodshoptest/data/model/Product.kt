package ru.juxlab.ifoodshoptest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("products")
data class Product (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String = "",
    @SerializedName("category")
    val description: String = "",
    val price: Float = 0.0F,

    @SerializedName("image_url")
    val imageUrl: String,
    var imageId: Int = 0

)