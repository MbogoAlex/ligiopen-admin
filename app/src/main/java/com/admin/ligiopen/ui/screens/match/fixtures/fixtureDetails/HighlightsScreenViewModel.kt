package com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admin.ligiopen.data.network.enums.LoadingStatus
import com.admin.ligiopen.data.network.repository.ApiRepository
import com.admin.ligiopen.data.room.db.userAccountDt
import com.admin.ligiopen.data.room.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HighlightsScreenViewModel(
    private val apiRepository: ApiRepository,
    private val dbRepository: DBRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _uiState = MutableStateFlow(HighlightsScreenUiData())
    val uiState: StateFlow<HighlightsScreenUiData> = _uiState.asStateFlow()

    private val postMatchId: String? = savedStateHandle[HighlightsScreenDestination.postMatchId]
    private val fixtureId: String? = savedStateHandle[HighlightsScreenDestination.fixtureId]

    private fun getMatchCommentary() {
        viewModelScope.launch {
            try {
                val response = apiRepository.getFullMatchDetails(
                    token = uiState.value.userAccount.token,
                    postMatchAnalysisId = postMatchId!!.toInt()
                )

                if(response.isSuccessful) {
                    _uiState.update {
                        it.copy(
                            commentaries = response.body()?.data?.minuteByMinuteCommentary!!,
                            loadingStatus = LoadingStatus.SUCCESS
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            loadingStatus = LoadingStatus.FAIL
                        )
                    }
                    Log.e("matchHighlights", "response: $response")
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loadingStatus = LoadingStatus.FAIL
                    )
                }
                Log.e("matchHighlights", "e: $e")
            }
        }
    }

    fun getInitialData() {
        viewModelScope.launch {
            while (uiState.value.userAccount.id == 0) {
                delay(1000)
            }
            getMatchCommentary()
        }
    }

    fun resetStatus() {
        _uiState.update {
            it.copy(
                loadingStatus = LoadingStatus.LOADING
            )
        }
    }


    private fun loadUserData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dbRepository.getUsers().collect {users ->
                    _uiState.update {
                        it.copy(
                            userAccount = userAccountDt.takeIf { users.isEmpty() } ?: users[0],
                            postMatchId = postMatchId,
                            fixtureId = fixtureId
                        )
                    }
                }
            }
        }
    }

    init {
        loadUserData()
        getInitialData()
    }
}