package com.starwarscharacter.app.features.character.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.starwarscharacter.app.features.character.domain.entity.Character
import com.starwarscharacter.app.features.character.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CharacterViewModel @Inject constructor (
    private val getCharactersUseCase: GetCharactersUseCase
): ViewModel() {
    private val _characterState: MutableStateFlow<PagingData<Character>> = MutableStateFlow(value = PagingData.empty())
    val charactersState: MutableStateFlow<PagingData<Character>> get() = _characterState

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            getCharactersUseCase.execute(Unit)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .onCompletion {
                    Log.d("CharacterViewModel", "Data Fetching Complete")
                }
                .collect {
                    _characterState.value = it
                }
        }
    }

}



