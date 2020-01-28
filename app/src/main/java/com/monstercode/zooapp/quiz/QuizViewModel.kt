package com.monstercode.zooapp.quiz

import android.app.Application
import androidx.lifecycle.*
import com.monstercode.skyllaconnect.Request
import com.monstercode.zooapp.Utils
import com.monstercode.zooapp.Utils.Companion.logd
import com.monstercode.zooapp.room.AppDatabase
import com.monstercode.zooapp.room.Choice
import com.monstercode.zooapp.room.Question
import com.monstercode.zooapp.room.QuestionWithChoices
import org.jetbrains.anko.doAsync


class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    private val animalCategoryId = MutableLiveData<String>()
    private val attempts = MutableLiveData<MutableList<Question>>()
    private val questionNumberLiveData = MutableLiveData<Int>().apply { value = 0 }

    private val questionsListLiveData: LiveData<List<QuestionWithChoices>> =
        Transformations.switchMap(animalCategoryId) {
            AppDatabase(context).questionDao().questionsByCategory(it)
        }

    val questionLiveData: LiveData<QuestionWithChoices> =
        Transformations.map(questionsListLiveData) {
            if (it.isEmpty()) null else it.first()
    }

    // liveDataMerger observes other LiveData objects
    var liveDataMerger: MediatorLiveData<*> = MediatorLiveData<Any?>()

    init {
        updateQuiz()
    }

    fun updateQuiz() {
        doAsync {
            try {
                val questionsResponse = Request.questionsCall()
                if (questionsResponse.isSuccessful) {
                    val questions = questionsResponse.body()
                    Utils.logd(context, questions.toString())
                    AppDatabase(context).questionDao().insertAll(questions)
                } else {
                    Utils.logd(context, "Failed to fetch animals: ${questionsResponse.code()}")
                    Utils.snack(context, "Failed to fetch animals: ${questionsResponse.code()}")
                }
            } catch (e: Exception) {
                Utils.snack(context, "Failed to fetch animals. Not connected")
                Utils.logd(context, "Error getting animals : $e")
            }

            try {
                val choicesResponse = Request.choicesCall()
                Utils.logd(context, choicesResponse.toString())
                if (choicesResponse.isSuccessful) {
                    val choices = choicesResponse.body()
                    AppDatabase(context).choiceDao().insertAll(choices)
                } else {
                    logd(context, "Failed to fetch categories: ${choicesResponse.code()}")
                    Utils.snack(context, "Failed to fetch categories: ${choicesResponse.code()}")
                }
            } catch (e: Exception) {
                logd(context, "Error getting categories : $e")
            }


        }

    }

    val choicesLiveData: LiveData<List<Choice>> =
        Transformations.switchMap(questionLiveData) {
            AppDatabase(context).choiceDao().choicesByQuestion(it.id)
        }


    fun incrementQuestionNumberLiveData() {
        questionNumberLiveData.value = (questionNumberLiveData.value ?: 0) + 1
    }



    fun setAnimalCategoryId(id: String) =
        animalCategoryId.apply { value = id }

}
