package com.monstercode.zooapp.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.monstercode.skyllaconnect.room.AppDatabase
import com.monstercode.zooapp.Utils
import com.monstercode.zooapp.room.Question
import org.jetbrains.anko.doAsync

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    private val animalCategoryId = MutableLiveData<String>()

    private val questionsListLiveData: LiveData<List<Question>> =
        Transformations.switchMap(animalCategoryId) {
            AppDatabase(context).questionDao().questionsByCategory(it)
        }

    val questionLiveData: LiveData<Question> = Transformations.map(questionsListLiveData) {
        it.first()
    }

    init {
        insertQuestion()
    }

    private fun insertQuestion() {
        val question =
            Question(id = "k", question = "Do you like Gorillas", animal_category_id = "3")
        doAsync {
            val questionsInserted = AppDatabase(context).questionDao().insertOne(question)
            Utils.logd(context, "Inserted $questionsInserted questions")
        }

        Utils.logd(context, "Inserted a question")
    }


    fun setAnimalCategoryId(id: String) =
        animalCategoryId.apply { value = id }

}
