package com.monstercode.zooapp.quiz


import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.monstercode.zooapp.R
import com.monstercode.zooapp.Utils
import com.monstercode.zooapp.room.Choice
import kotlinx.android.synthetic.main.recycler_choices.view.*
import org.jetbrains.anko.design.indefiniteSnackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class QuizRecyclerViewAdapter(
    private val choices: List<Choice>,
    private val activity: Activity,
    private val context: Context,
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
        val item = choices[position]
        holder.choice.text = item.choice


        holder.choice.onClick {
            if (item.correct) {
                holder.choice.isSelected = true
                holder.choice.setIconResource(R.drawable.ic_tick)
                activity.find<View>(android.R.id.content).indefiniteSnackbar(item.info, "NEXT") {
                    //                    context.startActivity(Intent(context, MainActivity::class.java))
                    mListener?.onListFragmentInteraction(item)
                }.show()
            } else {
                holder.choice.setIconResource(R.drawable.ic_cross)
            }
        }

        with(holder.mView) {
            tag = item
//            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = choices.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val choice: MaterialButton = mView.btn_choice

    }
}
