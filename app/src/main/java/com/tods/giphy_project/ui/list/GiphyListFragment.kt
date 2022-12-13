package com.tods.giphy_project.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tods.giphy_project.databinding.FragmentGiphyListBinding
import com.tods.giphy_project.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GiphyListFragment: BaseFragment<FragmentGiphyListBinding, GiphyListViewModel>() {
    override val viewModel: GiphyListViewModel by viewModels()
    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGiphyListBinding =
        FragmentGiphyListBinding.inflate(inflater, container, false)
}