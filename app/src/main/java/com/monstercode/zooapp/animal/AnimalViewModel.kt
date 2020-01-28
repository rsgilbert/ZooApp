package com.monstercode.zooapp.animal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.monstercode.skyllaconnect.Request
import com.monstercode.zooapp.Utils
import com.monstercode.zooapp.room.AppDatabase
import com.monstercode.zooapp.room.Category
import org.jetbrains.anko.doAsync

class AnimalViewModel(application: Application) : AndroidViewModel(application) {
    val context = application.applicationContext
    val tag = "AnimalViewModel"
    val selectedAnimalCategoryLiveData = MutableLiveData<Category>()

    init {

        updateAnimals()
    }


    fun updateAnimals() {
        doAsync {
            try {
                val animalsResponse = Request.animalsCall()
                if (animalsResponse.isSuccessful) {
                    val animals = animalsResponse.body()
                    Utils.logd(context, animals.toString())
                    AppDatabase(context).animalDao().insertAll(animals)
                } else {
                    Utils.logd(context, "Failed to fetch animals: ${animalsResponse.code()}")
                    Utils.snack(context, "Failed to fetch animals: ${animalsResponse.code()}")
                }
            } catch (e: Exception) {
                Utils.snack(context, "Failed to fetch animals. Not connected")
                Utils.logd(context, "Error getting animals : $e")
            }

            try {
                val categoriesResponse = Request.categoriesCall()
                Utils.logd(context, categoriesResponse.toString())
                if (categoriesResponse.isSuccessful) {
                    val categories = categoriesResponse.body()
                    AppDatabase(context).categoryDao().insertAll(categories)
                } else {
                    Utils.logd(context, "Failed to fetch categories: ${categoriesResponse.code()}")
                    Utils.snack(context, "Failed to fetch categories: ${categoriesResponse.code()}")
                }
            } catch (e: Exception) {
                Utils.logd(context, "Error getting categories : $e")
            }


        }
    }

    val animalCategoryLiveData = AppDatabase(context).categoryDao().all()


    fun setSelectedAnimalCategory(category: Category) =
        selectedAnimalCategoryLiveData.apply { value = category }

}
