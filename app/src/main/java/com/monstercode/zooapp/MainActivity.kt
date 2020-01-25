package com.monstercode.zooapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.monstercode.zooapp.animal.AnimalDetailsFragment
import com.monstercode.zooapp.animal.AnimalFragment
import com.monstercode.zooapp.animal.AnimalViewModel
import com.monstercode.zooapp.room.AnimalCategory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AnimalFragment.OnListFragmentInteractionListener {

    private lateinit var animalViewModel: AnimalViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        animalViewModel = ViewModelProviders.of(this).get(AnimalViewModel::class.java)


        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.fragment_container,
            AnimalFragment()
        )
        fragmentTransaction.commit()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                run { super.onBackPressed() }
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * This function is invoked when you click on an item on the list
     * It changes the fragment from AnimalListFragment to AnimalDetailsFragment
     */
    override fun onListFragmentInteraction(animalCategory: AnimalCategory?) {
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        animalViewModel.setSelectedAnimalCategory(animalCategory!!)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.fragment_container,
            AnimalDetailsFragment()
        )
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
