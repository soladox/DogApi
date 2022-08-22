package com.proyecto.dogapi.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.proyecto.dogapi.R
import com.proyecto.dogapi.di.component.DaggerDogListActivityComponent

class DogListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        setContentView(R.layout.breed_dog_activity)
        val myNavHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = myNavHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation)
        myNavHostFragment.navController.graph = graph
    }

    protected fun injectDependencies(){
        DaggerDogListActivityComponent.builder().build().inject(this)
    }
}