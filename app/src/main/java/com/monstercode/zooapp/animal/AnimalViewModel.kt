package com.monstercode.zooapp.animal

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.monstercode.skyllaconnect.room.AppDatabase
import com.monstercode.zooapp.room.AnimalCategory
import org.jetbrains.anko.doAsync

class AnimalViewModel(application: Application) : AndroidViewModel(application) {
    val context = application.applicationContext
    val tag = "AnimalViewModel"

    init {
        insertAnimalCategory()
    }


    fun insertAnimalCategory() {
        val animalCategory = AnimalCategory(id = "2", name = "Bukope", summary = "Jangu and eat")
        doAsync {
            val categories = AppDatabase(context).animalCategoryDao().insertOne(animalCategory)
            Log.d(tag, "Inserted $categories animal categories into db")
        }
    }

    val animalCategoryLiveData = AppDatabase(context).animalCategoryDao().all()

    val selectedAnimalCategoryLiveData = MutableLiveData<AnimalCategory>().apply { value = null }

    fun setSelectedAnimalCategory(animalCategory: AnimalCategory) =
        selectedAnimalCategoryLiveData.apply { value = animalCategory }

}
