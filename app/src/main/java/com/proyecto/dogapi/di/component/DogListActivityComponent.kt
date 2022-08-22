package com.proyecto.dogapi.di.component

import com.proyecto.dogapi.di.module.DogListActivityModule
import com.proyecto.dogapi.presentation.activity.DogListActivity
import com.proyecto.dogapi.utils.ActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DogListActivityModule::class])
interface DogListActivityComponent: ActivityComponent<DogListActivity>