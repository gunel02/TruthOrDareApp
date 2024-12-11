package com.example.app.models


data class GetQuestionsModel(
    val truth: List<GetTruthQuestionsModel?>?,
    val dare: List<GetDareQuestionsModel?>?,
)
