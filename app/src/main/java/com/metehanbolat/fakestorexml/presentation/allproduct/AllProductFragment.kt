package com.metehanbolat.fakestorexml.presentation.allproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.metehanbolat.fakestorexml.MainUIState
import com.metehanbolat.fakestorexml.databinding.FragmentAllProductBinding
import com.metehanbolat.fakestorexml.util.observeTextChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@AndroidEntryPoint
class AllProductFragment : Fragment() {

    private var _binding: FragmentAllProductBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllProductViewModel by viewModels()

    private lateinit var allProductAdapter: AllProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllProductBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        observeSearchTextChanges()
    }

    private fun bindViewModel() {
        viewModel.mainUIState.observe(viewLifecycleOwner) {
            when(it) {
                is MainUIState.Loading -> println("Loading")
                is MainUIState.Error -> println("Error: ${resources.getString(it.message)}")
                is MainUIState.Success -> {
                    allProductAdapter = AllProductAdapter(it.data)
                    binding.recyclerView.adapter = allProductAdapter
                }
            }
        }
    }

    private fun observeSearchTextChanges() {
        binding.searchInput.observeTextChanges()
            //.filter { it.isNotBlank() }
            .debounce(300L)
            .distinctUntilChanged()
            .onEach { viewModel.getLimitedProducts(it) }
            .launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}