package com.eva.githubprofileviewer.presentation

data class ShowContent<T>(
    val isLoading:Boolean,
    val content:T?=null
)
