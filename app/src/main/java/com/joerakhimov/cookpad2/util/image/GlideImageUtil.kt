package com.joerakhimov.cookpad2.util.image

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.joerakhimov.cookpad2.R

class GlideImageUtil(val context: Context) : ImageUtil {

    override fun loadImage(imageView: ImageView, url: String?) {
        Glide.with(context).load(url).error(R.drawable.placeholder).into(imageView)
    }

}