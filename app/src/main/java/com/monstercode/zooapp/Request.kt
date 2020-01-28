package com.monstercode.skyllaconnect

import com.monstercode.zooapp.room.Animal
import com.monstercode.zooapp.room.Category
import com.monstercode.zooapp.room.Choice
import com.monstercode.zooapp.room.Question
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * The Request class is responsible for all network requests such
 * as requesting for JSON data from an API.
 * The Request class makes use of the Service interface at the bottom of this file
 */

class Request {
    companion object {
        private const val tag = "Request"
        private const val baseUrl = "https://zoo.herokuapp.com"
        private const val localUrl = "http://10.0.2.2:8000"
        private const val inUseUrl = localUrl


        /**
         * These call functions are what will be called from the viewModels, activities or fragments
         * when requesting for data over the network. Remember, the call has to be made Asynchronously
         * so as not to block the Main UI.
         * The calls return a Retrofit response.
         */

        fun categoriesCall(): Response<List<Category>> =
            service().requestCategories().execute()

        fun questionsCall(): Response<List<Question>> =
            service().requestQuestions().execute()

        fun animalsCall(): Response<List<Animal>> =
            service().requestAnimals().execute()

        fun choicesCall(): Response<List<Choice>> =
            service().requestChoices().execute()


        /**
         * This function should be invoked before every call.
         * It is responsible for converting the JSON response got from the
         * network operation into an object or list of objects of a given class
         */
        private fun service(): Service {
            val client = OkHttpClient().newBuilder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(inUseUrl) // confirm that you are using correct url
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Service::class.java)
        }


    }
}

/**
 * This interface uses the Retrofit library to create request calls
 */
interface Service {
    @GET("categories")
    fun requestCategories(): Call<List<Category>>

    @GET("animals")
    fun requestAnimals(): Call<List<Animal>>

    @GET("questions")
    fun requestQuestions(): Call<List<Question>>

    @GET("choices")
    fun requestChoices(): Call<List<Choice>>

}
