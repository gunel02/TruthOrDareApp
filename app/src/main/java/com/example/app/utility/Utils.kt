package com.example.app.utility

import android.content.Context
import com.example.app.dto.GetQuestionsDTO
import com.example.app.models.GetQuestionsModel
import kotlinx.serialization.json.Json

object Utils {
    fun loadJSONFromAsset(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).use { inputStream ->
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                String(buffer, Charsets.UTF_8)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun parseQuestionsJSON(context: Context): GetQuestionsModel? {
        return try {
            val jsonString = loadJSONFromAsset(context, "questions.json")
            val dto = Json.decodeFromString<GetQuestionsDTO>(jsonString ?: "")
            dto.toGetQuestionsModel()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

