package com.example.amphibians.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibiansRepository
import com.example.amphibians.networking.Amphibian
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    object LoadingState : HomeUiState
    object ErrorState: HomeUiState
    data class ListState(val amphibianList: List<Amphibian>): HomeUiState
}

class HomeViewModel(
    private val repository: AmphibiansRepository
) : ViewModel() {
    // initial state is loading
    private var _amphibianState = MutableStateFlow<HomeUiState>(HomeUiState.LoadingState)
    val amphibianState = _amphibianState.asStateFlow()

   // private val repository = AmphibiansRepository()

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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.repository
                HomeViewModel(amphibiansRepository)
            }
        }
    }
}