package co.leaders.board.api

import android.util.Log
import co.leaders.board.model.LearningLeaderModel
import co.leaders.board.model.SkillModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @GET("/api/hours")
    fun leaningApiCall(): Call<List<LearningLeaderModel>>

    @GET("/api/skilliq")
    fun skillsApiCall(): Call<List<SkillModel>>

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun submitFormData(
        @Field("entry.1824927963") email: String,
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") gitLink: String,
    ): Call<Void>

    companion object {

        const val TAG = "ApiService"

        // For singleton instantiation
        @Volatile
        private var instance: ApiService? = null

        fun getInstance(url: String): ApiService {
            Log.d(TAG, "url => $url");
//            return instance ?: synchronized(this) ?
            return buildInstance(url).also { instance = it }
//            }
        }

        private fun buildInstance(url: String): ApiService {

            val gson = GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create()

            Log.d(TAG, "url => sent $url");

            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)
        }

    }

}