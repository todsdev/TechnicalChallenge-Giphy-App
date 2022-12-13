package com.tods.giphy_project.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tods.giphy_project.databinding.FragmentGiphySearchBinding
import com.tods.giphy_project.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GiphySearchFragment: BaseFragment<FragmentGiphySearchBinding, GiphySearchViewModel>() {
    override val viewModel: GiphySearchViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGiphySearchBinding =
        FragmentGiphySearchBinding.inflate(inflater, container, false)
}