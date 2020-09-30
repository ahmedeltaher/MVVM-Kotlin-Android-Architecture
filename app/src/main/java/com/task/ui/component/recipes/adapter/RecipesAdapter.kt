package com.task.ui.component.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.recipes.Recipes
import com.task.data.dto.recipes.RecipesItem
import com.task.databinding.RecipeItemBinding
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.recipes.RecipesListViewModel

/**
 * Created by AhmedEltaher
 */

class RecipesAdapter(private val recipesListViewModel: RecipesListViewModel, var recipes: Recipes?) : RecyclerView.Adapter<RecipeViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(recipe: RecipesItem) {
            recipesListViewModel.openRecipeDetails(recipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes!!.recipesList[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        if (recipes != null)
            return recipes?.recipesList!!.size
        else return 0;
    }
}

