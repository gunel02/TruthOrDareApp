package com.example.app.dto

import com.example.app.models.GetTruthQuestionsModel
import kotlinx.serialization.Serializable

@Serializable
data class GetTruthQuestionsDTO(
    var id: Int?,
    var question: String?,
    var level: String?

    ) {
    fun toGetTruthQuestionsModel(): GetTruthQuestionsModel {
        return GetTruthQuestionsModel(
            id = id ?: 0,
            question = question ?: "",
            level = level ?: ""
        )
    }
}
