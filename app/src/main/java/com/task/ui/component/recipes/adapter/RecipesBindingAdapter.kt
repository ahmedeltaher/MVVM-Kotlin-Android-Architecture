package com.task.ui.component.recipes.adapter

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.recipes.Recipes
import com.task.data.dto.recipes.RecipesItem
import com.task.ui.component.recipes.RecipesListViewModel


@BindingAdapter(value = ["recipes", "viewModel"])
fun setRepositories(view: RecyclerView, recipes: Recipes?, vm: RecipesListViewModel) {

        view.adapter?.run {
        if (this is RecipesAdapter) {
            this.recipes = recipes
            this.notifyDataSetChanged()
    }
    } ?: run {
        RecipesAdapter(vm,recipes).apply { view.adapter = this }
    }
}

@BindingAdapter("android:loader")
fun View.toVisble(visibility: Boolean) {
    if (visibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility  = View.GONE

    }
}
