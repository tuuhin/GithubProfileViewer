package com.eva.githubprofileviewer.domain.models

import com.eva.githubprofileviewer.domain.mappers.languageGraphData
import com.eva.githubprofileviewer.domain.mappers.starsGraphData

class GithubGraphDataModel(
    langInfoModel: List<GithubLanguageModel>
) {
    val topLangGraphData: List<LanguageGraphModel> =
        langInfoModel.languageGraphData().sortedByDescending { it.percentage }

    val starsPerLangGraphData: List<StaredLanguageGraphModel> =
        langInfoModel.starsGraphData().sortedByDescending { it.percentage }
}

