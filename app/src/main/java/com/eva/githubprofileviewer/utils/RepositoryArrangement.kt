package com.eva.githubprofileviewer.utils

sealed class RepositoryArrangement(val text: String) {
    object ByStars : RepositoryArrangement("By stars")
    object ByFork : RepositoryArrangement("By Fork")
    object BySize : RepositoryArrangement("By size")
}