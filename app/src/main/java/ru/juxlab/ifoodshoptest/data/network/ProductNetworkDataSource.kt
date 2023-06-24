package ru.juxlab.ifoodshoptest.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.juxlab.ifoodshoptest.data.model.ProductList
import java.io.IOException

class ProductNetworkDataSource {

    private val _productList = MutableLiveData<ProductList>()
    val productList: LiveData<ProductList>
        get() = _productList

    suspend fun fetchProductList() {
        try {
            val fetchedProductList = APIGetProducts().getProducts().await()
            _productList.postValue(fetchedProductList)
        }
        catch (e: IOException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}