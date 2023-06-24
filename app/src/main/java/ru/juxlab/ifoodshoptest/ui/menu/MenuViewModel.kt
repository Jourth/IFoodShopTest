package ru.juxlab.ifoodshoptest.ui.menu

import androidx.lifecycle.ViewModel
import ru.juxlab.ifoodshoptest.data.model.ProductCategory
import ru.juxlab.ifoodshoptest.data.model.Promo
import ru.juxlab.ifoodshoptest.data.repository.IFoodShopRepository
import ru.juxlab.ifoodshoptest.utils.lazyDeffered

class MenuViewModel (private val repository: IFoodShopRepository): ViewModel() {

    val productList by lazyDeffered { repository.getProductList() }

    val categoryList = ProductCategory.getProductCategories()

    val promoList = Promo.getPromos()

    fun getSavedImage(id: Int) = repository.getSavedImage(id)

}