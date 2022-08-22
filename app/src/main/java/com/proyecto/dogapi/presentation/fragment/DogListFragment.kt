package com.proyecto.dogapi.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyecto.dogapi.R
import com.proyecto.dogapi.domain.model.Race
import com.proyecto.dogapi.presentation.adapter.DogListAdapter
import com.proyecto.dogapi.presentation.viewModel.DogListViewModel
import com.proyecto.dogapi.utils.Failure
import com.proyecto.dogapi.utils.ResourceState
import com.proyecto.dogapi.utils.ViewModelFactory
import com.proyecto.dogapi.utils.extension.requireNavController
import com.proyecto.dogapi.utils.extension.showMessage
import com.proyecto.dogapi.utils.extension.showProgress
import com.proyecto.dogapi.utils.getViewModel
import com.proyecto.dogapi.di.component.DaggerDogListFragmentComponent
import kotlinx.android.synthetic.main.fragment_dog_list.*
import javax.inject.Inject

class DogListFragment : Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: DogListViewModel
    lateinit var dogListAdapter: DogListAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        injectDependence()
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.getDogRace()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dog_list, container, false)

    companion object{
        fun newInstance(): DogListFragment {
            val instance = DogListFragment()
            return instance
        }
    }

    fun handleDogList(status: ResourceState, data: Race?, failure: Failure?){
        when(status){
            ResourceState.LOADING -> {
                pbCoupon.showProgress(true, activity)
            }
            ResourceState.SUCCESS -> {
                pbCoupon.showProgress(false, activity)
                data?.let{
                    displayRace(it)
                }
            }
            ResourceState.ERROR -> {
                pbCoupon.showProgress(false, activity)

                when(failure){
                    Failure.NetworkConnection ->{}
                    Failure.ServerError ->{
                        pbCoupon.showProgress(false, activity)
                        showMessage((failure as Failure.Error).errorMessage, context)
                        }
                    }
                }
                else -> {}
            }
        }

    fun initAdapter(){
        dogListAdapter = DogListAdapter{
            goToDetailImageDog(it)
        }
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.layoutManager = GridLayoutManager(context, 1)
        recycler_view.adapter = dogListAdapter
    }

    fun displayRace(race: Race?){
        race?.let {
            dogListAdapter.setList(it.message)
        }
    }

    private fun injectDependence(){
        DaggerDogListFragmentComponent.builder().build().inject(this)
    }

    fun initViewModel(){
        viewModel = getViewModel(viewModelFactory)
        viewModel.liveData.observe(this, androidx.lifecycle.Observer {
            it?.also {
                handleDogList(it.status, it.data, it.failure)
            }
        })
    }

    private fun goToDetailImageDog(it:String){
        val b = Bundle()
        b.putString("race", it)
        requireNavController().navigate(R.id.action_dogListFragment_to_dogListDetailsFragment,
            b,
            NavOptions.Builder()
                .setPopUpTo(R.id.dogListDetailsFragment, true)
                .build())
    }
}
