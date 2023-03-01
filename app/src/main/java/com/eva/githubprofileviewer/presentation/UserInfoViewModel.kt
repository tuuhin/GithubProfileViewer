package com.eva.githubprofileviewer.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.models.GitHubUserModel
import com.eva.githubprofileviewer.domain.models.GithubGraphDataModel
import com.eva.githubprofileviewer.domain.repository.GitHubUserInfoRepository
import com.eva.githubprofileviewer.domain.useCases.ReArrangeRepositoryUseCase
import com.eva.githubprofileviewer.utils.RepositoryArrangement
import com.eva.githubprofileviewer.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class UserInfoViewModel(
    private val repository: GitHubUserInfoRepository,
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        saveStateHandle.get<String>("userId")?.let { username ->
            getUserData(username)
            getRepositoryData(username)
            getGraphInformation(username)
        }
    }


    var repositoryState = mutableStateOf<ShowContent<List<GitHubRepositoryModel>>>(
        ShowContent(
            isLoading = true,
            content = emptyList()
        )
    )
        private set

    var graphState = mutableStateOf<ShowContent<GithubGraphDataModel>>(
        ShowContent(
            isLoading = true,
            content = null
        )
    )
        private set

    var userInfoState = mutableStateOf<ShowContent<GitHubUserModel>>(
        ShowContent(
            isLoading = true,
            content = null
        )
    )
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()

    var eventFlow = _uiEvent.asSharedFlow()

    private var getUserJob: Job? = null
    private var getRepositoryJob: Job? = null
    private var graphJob: Job? = null


    fun rearrangeRepository(arrangement: RepositoryArrangement) {
        if (repositoryState.value.content != null) {
            repositoryState.value = repositoryState.value.copy(
                content = ReArrangeRepositoryUseCase(
                    repositories = repositoryState.value.content!!
                ).invoke(arrangement)
            )
        }
    }

    private fun getUserData(username: String) {
        getUserJob?.cancel()
        getUserJob = viewModelScope.launch(Dispatchers.IO) {
            repository
                .getUserInformation(username)
                .cancellable()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            userInfoState.value = userInfoState.value.copy(
                                isLoading = false,
                                content = result.data
                            )
                        }
                        is Resource.Error -> {
                            _uiEvent.emit(
                                UiEvent.ShowSnackBar(
                                    result.message ?: "Error occurred cannot get user data"
                                )
                            )
                            userInfoState.value = userInfoState.value.copy(
                                isLoading = false,
                                content = null
                            )
                        }
                        is Resource.Loading -> {

                        }
                    }
                }.launchIn(this)

        }
    }

    private fun getGraphInformation(username: String) {
        graphJob?.cancel()
        graphJob = viewModelScope.launch(Dispatchers.IO) {
            repository
                .getGraphData(username)
                .cancellable()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            graphState.value =
                                graphState.value.copy(isLoading = false, content = result.data)
                        }
                        is Resource.Error -> {
                            _uiEvent.emit(
                                UiEvent.ShowSnackBar(
                                    result.message ?: "Error occurred cannot get graph data"
                                )
                            )
                            graphState.value = graphState.value.copy(
                                isLoading = false,
                                content = null
                            )
                        }
                        is Resource.Loading -> {

                        }
                    }
                }.launchIn(this)
        }
    }

    private fun getRepositoryData(username: String) {
        getRepositoryJob?.cancel()
        getRepositoryJob = viewModelScope.launch(Dispatchers.IO) {
            repository
                .getRepoInformation(username, 20)
                .cancellable()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            repositoryState.value = repositoryState.value.copy(
                                isLoading = false,
                                content = result.data
                            )
                        }
                        is Resource.Error -> {
                            _uiEvent.emit(
                                UiEvent.ShowSnackBar(
                                    result.message ?: "Error Occurred cannot get repositories data"
                                )
                            )
                            repositoryState.value = repositoryState.value.copy(
                                isLoading = false,
                                content = null
                            )
                        }

                        is Resource.Loading -> {

                        }
                    }
                }.launchIn(this)
        }
    }

}