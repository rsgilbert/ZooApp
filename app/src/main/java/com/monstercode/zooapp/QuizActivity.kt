package com.monstercode.zooapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.monstercode.zooapp.quiz.QuizFragment
import com.monstercode.zooapp.quiz.QuizViewModel
import org.jetbrains.anko.toast

class QuizActivity : AppCompatActivity() {

    private lateinit var quizViewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        // setup view model that will be used by QuizFragment
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)

        val animalCategoryId = intent.getStringExtra("animalCategoryId")

        toast(animalCategoryId!!)

        quizViewModel.setAnimalCategoryId(animalCategoryId)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.fragment_container,
            QuizFragment()
        )
        fragmentTransaction.commit()
    }


}