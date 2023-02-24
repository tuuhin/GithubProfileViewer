package com.eva.githubprofileviewer.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.domain.repository.GitHubUserInfoRepository
import com.eva.githubprofileviewer.utils.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserInfoViewModel(
    private val repository: GitHubUserInfoRepository
) : ViewModel() {


    init {
//        getRepoData()
        getUserData()
    }

    var repositoryState = mutableStateOf<ShowContent<List<GitHubRepositoryModel>>>(
        ShowContent(isLoading = true, content = emptyList())
    )
        private set

//    var githubUserData = MutableStateFlow<GitHubUserModel?>(null)
//        private set

    private var _uiEvent = MutableSharedFlow<UiEvent>()

    var eventFlow = _uiEvent.asSharedFlow()

//    private fun getRepoData() {
//        viewModelScope.launch {
//            repository.getUserInfo("tuuhin")
//                .onEach {
//                    when (it) {
//                        else -> {
//
//                        }
//                    }
//
//                }.launchIn(this)
//
//        }
//    }

    private fun getUserData() {
        viewModelScope.launch {
            repository.getUserRepoInformation("tuuhin", 20)
                .onEach {
                    Log.d("REPOSITORY", it.toString())
                    when (it) {

                        is Resource.Success -> {
                            repositoryState.value = ShowContent(
                                isLoading = false,
                                content = it.data
                            )
                        }

                        is Resource.Error -> {
                            _uiEvent.emit(UiEvent.ShowSnackBar("Error Occurred"))
                        }

                        is Resource.Loading -> {


//                            repositoryState.value =
//                                ShowContent(
//                                    isLoading = true,
//                                    content = emptyList()
//                                )
                        }

                    }
                }.launchIn(this)
        }
    }


}