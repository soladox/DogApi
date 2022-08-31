package com.proyecto.dogapi.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.dogapi.R
import com.proyecto.dogapi.di.component.DaggerDogListNewDetailsFragmentComponent
import com.proyecto.dogapi.presentation.adapter.DogListNewDetailsAdapter
import com.proyecto.dogapi.presentation.viewModel.DogListNewDetailsViewModel
import com.proyecto.dogapi.utils.Failure
import com.proyecto.dogapi.utils.ResourceState
import com.proyecto.dogapi.utils.ViewModelFactory
import com.proyecto.dogapi.utils.extension.requireNavController
import com.proyecto.dogapi.utils.extension.showMessage
import com.proyecto.dogapi.utils.extension.showProgress
import com.proyecto.dogapi.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_dog_list_new_details.*
import javax.inject.Inject

class DogListNewDetailsFragment : Fragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: DogListNewDetailsViewModel
    lateinit var adapter: DogListNewDetailsAdapter
    var race_name: String? = null
    lateinit var b: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependence()
        initViewModel()
        goBackNavBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dog_list_new_details,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        race_name = arguments?.getString("race")
        race_name?.let{
            viewModel.getImageRace(it)
        }

        iv_back.setOnClickListener { goBack() }

        rc_new_images.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                    viewModel.loadImageList()
                }
            }
        })
    }

    private fun injectDependence(){
        DaggerDogListNewDetailsFragmentComponent.builder().build().inject(this)
    }

    private fun handleDetailsDog(status: ResourceState, data: List<String>?, failure: Failure?){
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
        adapter = DogListNewDetailsAdapter {

        }
        rc_new_images.layoutManager = LinearLayoutManager(activity)
        rc_new_images.layoutManager = GridLayoutManager(context,2)
        rc_new_images.adapter = adapter
    }

    fun addImageList(images: List<String>){
        adapter.addList(images)
    }

    fun goBack(){
        b = Bundle()
        b.putString("race", race_name)
        requireNavController().navigate(
            R.id.action_dogListNewDetailsFragment_to_dogListDetailsFragments,b, NavOptions.Builder()
            .setPopUpTo(R.id.dogListDetailsFragment,true)
            .build())
    }

    fun goBackNavBar(){
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            race_name = arguments?.getString("race")
            race_name?.let{
                viewModel.getImageRace(it)
            }
            goBack()
        }
    }
}