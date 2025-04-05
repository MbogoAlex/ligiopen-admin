package com.admin.ligiopen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.admin.ligiopen.ui.screens.auth.LoginViewModel
import com.admin.ligiopen.ui.screens.match.clubs.ClubAdditionViewModel
import com.admin.ligiopen.ui.screens.match.clubs.ClubsViewModel
import com.admin.ligiopen.ui.screens.match.fixtures.FixturesViewModel
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.HighlightsScreenViewModel
import com.admin.ligiopen.ui.screens.match.fixtures.fixtureDetails.commentary.EventUploadViewModel
import com.admin.ligiopen.ui.screens.match.location.LocationAdditionVewModel
import com.admin.ligiopen.ui.screens.match.location.LocationsViewModel
import com.admin.ligiopen.ui.screens.news.NewsDetailsViewModel
import com.admin.ligiopen.ui.screens.news.NewsViewModel
import com.admin.ligiopen.ui.screens.start.SplashViewModel

object AppViewModelFactory {
    val Factory = viewModelFactory {

        initializer {
            SplashViewModel(
                dbRepository = ligiopenApplication().container.dbRepository
            )
        }

        initializer {
            LoginViewModel(
                apiRepository = ligiopenApplication().container.apiRepository,
                dbRepository = ligiopenApplication().container.dbRepository,
                savedStateHandle = this.createSavedStateHandle()
            )
        }

        initializer {
            LocationsViewModel(
                apiRepository = ligiopenApplication().container.apiRepository,
                dbRepository = ligiopenApplication().container.dbRepository,
            )
        }

        initializer {
            LocationAdditionVewModel(
                apiRepository = ligiopenApplication().container.apiRepository,
                dbRepository = ligiopenApplication().container.dbRepository
            )
        }

        initializer {
            ClubsViewModel(
                apiRepository = ligiopenApplication().container.apiRepository,
                dbRepository = ligiopenApplication().container.dbRepository
            )
        }

        initializer {
            ClubAdditionViewModel(
                apiRepository = ligiopenApplication().container.apiRepository,
                dbRepository = ligiopenApplication().container.dbRepository
            )
        }

        initializer {
            FixturesViewModel(
                apiRepository = ligiopenApplication().container.apiRepository,
                dbRepository = ligiopenApplication().container.dbRepository
            )
        }

        initializer {
            EventUploadViewModel(
                apiRepository = ligiopenApplication().container.apiRepository,
                dbRepository = ligiopenApplication().container.dbRepository,
                savedStateHandle = this.createSavedStateHandle()
            )
        }

        initializer {
            HighlightsScreenViewModel(
                apiRepository = ligiopenApplication().container.apiRepository,
                dbRepository = ligiopenApplication().container.dbRepository,
                savedStateHandle = this.createSavedStateHandle()
            )
        }

        initializer {
            NewsViewModel(
                apiRepository = ligiopenApplication().container.apiRepository,
                dbRepository = ligiopenApplication().container.dbRepository
            )
        }

        initializer {
            NewsDetailsViewModel(
                apiRepository = ligiopenApplication().container.apiRepository,
                dbRepository = ligiopenApplication().container.dbRepository,
                savedStateHandle = this.createSavedStateHandle()
            )
        }

    }
}

fun CreationExtras.ligiopenApplication(): Ligiopen =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Ligiopen)