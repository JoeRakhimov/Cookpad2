package com.joerakhimov.cookpad2.screen.recipe.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.joerakhimov.cookpad2.util.image.ImageUtil
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.databinding.FragmentInfoBinding
import com.joerakhimov.cookpad2.screen.recipe.ARG_RECIPE
import com.joerakhimov.cookpad2.util.time.DateTimeUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InfoFragment: Fragment() {

    @Inject
    lateinit var imageUtil: ImageUtil

    @Inject
    lateinit var dateTimeUtil: DateTimeUtil

    lateinit var recipe: Recipe
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance(recipe: Recipe): InfoFragment {
            return InfoFragment().apply {
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
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInfo()
    }

    private fun showInfo() {
        imageUtil.loadImage(binding.imageRecipePhoto, recipe.imageUrl)
        binding.textRecipeTitle.text = recipe.title

        imageUtil.loadImage(binding.imageUserPhoto, recipe.user.imageUrl)
        binding.textRecipeUser.text = recipe.user.name
        val humanReadableDate =
            dateTimeUtil.fromBackendDatetimeToHumanReadableDate(recipe.publishedAt)
        binding.textRecipePublishedTime.text = humanReadableDate

        binding.textRecipeStory.text = recipe.story
    }

}