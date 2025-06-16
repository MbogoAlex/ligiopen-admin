package com.admin.ligiopen.ui.screens.users

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.admin.ligiopen.data.network.repository.ApiRepository
import com.admin.ligiopen.data.room.repository.DBRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ClubSearchViewModel(
    private val apiRepository: ApiRepository,
    private val dbRepository: DBRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _uiState = MutableStateFlow(ClubSearchUiData())
    val uiState: StateFlow<ClubSearchUiData> = _uiState.asStateFlow()



}