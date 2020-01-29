package com.monstercode.zooapp.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.monstercode.zooapp.R
import com.monstercode.zooapp.room.Choice
import kotlinx.android.synthetic.main.fragment_quiz.view.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [QuizFragment.OnListFragmentInteractionListener] interface.
 */
class QuizFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var quizViewModel: QuizViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Attach view model in parent activity to this activity
         * so the two can use the same data
         */
        quizViewModel = activity?.run {
            ViewModelProviders.of(this).get(QuizViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        val recyclerView = view.choice_list

        recyclerView.layoutManager = LinearLayoutManager(context)



        // Set question and other quiz information basing on quiz live data
        quizViewModel.questionLiveData.observe(this, Observer {

            if (it != null) {
                view.quiz_question.text = it.question
                recyclerView.adapter =
                    QuizRecyclerViewAdapter(
                        it.choices,
                        activity = activity!!,
                        context = context!!,
                        mListener = listener
                    )
            }
        })

        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }



    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(choice: Choice)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
