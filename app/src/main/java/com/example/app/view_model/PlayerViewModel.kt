package com.example.app.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.app.data.PlayerDatabase
import com.example.app.data_class.PlayersData
import com.example.app.models.GetQuestionsModel
import com.example.app.repository.PlayerRepository
import com.example.app.utility.Utils.parseQuestionsJSON
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    var currentPlayerIndex : Int = 0
    var currentQuestionIndex : Int = 0


    val readAllData: LiveData<List<PlayersData>>
    private val repository: PlayerRepository

    private  var _questionsModel: GetQuestionsModel? = null
    val questionsModel: GetQuestionsModel? get() = _questionsModel

    init {
        val playerDao = PlayerDatabase.getDatabase(application).playerDao()
        repository = PlayerRepository(playerDao)
        readAllData = repository.readAllData

        loadQuestions()
    }

    fun addUser(playersData: PlayersData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(playersData)
        }
    }

    fun loadQuestions(){
        viewModelScope.launch {
            _questionsModel = parseQuestionsJSON(getApplication())
        }
    }


    fun updateUser(playersData: PlayersData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(playersData)
        }
    }

    fun deleteUser(playersData: PlayersData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(playersData)
        }
    }

    fun savePLayersName (playersName: PlayersData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.savePLayersName(playersName)
        }
    }


}
