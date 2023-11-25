package com.starwarscharacter.app.features.planet.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.starwarscharacter.app.features.planet.domain.entity.Planets
import com.starwarscharacter.app.features.planet.domain.usecase.GetPlanetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor (
    private val getstarShipsUseCase: GetPlanetsUseCase
): ViewModel() {
    private val _starShipsState: MutableStateFlow<PagingData<Planets>> = MutableStateFlow(value = PagingData.empty())
    val starShipsState: MutableStateFlow<PagingData<Planets>> get() = _starShipsState

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