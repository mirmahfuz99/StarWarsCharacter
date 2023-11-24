package com.starwarscharacter.app.features.starships.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.starwarscharacter.app.features.starships.domain.entity.StarShips
import com.starwarscharacter.app.features.starships.domain.usecase.GetStarShipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarShipsViewModel @Inject constructor (
    private val getstarShipsUseCase: GetStarShipsUseCase
): ViewModel() {
    private val _starShipsState: MutableStateFlow<PagingData<StarShips>> = MutableStateFlow(value = PagingData.empty())
    val starShipsState: MutableStateFlow<PagingData<StarShips>> get() = _starShipsState

    init {
        getStarShips()
    }

    private fun getStarShips() {
        viewModelScope.launch {
            getstarShipsUseCase.execute(Unit)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _starShipsState.value = it
                }
        }
    }

}