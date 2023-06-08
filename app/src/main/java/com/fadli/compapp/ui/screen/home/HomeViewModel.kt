package com.fadli.compapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadli.compapp.data.HeroRepository
import com.fadli.compapp.model.OrderHero
import com.fadli.compapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HeroRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderHero>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderHero>>>
        get() = _uiState

    fun getAllHeroes() {
        viewModelScope.launch {
            repository.getAllHeroes()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderHeroes ->
                    _uiState.value = UiState.Success(orderHeroes)
                }
        }
    }
}