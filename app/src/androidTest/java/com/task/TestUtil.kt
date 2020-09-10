package com.task

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.task.data.dto.recipes.Recipes
import com.task.data.dto.recipes.RecipesItem
import com.task.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import com.task.data.remote.moshiFactories.MyStandardJsonAdapters
import java.io.InputStream
import java.lang.reflect.Type

/**
 * Created by AhmedEltaher
 */

object TestUtil {
    var dataStatus: DataStatus = DataStatus.Success
    var recipes: Recipes = Recipes(arrayListOf())
    fun initData(): Recipes {
        val moshi = Moshi.Builder()
                .add(MyKotlinJsonAdapterFactory())
                .add(MyStandardJsonAdapters.FACTORY)
                .build()
        val type: Type = Types.newParameterizedType(List::class.java, RecipesItem::class.java)
        val adapter: JsonAdapter<List<RecipesItem>> = moshi.adapter(type)
        val jsonString = getJson("RecipesApiResponse.json")
        adapter.fromJson(jsonString)?.let {
            recipes = Recipes(ArrayList(it))
            return recipes
        }
        return Recipes(arrayListOf())
    }

    private fun getJson(path: String): String {
        val ctx: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val inputStream: InputStream = ctx.classLoader.getResourceAsStream(path)
        return inputStream.bufferedReader().use { it.readText() }
    }
}
