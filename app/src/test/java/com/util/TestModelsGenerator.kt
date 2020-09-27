package com.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.task.data.dto.recipes.Recipes
import com.task.data.dto.recipes.RecipesItem
import com.task.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import com.task.data.remote.moshiFactories.MyStandardJsonAdapters
import java.io.File
import java.lang.reflect.Type


/**
 * Created by AhmedEltaher
 */

class TestModelsGenerator {
    private var recipes: Recipes = Recipes(arrayListOf())

    init {
        val moshi = Moshi.Builder()
                .add(MyKotlinJsonAdapterFactory())
                .add(MyStandardJsonAdapters.FACTORY)
                .build()
        val type: Type = Types.newParameterizedType(List::class.java, RecipesItem::class.java)
        val adapter: JsonAdapter<List<RecipesItem>> = moshi.adapter(type)
        val jsonString = getJson("RecipesApiResponse.json")
        adapter.fromJson(jsonString)?.let {
            recipes = Recipes(ArrayList(it))
        }
        print("this is $recipes")
    }

    fun generateRecipes(): Recipes {
        return recipes
    }

    fun generateRecipesModelWithEmptyList(): Recipes {
        return Recipes(arrayListOf())
    }

    fun generateRecipesItemModel(): RecipesItem {
        return recipes.recipesList[0]
    }

    fun getStubSearchTitle(): String {
        return recipes.recipesList[0].name
    }


    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}
