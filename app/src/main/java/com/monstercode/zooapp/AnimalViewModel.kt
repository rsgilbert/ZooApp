package com.monstercode.zooapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.monstercode.zooapp.room.Animal

class AnimalViewModel(application: Application) : AndroidViewModel(application) {
    val context = application.applicationContext

    private val allAnimals = listOf(
        Animal(_id = "1", name = "Rhino", summary = "Big one horned", profile_image = "dang"),
        Animal(_id = "1", name = "Elephant", summary = "Big and long nose", profile_image = "dang"),
        Animal(
            _id = "1",
            name = "Nile Crocodile",
            summary = "Deadly in the water",
            profile_image = "bat"
        )
    )

    val animalLiveData = MutableLiveData<Animal>()

    fun setAnimal(animal: Animal) = animalLiveData.apply { value = animal }

    val animals = MutableLiveData<List<Animal>>().apply { value = allAnimals }
}
