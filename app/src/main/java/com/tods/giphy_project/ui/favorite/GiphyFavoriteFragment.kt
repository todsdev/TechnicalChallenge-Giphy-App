package com.tods.giphy_project.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tods.giphy_project.R
import com.tods.giphy_project.databinding.FragmentGiphyFavoriteBinding
import com.tods.giphy_project.state.ResourceState
import com.tods.giphy_project.ui.adapter.GiphyAdapter
import com.tods.giphy_project.ui.base.BaseFragment
import com.tods.giphy_project.util.hide
import com.tods.giphy_project.util.show
import com.tods.giphy_project.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GiphyFavoriteFragment: BaseFragment<FragmentGiphyFavoriteBinding, GiphyFavoriteViewModel>() {
    override val viewModel: GiphyFavoriteViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGiphyFavoriteBinding =
        FragmentGiphyFavoriteBinding.inflate(inflater, container, false)
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
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, it.images.original.url)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(intent, getString(R.string.share_url))
                    startActivity(shareIntent)
                }.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
        giphyAdapter.setOnLongClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.delete))
                .setMessage(getString(R.string.delete_))
                .setPositiveButton(getString(R.string.yes)) { _, _ ->
                    viewModel.delete(it)
                }.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }

    private fun configRecyclerView() = with(binding) {
        recyclerGiphyFavorites.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = giphyAdapter
        }
    }

    private fun configDataCollection() = lifecycleScope.launch {
        viewModel.favorite.collect { result ->
            when(result) {
                is ResourceState.Success -> {
                    result.data?.let { values ->
                        binding.textEmpty.hide()
                        giphyAdapter.giphies = values.toList()
                    }
                }
                is ResourceState.Empty -> {
                    binding.textEmpty.show()
                }
                else -> { }
            }
        }
    }
}