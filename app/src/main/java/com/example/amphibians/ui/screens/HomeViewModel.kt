package com.example.amphibians.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.data.AmphibiansRepository
import com.example.amphibians.networking.Amphibian
import com.example.amphibians.networking.AmphibiansApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    object LoadingState : HomeUiState
    object ErrorState: HomeUiState
    data class ListState(val amphibianList: List<Amphibian>): HomeUiState
}

class HomeViewModel : ViewModel() {
    // initial state is loading
    private var _amphibianState = MutableStateFlow<HomeUiState>(HomeUiState.LoadingState)
    val amphibianState = _amphibianState.asStateFlow()

    private val repository = AmphibiansRepository()

    init {
        getAmphibian()
    }

    private fun getAmphibian() {
        viewModelScope.launch {
            val resultList = repository.getAmphibiansRepo()

            if(resultList == null) {
                _amphibianState.value = HomeUiState.ErrorState
            } else {
                _amphibianState.value = HomeUiState.ListState(resultList)
            }

        }
    }
}