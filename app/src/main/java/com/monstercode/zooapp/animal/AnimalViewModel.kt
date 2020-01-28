package com.monstercode.zooapp.animal

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.monstercode.zooapp.room.Animal
import com.monstercode.zooapp.room.AnimalCategory
import com.monstercode.zooapp.room.AppDatabase
import org.jetbrains.anko.doAsync

class AnimalViewModel(application: Application) : AndroidViewModel(application) {
    val context = application.applicationContext
    val tag = "AnimalViewModel"
    val selectedAnimalCategoryLiveData = MutableLiveData<AnimalCategory>()

    init {
        insertAnimalCategory()
        insertAnimal()
    }


    fun insertAnimalCategory() {
        val animalCategory = AnimalCategory(id = "5", name = "Termite", summary = "Tiny and frail")

        doAsync {
            val categories = AppDatabase(context).animalCategoryDao().insertOne(animalCategory)
            Log.d(tag, "Inserted $categories animal categories into db")
        }
    }

    fun insertAnimal() {
        val animal = Animal(id = "2", english_name = "Crested Crane", animal_category_id = "3")
        doAsync {
            val animals = AppDatabase(context).animalDao().insertOne(animal)
            Log.d(tag, "Inserted $animals animals into db")
        }
    }

    val animalCategoryLiveData = AppDatabase(context).animalCategoryDao().all()


    fun setSelectedAnimalCategory(animalCategory: AnimalCategory) =
        selectedAnimalCategoryLiveData.apply { value = animalCategory }

}
