package com.monstercode.zooapp.animal

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.monstercode.skyllaconnect.room.AppDatabase
import com.monstercode.zooapp.room.Animal
import com.monstercode.zooapp.room.AnimalCategory
import org.jetbrains.anko.doAsync

class AnimalViewModel(application: Application) : AndroidViewModel(application) {
    val context = application.applicationContext
    val tag = "AnimalViewModel"

    init {
        insertAnimalCategory()
        insertAnimal()
    }


    fun insertAnimalCategory() {
        val animalCategory = AnimalCategory(id = "3", name = "Chimpazee", summary = "Very first")

        doAsync {
            val categories = AppDatabase(context).animalCategoryDao().insertOne(animalCategory)
            Log.d(tag, "Inserted $categories animal categories into db")
        }
    }

    fun insertAnimal() {
        val animal = Animal(id = "1", english_name = "Black Chimpazee", animal_category_id = "3")
        doAsync {
            val animals = AppDatabase(context).animalDao().insertOne(animal)
            Log.d(tag, "Inserted $animals animals into db")
        }
    }

    val animalCategoryLiveData = AppDatabase(context).animalCategoryDao().all()

    val selectedAnimalCategoryLiveData = MutableLiveData<AnimalCategory>().apply { value = null }

    fun setSelectedAnimalCategory(animalCategory: AnimalCategory) =
        selectedAnimalCategoryLiveData.apply { value = animalCategory }

}
