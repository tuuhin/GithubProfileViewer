package com.eva.githubprofileviewer.presentation

sealed class UiEvent(val text:String){
    class ShowSnackBar(text:String):UiEvent(text = text)
}
