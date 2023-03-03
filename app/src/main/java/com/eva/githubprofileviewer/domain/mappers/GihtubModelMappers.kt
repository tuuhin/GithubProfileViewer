package com.eva.githubprofileviewer.domain.mappers

import com.eva.githubprofileviewer.domain.models.GithubLanguageModel
import com.eva.githubprofileviewer.domain.models.LanguageGraphModel
import com.eva.githubprofileviewer.domain.models.RepositoryLanguagesModel
import com.eva.githubprofileviewer.domain.models.StaredLanguageGraphModel

fun List<GithubLanguageModel>.starsGraphData(): List<StaredLanguageGraphModel> {

    val starMap: MutableMap<RepositoryLanguagesModel, Int> = mutableMapOf()
    forEach { model ->
        if (model.languagesModel.name.isNotEmpty()) {
            val language = model.languagesModel
            val prev = starMap.getOrDefault(language, 0)
            starMap[language] = prev + model.starsCount
        }
    }
    val total = starMap.values.sumOf { it }.toFloat()

    return starMap.map { entry ->
        StaredLanguageGraphModel(
            entry.key,
            entry.value,
            percentage = entry.value.toFloat() / total
        )
    }
}

fun List<GithubLanguageModel>.languageGraphData(): List<LanguageGraphModel> {
    val languageMap: MutableMap<RepositoryLanguagesModel, Int> = mutableMapOf()
    forEach { model ->
        if (model.languagesModel.name.isNotEmpty()) {
            val language = model.languagesModel
            val prev = languageMap.getOrDefault(language, 0)
            languageMap[language] = prev + 1
        }

    }
    val total = (languageMap.values.sumOf { it }).toFloat()
    return languageMap.map { entry ->
        LanguageGraphModel(
            entry.key,
            entry.value,
            percentage = entry.value.toFloat() / total
        )
    }
}