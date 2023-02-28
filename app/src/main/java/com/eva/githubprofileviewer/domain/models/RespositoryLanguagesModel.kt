package com.eva.githubprofileviewer.domain.models

import kotlin.math.roundToInt

data class RepositoryLanguagesModel(
    val name: String,
    val colorCode: String,
    val size: Float = 0f,
    val percentage: Float = 0f
) {
    val getPercentageString: String = "${(((percentage * 1000).roundToInt().toFloat() / 10))} %"

}