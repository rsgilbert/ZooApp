package com.monstercode.zooapp.animal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.monstercode.zooapp.QuizActivity
import com.monstercode.zooapp.R
import com.monstercode.zooapp.Utils
import kotlinx.android.synthetic.main.fragment_detail.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
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
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val animalCategory = animalViewModel.selectedAnimalCategoryLiveData.value


        view.details_name.text = animalCategory?.name
        view.detail_info.text = getString(R.string.info_text, animalCategory?.info)
        view.detail_info.text = Utils.fromHtml(animalCategory?.info!!)

        view.go_to_quiz.onClick {
            val i = Intent(context, QuizActivity::class.java)
            i.putExtra("animalCategoryId", animalCategory.id)
            startActivity(i)
        }


        val recyclerView = view.card_list

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        // Set question and other quiz information basing on quiz live data
        animalViewModel.selectedAnimalCategoryLiveData.observe(this, Observer {
            recyclerView.adapter =
                CardRecyclerViewAdapter(
                    animals = it.animals,
                    context = context!!
                )
        })

        return view
    }


}
