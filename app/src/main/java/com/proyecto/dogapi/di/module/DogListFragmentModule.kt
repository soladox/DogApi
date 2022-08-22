package com.proyecto.dogapi.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyecto.dogapi.presentation.viewModel.DogListViewModel
import com.proyecto.dogapi.utils.ViewModelFactory
import com.proyecto.dogapi.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module

abstract class DogListFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(DogListViewModel::class)

    abstract fun bindCompaniesViewModel(viewModel:DogListViewModel): ViewModel

    @Binds

    abstract fun bindViewModelFactory(factory:ViewModelFactory):ViewModelProvider.Factory
}