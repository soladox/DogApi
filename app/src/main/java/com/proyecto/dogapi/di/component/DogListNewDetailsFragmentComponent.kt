package com.proyecto.dogapi.di.component

import com.proyecto.dogapi.di.module.DogListNewDetailsFragmentModule
import com.proyecto.dogapi.di.module.DogListRepositoryModule
import com.proyecto.dogapi.presentation.fragment.DogListNewDetailsFragment
import com.proyecto.dogapi.utils.FragmentComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DogListNewDetailsFragmentModule::class, DogListRepositoryModule::class])
interface DogListNewDetailsFragmentComponent : FragmentComponent<DogListNewDetailsFragment> {
}
