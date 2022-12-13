package com.tods.giphy_project.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tods.giphy_project.data.model.GiphyResponse
import com.tods.giphy_project.data.model.giphy.GiphyModel
import com.tods.giphy_project.repository.GiphyRepository
import com.tods.giphy_project.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class GiphySearchViewModel @Inject constructor(private val repository: GiphyRepository): ViewModel() {
    private val _search = MutableStateFlow<ResourceState<GiphyResponse>>(ResourceState.Loading())
    val search: StateFlow<ResourceState<GiphyResponse>> = _search

    fun fetch(query: String) = viewModelScope.launch {
        safeFetch(query)
    }

    private suspend fun safeFetch(query: String) {
        _search.value = ResourceState.Loading()
        try {
            val response = repository.getGifsBySearch(query)
            _search.value = handleResponse(response)
        } catch(t: Throwable) {
            when(t) {
                is IOException -> _search.value = ResourceState.Error("An error with internet connection occurred")
                else -> _search.value = ResourceState.Error("An error converting data occurred")
            }
        }
    }

    private fun handleResponse(response: Response<GiphyResponse>): ResourceState<GiphyResponse> {
        if(response.isSuccessful) {
            response.body().let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }

    fun insert(giphy: GiphyModel) = viewModelScope.launch {
        repository.insert(giphy)
    }
}