package com.example.app.dto

import com.example.app.models.GetQuestionsModel
import kotlinx.serialization.Serializable

@Serializable
data class GetQuestionsDTO(
    var truth: List<GetTruthQuestionsDTO?>?,
    var dare: List<GetDareQuestionsDTO?>?

) {
    fun toGetQuestionsModel(): GetQuestionsModel {
        return GetQuestionsModel(
            truth = truth?.map { it?.toGetTruthQuestionsModel() } ?: emptyList(),
            dare = dare?.map { it?.toGetDareQuestionsModel() } ?: emptyList()
        )
    }
}

