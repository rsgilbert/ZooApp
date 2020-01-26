package com.monstercode.zooapp.quiz

import android.app.Application
import androidx.lifecycle.*
import com.monstercode.zooapp.Utils
import com.monstercode.zooapp.Utils.Companion.logd
import com.monstercode.zooapp.room.AppDatabase
import com.monstercode.zooapp.room.Choice
import com.monstercode.zooapp.room.Question
import org.jetbrains.anko.doAsync


class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    private val animalCategoryId = MutableLiveData<String>()
    private val attempts = MutableLiveData<MutableList<Question>>()
    private val questionNumberLiveData = MutableLiveData<Int>().apply { value = 0 }

    private val questionsListLiveData: LiveData<List<Question>> =
        Transformations.switchMap(animalCategoryId) {
            AppDatabase(context).questionDao().questionsByCategory(it)
        }

    val questionLiveData: LiveData<Question> = Transformations.map(questionsListLiveData) {
        it.first()
    }

    // liveDataMerger observes other LiveData objects
    var liveDataMerger: MediatorLiveData<*> = MediatorLiveData<Any?>()
    init {
        liveDataMerger.addSource(questionLiveData, Observer {
            var count = 1

            liveDataMerger.value = it
            count++
            logd(context, "Count is $count")
            if (count > 10) {
                logd(context, "Greater than 10")
            }

        })
        insertQuestion()
    }


    val choicesLiveData: LiveData<List<Choice>> =
        Transformations.switchMap(questionLiveData) {
            AppDatabase(context).choiceDao().choicesByQuestion(it.id)
        }


    fun incrementQuestionNumberLiveData() {
        questionNumberLiveData.value = (questionNumberLiveData.value ?: 0) + 1
    }

    private fun insertQuestion() {
        val question =
            Question(id = "k", question = "Do you like Gorillas", animal_category_id = "3")

        val question2 =
            Question(id = "kk", question = "Are chimpazees dead?", animal_category_id = "3")
        doAsync {
            val questionsInserted = AppDatabase(context).questionDao().insertOne(question)
            val questionsInserted2 = AppDatabase(context).questionDao().insertOne(question2)
            Utils.logd(context, "Inserted $questionsInserted2 questions2")
        }

        Utils.logd(context, "Inserted a question")
    }


    fun setAnimalCategoryId(id: String) =
        animalCategoryId.apply { value = id }

    fun setNextQuestion() {
        attempts.apply { value?.add(questionLiveData.value!!) }
        logd(context, attempts.value.toString())

    }
}
