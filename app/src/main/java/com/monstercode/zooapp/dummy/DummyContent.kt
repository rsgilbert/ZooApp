package com.monstercode.zooapp.dummy

import com.monstercode.zooapp.room.Animal
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<Animal> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Animal> = HashMap()

    private val COUNT = 25

    init {
        val animal = Animal(_id="12", name="Rhino", summary="Endangered badly", profile_image = "/adele")
        // Add some sample items.
        for (i in 1..COUNT) {

            ITEMS.add(animal)
            ITEM_MAP.put(i.toString(), animal)
        }
    }



    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}
