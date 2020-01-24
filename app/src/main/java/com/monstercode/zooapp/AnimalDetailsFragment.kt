package com.monstercode.zooapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.find


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
        val animal = animalViewModel.animalLiveData.value

        val detailsName = view.find<TextView>(R.id.details_name)
        val detailsSummary = view.find<TextView>(R.id.details_summary)
        detailsName.text = animal?.name
        detailsSummary.text = animal?.summary

        return view
    }


}
