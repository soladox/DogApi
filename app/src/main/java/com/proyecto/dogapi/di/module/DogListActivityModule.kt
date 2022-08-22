package com.proyecto.dogapi.di.module

import com.proyecto.dogapi.presentation.fragment.DogListFragment
import dagger.Module
import dagger.Provides

@Module

class DogListActivityModule {
    @Provides
    fun provideFragment(): DogListFragment {
        return DogListFragment.newInstance()
    }
}