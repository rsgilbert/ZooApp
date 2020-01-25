package com.monstercode.skyllaconnect.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.monstercode.zooapp.room.AnimalCategory
import com.monstercode.zooapp.room.AnimalCategoryDao

/**
 * Database used by the app, defines the tables to include
 * as well as Data Entry Objects (dao) to use
 */

@Database(entities = [AnimalCategory::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /**
     *  Access the database daos asynchronously by calling (eg):
     *      AppDatabase(context).announcementDao().all()
     */
    abstract fun animalCategoryDao(): AnimalCategoryDao
//    abstract fun animalDao(): AnimalDao

    /**
     * Do not make changes to this companion object unless if you know
     * what you are doing.
     * Visit the android documentation for more information on Room.
     */

    companion object {
        @Volatile
        private var mInstance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = mInstance ?: synchronized(LOCK) {
            mInstance ?: buildDatabase(context).also { mInstance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "db"
        ).build()
    }
}