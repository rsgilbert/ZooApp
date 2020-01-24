package com.monstercode.zooapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.monstercode.zooapp.room.Animal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AnimalFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


    }



    override fun onListFragmentInteraction(item: Animal?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        Log.d(this.javaClass.simpleName, "Item is $item")
    }
}
