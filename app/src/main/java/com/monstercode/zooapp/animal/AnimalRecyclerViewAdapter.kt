package com.monstercode.zooapp.animal


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monstercode.zooapp.R
import com.monstercode.zooapp.Utils
import com.monstercode.zooapp.animal.AnimalFragment.OnListFragmentInteractionListener
import com.monstercode.zooapp.room.Category
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_animal.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class AnimalRecyclerViewAdapter(
    private val mValues: List<Category>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<AnimalRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Category
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_animal, parent, false)
        Utils.setClickableAnimation(
            parent.context,
            view
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.name.text = item.name
        holder.summary.text = item.summary

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val profileImage: CircleImageView = mView.animal_profile_image
        val name: TextView = mView.animal_name
        val summary: TextView = mView.animal_summary

    }
}
