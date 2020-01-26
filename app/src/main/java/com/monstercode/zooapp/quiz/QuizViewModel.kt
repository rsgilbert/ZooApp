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
        val q = it.first()
        logd(context, q.toString())
        q
    }

    // liveDataMerger observes other LiveData objects
    var liveDataMerger: MediatorLiveData<*> = MediatorLiveData<Any?>()

    init {

        insertQuestion()
        insertChoice()
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


    }

    private fun insertChoice() {
        doAsync {
            var choice = Choice(
                id = "2",
                choice = "Do you prefer yellow flies",
                animal_id = "2",
                question_id = "k"
            )
            AppDatabase(context).choiceDao().insertOne(choice)

            choice = Choice(
                id = "2q",
                choice = "I feel like chicken tonight",
                animal_id = "2",
                question_id = "k"
            )
            AppDatabase(context).choiceDao().insertOne(choice)

            choice = Choice(
                id = "12kk3",
                choice = "Coronavirus is pretty deadly",
                animal_id = "2",
                question_id = "k"
            )
            AppDatabase(context).choiceDao().insertOne(choice)

            choice = Choice(
                id = "123",
                choice = "Gorillas are pretty",
                animal_id = "2",
                question_id = "k"
            )
            AppDatabase(context).choiceDao().insertOne(choice)

            choice = Choice(
                id = "1233",
                choice = "Potatoes like maize alot",
                animal_id = "2",
                question_id = "k"
            )
            var choices = AppDatabase(context).choiceDao().insertOne(choice)

            logd(context, "Inserted $choices choices")
        }
    }


    fun setAnimalCategoryId(id: String) =
        animalCategoryId.apply { value = id }

    fun setNextQuestion() {
        attempts.apply { value?.add(questionLiveData.value!!) }
        logd(context, attempts.value.toString())

    }
}
