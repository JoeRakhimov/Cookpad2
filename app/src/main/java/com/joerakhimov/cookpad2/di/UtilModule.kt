package com.joerakhimov.cookpad2.di

import android.content.Context
import com.joerakhimov.cookpad2.util.image.GlideImageUtil
import com.joerakhimov.cookpad2.util.image.ImageUtil
import com.joerakhimov.cookpad2.util.dimen.DimenUtil
import com.joerakhimov.cookpad2.util.time.DateTimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    @Singleton
    fun provideImageUtil(@ApplicationContext context: Context): ImageUtil = GlideImageUtil(context)

    @Provides
    fun provideDateTimeUtil() = DateTimeUtil()

    @Provides
    fun provideDimenTimeUtil(@ApplicationContext context: Context) = DimenUtil(context)

}