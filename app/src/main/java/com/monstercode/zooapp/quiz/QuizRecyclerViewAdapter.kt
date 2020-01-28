package com.monstercode.zooapp.quiz


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monstercode.zooapp.R
import com.monstercode.zooapp.Utils
import com.monstercode.zooapp.room.Choice
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_quiz.view.*
import kotlinx.android.synthetic.main.recycler_choices.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class QuizRecyclerViewAdapter(
    private val mValues: List<Choice>,
    private val mListener: QuizFragment.OnListFragmentInteractionListener?
) : RecyclerView.Adapter<QuizRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Choice
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_choices, parent, false)
        Utils.setClickableAnimation(
            parent.context,
            view
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.choice.text = item.choice


        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val choice: TextView = mView.choice

    }
}
