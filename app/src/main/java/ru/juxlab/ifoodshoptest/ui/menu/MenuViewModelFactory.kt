package ru.juxlab.ifoodshoptest.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.juxlab.ifoodshoptest.data.repository.IFoodShopRepository

class MenuViewModelFactory (private val repository: IFoodShopRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MenuViewModel(repository) as T
    }
}