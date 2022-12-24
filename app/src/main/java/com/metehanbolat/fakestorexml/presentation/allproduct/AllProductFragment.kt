package com.metehanbolat.fakestorexml.presentation.allproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.metehanbolat.domain.model.ProductDbModel
import com.metehanbolat.fakestorexml.MainUIState
import com.metehanbolat.fakestorexml.R
import com.metehanbolat.fakestorexml.connectivity.ConnectivityObserver
import com.metehanbolat.fakestorexml.connectivity.NetworkConnectivityObserver
import com.metehanbolat.fakestorexml.databinding.FragmentAllProductBinding
import com.metehanbolat.fakestorexml.presentation.MainViewModel
import com.metehanbolat.fakestorexml.util.gone
import com.metehanbolat.fakestorexml.util.observeTextChanges
import com.metehanbolat.fakestorexml.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlin.random.Random

@FlowPreview
@AndroidEntryPoint
class AllProductFragment : Fragment() {

    private var _binding: FragmentAllProductBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllProductViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var connectivityObserver: ConnectivityObserver
    private lateinit var allProductAdapter: AllProductAdapter

    private var isNetworkAvailable = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        bindUI()

        binding.roomTestButton.setOnLongClickListener {
            viewModel.readAllProductFromDatabase()
            true
        }

        binding.roomTestButton.setOnClickListener {
            viewModel.addProductsToDatabase(
                ProductDbModel(
                    id = Random.nextInt(),
                    productName = "Skirt",
                    productImageUrl = "Dummy Url"
                )
            )
        }

        viewModel.productListFromDatabase.observe(viewLifecycleOwner) {
            println("Observe productListFromDatabase: $it")
        }

    }

    private fun bindViewModel() {
        viewModel.mainUIState.observe(viewLifecycleOwner) {
            if (isNetworkAvailable) {
                when(it) {
                    is MainUIState.Loading -> {
                        contentVisible(isContentVisible = false)
                    }
                    is MainUIState.Error -> {
                        serviceError()
                    }
                    is MainUIState.Success -> {
                        contentVisible(isContentVisible = true)
                        allProductAdapter = AllProductAdapter(it.data)
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
        connectivityObserver = NetworkConnectivityObserver(requireContext())
        connectivityObserver.observe().onEach { networkStatus ->
            when (networkStatus) {
                ConnectivityObserver.Status.Available -> {
                    mainViewModel.setNetworkConnectivity(networkConnectivity = true)
                    observeSearchTextChanges()
                }
                else -> {
                    mainViewModel.setNetworkConnectivity(networkConnectivity = false)
                }
            }
        }.launchIn(lifecycleScope)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}