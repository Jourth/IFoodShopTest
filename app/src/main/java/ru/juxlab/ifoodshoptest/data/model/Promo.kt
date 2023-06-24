package ru.juxlab.ifoodshoptest.data.model

import ru.juxlab.ifoodshoptest.R

data class Promo (val imageId : Int) {
    companion object {
        fun getPromos():List<Promo>{
            return listOf(
                Promo(R.drawable.promo_banner_1),
                Promo(R.drawable.promo_banner_1)
            )
        }
    }
}