package com.joerakhimov.cookpad2.screen.recipe.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.databinding.FragmentStepsBinding
import com.joerakhimov.cookpad2.screen.recipe.ARG_RECIPE
import com.joerakhimov.cookpad2.util.dimen.DimenUtil
import com.joerakhimov.cookpad2.util.image.ImageUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StepsFragment: Fragment() {

    lateinit var recipe: Recipe
    private var _binding: FragmentStepsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var imageUtil: ImageUtil

    @Inject
    lateinit var dimenUtil: DimenUtil

    companion object {
        @JvmStatic
        fun newInstance(recipe: Recipe): StepsFragment {
            return StepsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_RECIPE, recipe)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipe = arguments?.getSerializable(ARG_RECIPE) as Recipe
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSteps()
    }

    private fun showSteps(){
        binding.recyclerRecipeSteps.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = StepsAdapter(recipe.steps, imageUtil, dimenUtil)
        }
    }

}