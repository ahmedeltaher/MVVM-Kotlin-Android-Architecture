package com.task

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.task.TestDataReprository.Instance.initData
import com.task.data.DataSource
import com.task.data.Resource
import com.task.data.remote.dto.NewsModel
import java.io.InputStream
import javax.inject.Inject


/**
 * Created by ahmedEltaher on 3/8/17.
 */

class TestDataReprository @Inject constructor() : DataSource {

    override suspend fun requestNews(): Resource<NewsModel> {
        return Resource.Success(initData())
    }

    object Instance {
        var STATUS = DATA_STATUS.FULL_LIST
        fun initData(): NewsModel {
            val gson = Gson()
            val jsonString = getJson("NewsApiResponse.json")
            return gson.fromJson(jsonString, NewsModel::class.java)
        }

        private fun getJson(path: String): String {
            val ctx: Context = InstrumentationRegistry.getInstrumentation().targetContext
            val inputStream: InputStream = ctx.assets.open(path)
            return inputStream.bufferedReader().use { it.readText() }
        }
    }

}

enum class DATA_STATUS { EMPTY_LIST, FULL_LIST, NO_DATA, NO_INTERNET }
