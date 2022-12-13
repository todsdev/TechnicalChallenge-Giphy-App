package com.tods.giphy_project.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.tods.giphy_project.R
import com.tods.giphy_project.databinding.FragmentGiphySearchBinding
import com.tods.giphy_project.state.ResourceState
import com.tods.giphy_project.ui.adapter.GiphyAdapter
import com.tods.giphy_project.ui.base.BaseFragment
import com.tods.giphy_project.util.Constants.DEFAULT_QUERY
import com.tods.giphy_project.util.Constants.LAST_SEARCH_QUERY
import com.tods.giphy_project.util.hide
import com.tods.giphy_project.util.show
import com.tods.giphy_project.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class GiphySearchFragment: BaseFragment<FragmentGiphySearchBinding, GiphySearchViewModel>() {
    override val viewModel: GiphySearchViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGiphySearchBinding =
        FragmentGiphySearchBinding.inflate(inflater, container, false)
    private val giphyAdapter by lazy { GiphyAdapter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configDataCollection()
        configRecyclerView()
        configClickAdapter()
        configQuerySearch(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY,
            binding.editSearchGiphy.editableText.trim().toString())
    }

    private fun configQuerySearch(savedInstanceState: Bundle?) {
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        configSearch(query)
    }

    private fun configSearch(query: String) = with(binding) {
        editSearchGiphy.setText(query)
        editSearchGiphy.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_GO) {
                configInitSearch()
                true
            } else {
                false
            }
        }
        editSearchGiphy.setOnKeyListener { _, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                configInitSearch()
                true
            } else {
                false
            }
        }
    }

    private fun configRecyclerView() = with(binding) {
        recyclerGiphySearch.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = giphyAdapter
        }
    }

    private fun configClickAdapter() {
        giphyAdapter.setOnClickListener {

        }
    }

    private fun configInitSearch() {
        binding.editSearchGiphy.editableText.trim().toString().let { query ->
            if(query.isNotEmpty()) {
                viewModel.fetch(query)
            }
        }
    }

    private fun configDataCollection() = lifecycleScope.launch {
        viewModel.search.collect { result ->
            when(result) {
                is ResourceState.Success -> {
                    binding.progressGiphySearch.hide()
                    result.data?.let { values ->
                        giphyAdapter.giphies = values.data.toList()
                    }
                }
                is ResourceState.Error -> {
                    binding.progressGiphySearch.hide()
                    result.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("SearchPokemonFragment").e("Error: $message")
                    }
                }
                is ResourceState.Loading -> {
                    binding.progressGiphySearch.show()
                }
                else -> { }
            }
        }
    }
}