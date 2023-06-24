package ru.juxlab.ifoodshoptest.data.db

import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import ru.juxlab.ifoodshoptest.data.model.Product
import ru.juxlab.ifoodshoptest.data.model.ProductList
import ru.juxlab.ifoodshoptest.data.model.SavedImage

@Dao
interface IFoodShopDao {


    //Products
    @Upsert
    fun updateProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductList(productList: List<Product>)

    @Query("select * from products where id = :id")
    fun readProduct(id: Int): LiveData<Product>

    @Query("select * from products order by id desc")
    fun getProductList(): LiveData<List<Product>>

    @Query("delete from products")
    fun cleanProductList()

    //Saved Images
    @Insert
    fun addImage(savedImage: SavedImage): Long

    fun readSavedImage(id: Int): SavedImage{
        val imgSize = getSavedImageSize(id).toInt()
        val output = ByteArray(imgSize.toInt())
        var pos = 1;
        while (pos < imgSize) {
            val chunk = readSavedImageChunk(pos, listOf(999999, imgSize-(pos-1)).min(), id)
            System.arraycopy(chunk, 0, output, pos-1, chunk.size)
            pos += 999999
        }
        return SavedImage(id, BitmapFactory.decodeByteArray(output, 0, output.size))
    }


    @Query("select substr(bitmap,:startPos,:endPos) from images where id = :id")
    fun readSavedImageChunk(startPos: Int, endPos: Int, id: Int): ByteArray

    @Query("select length(bitmap) from images where id = :id")
    fun getSavedImageSize(id: Int): Long

    @Query("delete from images")
    fun cleanSavedImages()
}