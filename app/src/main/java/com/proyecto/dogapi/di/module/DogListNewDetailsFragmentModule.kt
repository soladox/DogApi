package com.proyecto.dogapi.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyecto.dogapi.presentation.viewModel.DogListNewDetailsViewModel
import com.proyecto.dogapi.utils.ViewModelFactory
import com.proyecto.dogapi.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DogListNewDetailsFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(DogListNewDetailsViewModel::class)
    abstract fun bindCompaniesViewModel(viewModel: DogListNewDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}