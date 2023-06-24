package ru.juxlab.ifoodshoptest

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import ru.juxlab.ifoodshoptest.data.db.IFoodShopDao
import ru.juxlab.ifoodshoptest.data.db.IFoodShopDatabase
import ru.juxlab.ifoodshoptest.data.network.ProductNetworkDataSource
import ru.juxlab.ifoodshoptest.data.repository.IFoodShopRepository
import ru.juxlab.ifoodshoptest.ui.menu.MenuViewModelFactory

class IShopApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy{
        import(androidXModule(this@IShopApplication))

        bind<IFoodShopDatabase>() with singleton { IFoodShopDatabase(instance()) }
        bind<IFoodShopDao>() with singleton { instance<IFoodShopDatabase>().iFoodShopDao() }
        bind<ProductNetworkDataSource>() with singleton { ProductNetworkDataSource() }
        bind<IFoodShopRepository>() with singleton { IFoodShopRepository(instance(), instance()) }
        bind() from provider { MenuViewModelFactory(instance()) }

    }

}