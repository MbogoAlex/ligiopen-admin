package com.admin.ligiopen.ui.screens.match.clubs

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admin.ligiopen.data.network.enums.LoadingStatus
import com.admin.ligiopen.data.network.models.club.ChangeClubStatusRequestBody
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

class ClubDetailsViewModel(
    private val apiRepository: ApiRepository,
    private val dbRepository: DBRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _uiState = MutableStateFlow(ClubDetailsUiData())
    val uiState: StateFlow<ClubDetailsUiData> = _uiState.asStateFlow()

    private val clubId: String? = savedStateHandle[ClubDetailsScreenDestination.clubId]

    private fun getClub() {
        viewModelScope.launch {
            try {
               val response = apiRepository.getClub(
                   token = uiState.value.userAccount.token,
                   clubId = clubId!!.toInt()
               )

               if(response.isSuccessful) {
                   _uiState.update {
                       it.copy(
                           club = response.body()?.data!!
                       )
                   }
               } else {
                   val errorString = response.errorBody()?.string()
                   Log.e("getClub", "ResponseErr: $errorString")
               }
            } catch (e: Exception) {
                Log.e("getClub", "Exception: $e")
            }
        }
    }

    fun changeClubStatus(status: String) {
        _uiState.update {
            it.copy(
                loadingStatus = LoadingStatus.LOADING
            )
        }
        viewModelScope.launch {
            try {
                val changeClubStatusRequestBody = ChangeClubStatusRequestBody(
                    clubId = uiState.value.club.clubId,
                    clubStatus = status
                )

                val response = apiRepository.changeClubStatus(
                    token = uiState.value.userAccount.token,
                    changeClubStatusRequestBody = changeClubStatusRequestBody
                )

                if(response.isSuccessful) {
                    getClub()
                    _uiState.update {
                        it.copy(
                            loadingStatus = LoadingStatus.SUCCESS
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            loadingStatus = LoadingStatus.FAIL
                        )
                    }
                    val errorString = response.errorBody()?.string()
                    Log.e("changeClubStatus: ", "RESPONSE: $errorString")
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loadingStatus = LoadingStatus.FAIL
                    )
                }
                Log.e("changeClubStatus: ", "EXCEPTION: $e")
            }
        }
    }


    fun getInitialData() {
        viewModelScope.launch {
            while (uiState.value.userAccount.id == 0) {
                delay(1000)
            }
            getClub()
        }
    }

    private fun loadUserData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dbRepository.getUsers().collect {users ->
                    _uiState.update {
                        it.copy(
                            userAccount = userAccountDt.takeIf { users.isEmpty() } ?: users[0]
                        )
                    }
                }
            }
        }
    }

    fun resetStatus() {
        _uiState.update {
            it.copy(
                loadingStatus = LoadingStatus.INITIAL,
            )
        }
    }

    init {
        loadUserData()
        getInitialData()
    }
}