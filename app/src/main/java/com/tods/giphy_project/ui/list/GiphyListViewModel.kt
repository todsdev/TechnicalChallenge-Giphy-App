package com.tods.giphy_project.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tods.giphy_project.data.model.GiphyResponse
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
class GiphyListViewModel @Inject constructor(private val repository: GiphyRepository): ViewModel() {
    private val _list = MutableStateFlow<ResourceState<GiphyResponse>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<GiphyResponse>> = _list

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        safeFetch()
    }

    private suspend fun safeFetch() {
        try {
            val response = repository.getGifsByTrending()
            _list.value = handleResponse(response)
        } catch(t: Throwable) {
            when(t) {
                is IOException -> _list.value = ResourceState.Error("An error with internet connection occurred")
                else -> _list.value = ResourceState.Error("An error converting data occurred")
            }
        }
    }

    private fun handleResponse(response: Response<GiphyResponse>): ResourceState<GiphyResponse> {
        if (response.isSuccessful) {
            response.body().let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }
}