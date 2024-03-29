package com.monstercode.zooapp.animal


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monstercode.zooapp.R
import com.monstercode.zooapp.Utils
import com.monstercode.zooapp.animal.AnimalFragment.OnListFragmentInteractionListener
import com.monstercode.zooapp.room.CategoryWithAnimals
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.recycler_animal.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class AnimalRecyclerViewAdapter(
    private val mValues: List<CategoryWithAnimals>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<AnimalRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as CategoryWithAnimals
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_animal, parent, false)
        Utils.setClickableAnimation(
            parent.context,
            view
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        with(item) {
            holder.name.text = name
            holder.info.text = info
            holder.level.text = level
            holder.count.text = "~$count alive"

        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val profileImage: CircleImageView = mView.animal_profile_image
        val name: TextView = mView.animal_name
        val info: TextView = mView.animal_info
        val count: TextView = mView.animal_count
        val level: TextView = mView.animal_level

    }
}
