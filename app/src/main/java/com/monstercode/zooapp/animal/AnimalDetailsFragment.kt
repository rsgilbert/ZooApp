package com.monstercode.zooapp.animal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.monstercode.zooapp.QuizActivity
import com.monstercode.zooapp.R
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick


class AnimalDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AnimalDetailsFragment()
    }


    private lateinit var animalViewModel: AnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Attach view model in parent activity to this activity
         * so the two can use the same data
         */
        animalViewModel = activity?.run {
            ViewModelProviders.of(this).get(AnimalViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.animal_details_fragment, container, false)
        val animalCategory = animalViewModel.selectedAnimalCategoryLiveData.value

        val detailsName = view.find<TextView>(R.id.details_name)
        val detailsSummary = view.find<TextView>(R.id.details_summary)
        val goToQuizButton = view.find<Button>(R.id.go_to_quiz)
        detailsName.text = animalCategory?.name
        detailsSummary.text = animalCategory?.summary

        // change to quiz activity supplying the animal_category_id
        goToQuizButton.onClick {
            val intent = Intent(activity, QuizActivity::class.java)
            intent.putExtra("animalCategoryId", animalCategory!!.id)
            startActivity(intent)
        }
        return view
    }


}
