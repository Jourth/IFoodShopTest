package ru.juxlab.ifoodshoptest.data.model

import ru.juxlab.ifoodshoptest.R

data class ProductCategory (val nameId : Int) {
    companion object {
        fun getProductCategories():List<ProductCategory>{
            return listOf(
                ProductCategory(R.string.category_pizza),
                ProductCategory(R.string.category_combo),
                ProductCategory(R.string.category_desserts),
                ProductCategory(R.string.category_drinks)
            )
        }
    }
}
