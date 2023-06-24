package ru.juxlab.ifoodshoptest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.juxlab.ifoodshoptest.data.model.SavedImage
import ru.juxlab.ifoodshoptest.data.model.Product

@Database(
    version = 1,

    entities = [
        Product::class,
        SavedImage::class,
    ]

)
@TypeConverters(Converters::class)
abstract class IFoodShopDatabase : RoomDatabase() {

    abstract fun iFoodShopDao(): IFoodShopDao

    companion object{
        @Volatile private var instance: IFoodShopDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also{ instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, IFoodShopDatabase::class.java, "hmm.db").build()
    }
}