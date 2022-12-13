package com.tods.giphy_project.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tods.giphy_project.data.model.giphy.GiphyModel
import com.tods.giphy_project.repository.GiphyRepository
import com.tods.giphy_project.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiphyFavoriteViewModel @Inject constructor(private val repository: GiphyRepository): ViewModel() {
    private val _favorite = MutableStateFlow<ResourceState<List<GiphyModel>>>(ResourceState.Empty())
    val favorite: StateFlow<ResourceState<List<GiphyModel>>> = _favorite

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        repository.getAll().collectLatest { favorites ->
            if(favorites.isNullOrEmpty()) {
                _favorite.value = ResourceState.Empty()
            } else {
                _favorite.value = ResourceState.Success(favorites)
            }
        }
    }

    fun delete(giphy: GiphyModel) = viewModelScope.launch {
        repository.delete(giphy)
    }
}