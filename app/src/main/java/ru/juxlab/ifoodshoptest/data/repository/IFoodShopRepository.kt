package ru.juxlab.ifoodshoptest.data.repository

import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.juxlab.ifoodshoptest.data.db.IFoodShopDao
import ru.juxlab.ifoodshoptest.data.model.Product
import ru.juxlab.ifoodshoptest.data.model.ProductList
import ru.juxlab.ifoodshoptest.data.model.SavedImage
import ru.juxlab.ifoodshoptest.data.network.ProductNetworkDataSource
import java.net.URL

class IFoodShopRepository (
    private val iFoodShopDao: IFoodShopDao,
    private val productNetworkDataSource: ProductNetworkDataSource)
{
    init {
        productNetworkDataSource.apply {
            productList.observeForever { newProductList ->
                persistFetchedProductList(newProductList)
            }
        }
    }

    suspend fun getProductList(): LiveData<out List<Product>> {
        return withContext(Dispatchers.IO) {
            productNetworkDataSource.fetchProductList()
            return@withContext iFoodShopDao.getProductList()
           }
    }

    fun getSavedImage(id: Int) = iFoodShopDao.readSavedImage(id)

    private fun persistFetchedProductList(productList: ProductList) {

        GlobalScope.launch(Dispatchers.IO) {
            iFoodShopDao.cleanProductList()
            iFoodShopDao.cleanSavedImages()
            productList.latest.forEach {
                val bitmap = BitmapFactory.decodeStream(URL(it.imageUrl).openStream())
                it.imageId = iFoodShopDao.addImage(SavedImage(bitmap = bitmap)).toInt()
            }
            iFoodShopDao.insertProductList(productList.latest)

        }
    }

    private suspend fun fetchProductList() {
        productNetworkDataSource.fetchProductList()
    }

}