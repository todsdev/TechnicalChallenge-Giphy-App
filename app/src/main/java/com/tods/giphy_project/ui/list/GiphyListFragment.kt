package com.tods.giphy_project.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tods.giphy_project.R
import com.tods.giphy_project.data.model.giphy.GiphyModel
import com.tods.giphy_project.databinding.FragmentGiphyListBinding
import com.tods.giphy_project.state.ResourceState
import com.tods.giphy_project.ui.adapter.GiphyAdapter
import com.tods.giphy_project.ui.base.BaseFragment
import com.tods.giphy_project.util.hide
import com.tods.giphy_project.util.show
import com.tods.giphy_project.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class GiphyListFragment: BaseFragment<FragmentGiphyListBinding, GiphyListViewModel>() {
    override val viewModel: GiphyListViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGiphyListBinding =
        FragmentGiphyListBinding.inflate(inflater, container, false)
    private val giphyAdapter by lazy { GiphyAdapter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configDataCollection()
        configRecyclerView()
        configClickAdapter()
    }

    private fun configClickAdapter() {
        giphyAdapter.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.share))
                .setMessage(getString(R.string.share_))
                .setPositiveButton(getString(R.string.yes)) { _, _ ->

                }.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
        giphyAdapter.setOnLongClickListener {
            viewModel.insert(it)
            toast(getString(R.string.favorite_successfully))
        }
    }

    private fun configRecyclerView() = with(binding) {
        recyclerGiphyList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = giphyAdapter
        }
    }

    private fun configDataCollection() = lifecycleScope.launch {
        viewModel.list.collect { result ->
            when(result) {
                is ResourceState.Success -> {
                    result.data?.let { values ->
                        binding.progressGiphyList.hide()
                        giphyAdapter.giphies = values.data.toList()
                    }
                }
                is ResourceState.Error -> {
                    binding.progressGiphyList.hide()
                    result.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("GiphyListFragment").e("Error: $message")
                    }
                }
                is ResourceState.Loading -> {
                    binding.progressGiphyList.show()
                }
                else -> { }
            }
        }
    }
}