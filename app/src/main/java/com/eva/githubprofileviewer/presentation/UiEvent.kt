package com.eva.githubprofileviewer.presentation

sealed class UiEvent(val text:String){
    class ShowDialog(title:String,val desc:String?):UiEvent(text = title)
    class ShowSnackBar(text:String):UiEvent(text = text)
}
