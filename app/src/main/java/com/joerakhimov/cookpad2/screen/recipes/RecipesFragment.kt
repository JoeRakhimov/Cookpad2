package com.joerakhimov.cookpad2.screen.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.joerakhimov.cookpad2.R
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.data.model.util.Status
import com.joerakhimov.cookpad2.databinding.FragmentRecipesBinding
import com.joerakhimov.cookpad2.extensions.setGone
import com.joerakhimov.cookpad2.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RecipesFragment : Fragment(R.layout.fragment_recipes) {

    private val viewModel: RecipesViewModel by viewModels()
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.recipes)
        setupObservers()
        initRefreshListener()
    }

    private fun setupObservers() {

        viewModel.recipes.onEach { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    binding.swipeRecipes.isRefreshing = false
                    binding.recyclerRecipes.setVisible()
                    resource.data?.let { recipes -> showRecipes(recipes) }
                }
                Status.ERROR -> {
                    binding.swipeRecipes.isRefreshing = false
                    binding.recyclerRecipes.setGone()
                    resource.message?.let { showSnackbar(it) }
                }
                Status.LOADING -> {
                    binding.swipeRecipes.isRefreshing = true
                    binding.recyclerRecipes.setGone()
                }
            }
        }.launchIn(lifecycleScope)

    }

    private fun showRecipes(recipes: List<Recipe>) {
        binding.recyclerRecipes.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = RecipesAdapter(recipes)
        }
    }

    private fun initRefreshListener() {
        binding.swipeRecipes.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar
            .make(
                requireContext(),
                binding.root,
                message,
                Snackbar.LENGTH_LONG
            )
            .show()
    }

}