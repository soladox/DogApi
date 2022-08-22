package com.proyecto.dogapi.di.component

import com.proyecto.dogapi.di.module.DogListFragmentModule
import com.proyecto.dogapi.di.module.DogListRepositoryModule
import com.proyecto.dogapi.presentation.fragment.DogListFragment
import com.proyecto.dogapi.utils.FragmentComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DogListFragmentModule::class, DogListRepositoryModule::class])
interface DogListFragmentComponent: FragmentComponent<DogListFragment> {
}