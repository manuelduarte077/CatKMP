package dev.donmanuel.app.catkmp.data.local.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dev.donmanuel.app.catkmp.domain.model.CatModel

object CatLocalRepository {

    private val _selectedCat = mutableStateOf<CatModel?>(null)
    val selectedCat: State<CatModel?> = _selectedCat

    fun selectCat(cat: CatModel) {
        _selectedCat.value = cat
    }
}