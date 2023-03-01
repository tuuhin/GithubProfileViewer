package com.eva.githubprofileviewer.domain.useCases

import com.eva.githubprofileviewer.domain.models.GitHubRepositoryModel
import com.eva.githubprofileviewer.utils.RepositoryArrangement

class ReArrangeRepositoryUseCase(
    private val repositories: List<GitHubRepositoryModel>
) {
    operator fun invoke(arrangement: RepositoryArrangement): List<GitHubRepositoryModel> {
       return  when (arrangement) {
            is RepositoryArrangement.ByStars -> {
                repositories.sortedByDescending { it.starsCount }
            }
            is RepositoryArrangement.ByFork -> {
                repositories.sortedByDescending { it.forksCount }
            }
            is RepositoryArrangement.BySize -> {
                repositories.sortedByDescending { it.size }
            }
        }
    }
}