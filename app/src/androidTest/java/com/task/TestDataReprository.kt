package com.task

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.task.TestDataReprository.Instance.initData
import com.task.data.DataSource
import com.task.data.remote.Data
import com.task.data.remote.Error
import com.task.data.remote.dto.NewsModel
import java.io.InputStream
import javax.inject.Inject


/**
 * Created by ahmedEltaher on 3/8/17.
 */

class TestDataReprository @Inject constructor() : DataSource {

    override fun requestNews(): Data? {
        return initData()
    }

    object Instance {
        var STATUS = DATA_STATUS.FULL_LIST
        fun initData(): Data {
            val gson = Gson()
            val jsonString = getJson("NewsApiResponse.json")
            var mokedResponse = gson.fromJson(jsonString, NewsModel::class.java)
            return when (STATUS) {
                DATA_STATUS.EMPTY_LIST -> {
                    mokedResponse.newsItems = emptyList()
                    Data(code = 200, error = null, data = mokedResponse)
                }
                DATA_STATUS.FULL_LIST -> {
                    Data(code = 200, error = null, data = mokedResponse)
                }
                DATA_STATUS.NO_DATA -> {
                    Data(code = 500, error = Error(description = "No data retrieved from server side", code = 500), data = null)
                }
                DATA_STATUS.NO_INTERNET -> {
                    Data(code = -1, error = Error(description = "No internet Connections", code = -1), data = null)
                }
            }

        }

        private fun getJson(path: String): String {
            val ctx: Context = InstrumentationRegistry.getInstrumentation().targetContext
            val inputStream: InputStream = ctx.assets.open(path)
            return inputStream.bufferedReader().use { it.readText() }
        }
    }
}

enum class DATA_STATUS { EMPTY_LIST, FULL_LIST, NO_DATA, NO_INTERNET }
