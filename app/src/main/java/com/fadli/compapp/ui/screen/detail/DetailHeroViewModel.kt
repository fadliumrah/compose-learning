package com.fadli.compapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadli.compapp.model.OrderHero
import com.fadli.compapp.data.HeroRepository
import com.fadli.compapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailHeroViewModel(
    private val repository: HeroRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderHero>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderHero>>
        get() = _uiState

    fun getHeroById(heroId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderHeroesById(heroId))
        }
    }

}