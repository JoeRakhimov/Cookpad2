package com.joerakhimov.cookpad2.screen.recipe.steps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joerakhimov.cookpad2.data.model.recipe.Step
import com.joerakhimov.cookpad2.databinding.ListitemStepBinding
import com.joerakhimov.cookpad2.extensions.setGone
import com.joerakhimov.cookpad2.extensions.setVisible
import com.joerakhimov.cookpad2.extensions.showOrGone
import com.joerakhimov.cookpad2.util.dimen.DimenUtil
import com.joerakhimov.cookpad2.util.image.ImageUtil

class StepsAdapter(private val steps: List<Step>, private val imageUtil: ImageUtil, private val dimenUtil: DimenUtil) :
    RecyclerView.Adapter<StepsAdapter.StepHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepHolder {
        val itemBinding = ListitemStepBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StepHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StepHolder, position: Int) {
        val step = steps[position]
        holder.bind(step, itemCount, imageUtil, dimenUtil)
    }

    override fun getItemCount(): Int = steps.size

    class StepHolder(private val itemBinding: ListitemStepBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(step: Step, itemCount: Int, imageUtil: ImageUtil, dimenUtil: DimenUtil) {
            itemBinding.textStepDescription.text = step.description

            showImages(itemBinding, step.imageUrls, imageUtil, dimenUtil)

            val showDivider = position != itemCount - 1
            itemBinding.viewStepDivider.showOrGone(showDivider)
        }

        private fun showImages(itemBinding: ListitemStepBinding, imageUrls: List<String>, imageUtil: ImageUtil, dimenUtil: DimenUtil) {

            if (imageUrls.isEmpty()) {
                itemBinding.layoutStepImages.setGone()
                return
            }

            itemBinding.layoutStepImages.setVisible()

            itemBinding.cardStepPhoto1.setGone()
            itemBinding.cardStepPhoto2.setGone()
            itemBinding.cardStepPhoto3.setGone()

            imageUrls.forEachIndexed { index, imageUrl ->
                when (index) {
                    0 -> {
                        itemBinding.cardStepPhoto1.setVisible()
                        imageUtil.loadImage(itemBinding.imageStepPhoto1, imageUrl)
                    }
                    1 -> {
                        itemBinding.cardStepPhoto2.setVisible()
                        imageUtil.loadImage(itemBinding.imageStepPhoto2, imageUrl)
                    }
                    2 -> {
                        itemBinding.cardStepPhoto3.setVisible()
                        imageUtil.loadImage(itemBinding.imageStepPhoto3, imageUrl)
                    }
                }
            }

            val displayMetrics = itemBinding.layoutStepImages.context.resources.displayMetrics
            val displayWidth = displayMetrics.widthPixels
            val displayWidthExcludingMargins = displayWidth - dimenUtil.dpToPx(48)
            itemBinding.layoutStepImages.layoutParams.height =  displayWidthExcludingMargins / imageUrls.size

        }

    }

}