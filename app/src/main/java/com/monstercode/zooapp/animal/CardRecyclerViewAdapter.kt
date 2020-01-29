package com.monstercode.zooapp.animal


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monstercode.zooapp.R
import com.monstercode.zooapp.Utils
import com.monstercode.zooapp.animal.AnimalFragment.OnListFragmentInteractionListener
import com.monstercode.zooapp.room.Animal
import kotlinx.android.synthetic.main.recycler_cards.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class CardRecyclerViewAdapter(
    private val animals: List<Animal>,
    private val context: Context
//    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Animal
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
//            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_cards, parent, false)
        Utils.setClickableAnimation(
            parent.context,
            view
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = animals[position]
        with(item) {
            holder.name.text = name
            holder.level.text = level
            holder.count.text = context.getString(R.string.count_text, count)
            holder.location.text = location

        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = animals.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val picture: ImageView = mView.card_picture
        val name: TextView = mView.card_name
        val count: TextView = mView.card_count
        val level: TextView = mView.card_level
        val location: TextView = mView.card_location


    }
}
