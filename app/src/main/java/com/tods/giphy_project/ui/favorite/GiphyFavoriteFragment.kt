package com.tods.giphy_project.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tods.giphy_project.databinding.FragmentGiphyFavoriteBinding
import com.tods.giphy_project.ui.base.BaseFragment

class GiphyFavoriteFragment: BaseFragment<FragmentGiphyFavoriteBinding, GiphyFavoriteViewModel>() {
    override val viewModel: GiphyFavoriteViewModel by viewModels()

    override fun recoverViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGiphyFavoriteBinding =
        FragmentGiphyFavoriteBinding.inflate(inflater, container, false)
}