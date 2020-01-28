package com.monstercode.zooapp.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * SQLite Database used by the app, defines the tables to include
 * as well as Data Entry Objects (dao) to use
 */

@Database(
    entities = [
        Category::class,
        Animal::class,
        Question::class,
        Choice::class,
        Image::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    /**
     *  Access the database daos asynchronously by calling (eg):
     *      AppDatabase(context).announcementDao().all()
     */
    abstract fun categoryDao(): CategoryDao

    abstract fun animalDao(): AnimalDao

    abstract fun questionDao(): QuestionDao

    abstract fun choiceDao(): ChoiceDao

    abstract fun imageDao(): ImageDao

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