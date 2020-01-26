package com.monstercode.zooapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.monstercode.zooapp.quiz.QuizFragment
import com.monstercode.zooapp.quiz.QuizViewModel
import com.monstercode.zooapp.room.Choice

class QuizActivity : AppCompatActivity(), QuizFragment.OnListFragmentInteractionListener {

    private lateinit var quizViewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        // setup view model that will be used by QuizFragment
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)

        val animalCategoryId = intent.getStringExtra("animalCategoryId")

        quizViewModel.setAnimalCategoryId(animalCategoryId)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.fragment_container,
            QuizFragment()
        )
        fragmentTransaction.commit()
    }

    /**
     * This function is invoked when you click on an item on the list
     * It changes the fragment from AnimalListFragment to AnimalDetailsFragment
     */
    override fun onListFragmentInteraction(choice: Choice) {
        changeFragment()
        quizViewModel.incrementQuestionNumberLiveData()
    }

    // Change the question being asked on the quiz fragment
    private fun changeFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.fragment_container,
            QuizFragment()
        )
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }




}