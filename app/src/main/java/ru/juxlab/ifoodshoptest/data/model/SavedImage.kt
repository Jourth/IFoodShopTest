package ru.juxlab.ifoodshoptest.data.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("images")
class SavedImage (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var bitmap: Bitmap? = null
)