package com.proyecto.dogapi.di.component

import com.proyecto.dogapi.di.module.DogListDetailsFragmentModule
import com.proyecto.dogapi.di.module.DogListRepositoryModule
import com.proyecto.dogapi.presentation.fragment.DogListDetailsFragment
import com.proyecto.dogapi.utils.FragmentComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DogListDetailsFragmentModule::class, DogListRepositoryModule::class])
interface DogListDetailsFragmentComponent : FragmentComponent<DogListDetailsFragment>{
}