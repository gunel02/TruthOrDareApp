package com.example.app.dto

import com.example.app.models.GetDareQuestionsModel
import kotlinx.serialization.Serializable

@Serializable
data class GetDareQuestionsDTO(
    var id: Int?,
    var question: String?,
) {
    fun toGetDareQuestionsModel(): GetDareQuestionsModel {
        return GetDareQuestionsModel(
            id = id ?: 0,
            question = question ?: "",
        )
    }
}
