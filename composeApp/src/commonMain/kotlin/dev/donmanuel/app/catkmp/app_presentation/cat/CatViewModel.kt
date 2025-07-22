package dev.donmanuel.app.catkmp.app_presentation.cat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.app.catkmp.domain.model.CatModel
import dev.donmanuel.app.catkmp.domain.repository.UiState
import dev.donmanuel.app.catkmp.domain.usecase.CatListUseCase
import dev.donmanuel.app.catkmp.domain.usecase.FavoriteCatUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatViewModel(
    private val catListUseCase: CatListUseCase,
    private val favoriteCatUseCase: FavoriteCatUseCase
) : ViewModel() {

    var hasLoaded = false

    private val _stateCatList = MutableStateFlow<UiState>(UiState.Init)
    val stateCatList: StateFlow<UiState> = _stateCatList.asStateFlow()

    private val _stateFavoriteCatsList = MutableStateFlow<UiState>(UiState.Init)
    val stateFavoriteCatsList: StateFlow<UiState> = _stateFavoriteCatsList.asStateFlow()

    fun getCats() {

        if (hasLoaded)
            return

        _stateCatList.value = UiState.Loading
        viewModelScope.launch {
            hasLoaded = true
            _stateCatList.value = catListUseCase()
        }
    }

    fun getFavoriteCats() {
        _stateFavoriteCatsList.value = UiState.Loading
        viewModelScope.launch {
            _stateFavoriteCatsList.value = favoriteCatUseCase.getFavoriteCats()
        }
    }

    fun saveFavoriteCat(catModel: CatModel) {
        viewModelScope.launch {
            favoriteCatUseCase.saveFavorite(catModel)
        }
    }

    fun deleteFavoriteCat(idCat: String) {
        viewModelScope.launch {
            favoriteCatUseCase.deleteFavoriteCats(idCat)
        }
    }
}