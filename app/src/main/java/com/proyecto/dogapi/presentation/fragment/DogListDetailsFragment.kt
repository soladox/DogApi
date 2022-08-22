package com.proyecto.dogapi.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.dogapi.R
import com.proyecto.dogapi.presentation.adapter.DogListDetailsAdapter
import com.proyecto.dogapi.presentation.viewModel.DogListDetailsViewModel
import com.proyecto.dogapi.utils.Failure
import com.proyecto.dogapi.utils.ResourceState
import com.proyecto.dogapi.utils.ViewModelFactory
import com.proyecto.dogapi.utils.extension.requireNavController
import com.proyecto.dogapi.utils.extension.showMessage
import com.proyecto.dogapi.utils.extension.showProgress
import com.proyecto.dogapi.utils.getViewModel
import com.proyecto.dogapi.di.component.DaggerDogListDetailsFragmentComponent
import kotlinx.android.synthetic.main.fragment_dog_list_details.*
import javax.inject.Inject

class DogListDetailsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: DogListDetailsViewModel
    lateinit var adapter: DogListDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependence()
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        val race_name = arguments?.getString("race")
        race_name?.let{
            viewModel.getImageRace(it)
        }

        iv_back.setOnClickListener { goBack() }

        rc_images.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                    viewModel.loadImageList()
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dog_list_details,container,false)

    private fun injectDependence(){
        DaggerDogListDetailsFragmentComponent.builder().build().inject(this)
    }

    private fun handleDetailsDog(status: ResourceState,data: List<String>?, failure: Failure?){
        when(status){
            ResourceState.LOADING->{
                pb_details.showProgress(true,activity)
            }
            ResourceState.SUCCESS->{
                pb_details.showProgress(false,activity)
                data?.let{
                    addImageList(it)
                }
            }
            ResourceState.ERROR->{
                pb_details.showProgress(false,activity)
                showMessage((failure as Failure.Error).errorMessage,context)
            }
            else->{}
        }
    }

    private fun initViewModel(){
        viewModel = getViewModel(viewModelFactory)
        viewModel.liveData.observe(this, Observer {
            it?.also { handleDetailsDog(it.status, it.data, it.failure) }
        })
    }

    private fun initAdapter(){
        adapter = DogListDetailsAdapter {}
        rc_images.layoutManager = LinearLayoutManager(activity)
        rc_images.layoutManager = GridLayoutManager(context,1)
        rc_images.adapter = adapter
    }

    fun addImageList(images: List<String>){
        adapter.addList(images)
    }

    fun goBack(){
        requireNavController().navigate(R.id.action_dogListDetailsFragment_to_dogListFragment,null,NavOptions.Builder()
            .setPopUpTo(R.id.dogListFragment,true)
            .build())
    }
}