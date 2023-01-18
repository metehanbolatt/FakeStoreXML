package com.metehanbolat.fakestorexml.presentation.allproduct

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.metehanbolat.fakestorexml.MainUIState
import com.metehanbolat.fakestorexml.R
import com.metehanbolat.fakestorexml.connectivity.NetworkConnectivityLD
import com.metehanbolat.fakestorexml.connectivity.Status
import com.metehanbolat.fakestorexml.databinding.FragmentAllProductBinding
import com.metehanbolat.fakestorexml.presentation.MainViewModel
import com.metehanbolat.fakestorexml.util.gone
import com.metehanbolat.fakestorexml.util.inflate
import com.metehanbolat.fakestorexml.util.observeTextChanges
import com.metehanbolat.fakestorexml.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@FlowPreview
@AndroidEntryPoint
class AllProductFragment : Fragment(R.layout.fragment_all_product) {

    private val viewModel: AllProductViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private val binding by inflate(FragmentAllProductBinding::bind)

    private val allProductAdapter by lazy { AllProductAdapter() }

    @Inject
    lateinit var connectivityObserver: NetworkConnectivityLD

    private var isNetworkAvailable = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        bindUI()

        viewModel.readFromDataStore.observe(viewLifecycleOwner) {
            println("Saat: $it")
            println("Güncel: ${getLocalCurrentTime()}")
        }

        viewModel.productListFromDatabase.observe(viewLifecycleOwner) {

        }

    }

    private fun bindViewModel() {
        viewModel.productUIDataState.observe(viewLifecycleOwner) {
            if (isNetworkAvailable) {
                when (it) {
                    is MainUIState.Loading -> {
                        contentVisible(isContentVisible = false)
                    }
                    is MainUIState.Error -> {
                        serviceError()
                    }
                    is MainUIState.Success -> {
                        contentVisible(isContentVisible = true)
                        allProductAdapter.submitList(it.data)
                        allProductAdapter.setOnItemClickListener { productUIData ->
                            val action =
                                AllProductFragmentDirections.actionAllProductFragmentToProductDetailFragment(
                                    id = productUIData.id
                                )
                            findNavController().navigate(action)
                        }
                        binding.recyclerView.adapter = allProductAdapter
                    }
                }
            }
        }
        mainViewModel.networkConnectivity.observe(viewLifecycleOwner) {
            isConnectToInternet(it)
        }
    }

    private fun bindUI() {
        connectivityObserver.observe(viewLifecycleOwner) { networkStatus ->
            networkStatus?.let {
                when (it) {
                    Status.Available -> {
                        mainViewModel.setNetworkConnectivity(networkConnectivity = true)
                        observeSearchTextChanges()
                    }
                    Status.Unavailable -> {
                        mainViewModel.setNetworkConnectivity(networkConnectivity = false)
                    }
                    Status.Lost -> {
                        mainViewModel.setNetworkConnectivity(networkConnectivity = false)
                    }
                }
            }
        }

        binding.refreshButton.setOnClickListener {
            allProductAdapter.clearList()
            viewModel.getAllProducts()
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            viewModel.saveToDataStore(LocalDateTime.now().format(formatter))
        }
    }

    private fun observeSearchTextChanges() {
        binding.searchInput.observeTextChanges()
            .debounce(300L)
            .onEach {
                if (it.isBlank()) {
                    viewModel.getAllProducts()
                } else {
                    viewModel.getLimitedProducts(it)
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun contentVisible(isContentVisible: Boolean) {
        binding.apply {
            loadingView.isVisible = !isContentVisible
            recyclerView.isVisible = isContentVisible
            binding.networkErrorView.gone()
        }
    }

    private fun serviceError() {
        binding.apply {
            networkErrorText.text = resources.getString(R.string.service_error_text)
            networkErrorView.visible()
            loadingView.gone()
            recyclerView.gone()
        }
    }

    private fun isConnectToInternet(isConnect: Boolean) {
        isNetworkAvailable = isConnect
        binding.apply {
            recyclerView.isVisible = isConnect
            networkErrorText.text = resources.getString(R.string.no_internet_text)
            networkErrorView.isVisible = !isConnect
        }
    }

    private fun getLocalCurrentTime(): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return LocalDateTime.now().format(formatter)
    }
}