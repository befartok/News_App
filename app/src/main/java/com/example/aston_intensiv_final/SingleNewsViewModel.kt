package com.example.aston_intensiv_final

import androidx.lifecycle.ViewModel
import com.example.aston_intensiv_final.headlines_mvp.model.NewsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class NewsModel2(
//val id: Int? = null,
//val author: String? = null,
val title: String? = null,
val description: String? = null,
//val urlToImage: String? = null,
//val source: String? = null,
)

class SingleNewsViewModel: ViewModel() {

// TODO: исправить возврат на предыдущий фрагмент, а не базовый


    // Expose screen UI state
    private val _uiState = MutableStateFlow(SingletonNews.getProduct())
   // private val _uiState = MutableStateFlow(DiceUiState())
    //val uiState: StateFlow<DiceUiState> = _uiState.asStateFlow()
    val uiState: StateFlow<NewsModel?> = _uiState.asStateFlow()



    // Handle business logic
/*    fun rollDice() {
        _uiState.update { currentState ->
            currentState.copy(
                firstDieValue = Random.nextInt(from = 1, until = 7),
                secondDieValue = Random.nextInt(from = 1, until = 7),
                numberOfRolls = currentState.numberOfRolls + 1,
            )
        }
    }
    fun rollDice(_title: String, _description: String) {
        _uiState.update { currentState ->
            currentState?.copy(
                title = _title,
                description = _description


            )
        }
    }*/

}

object SingletonNews {
    //private var news: MutableList<NewsModel> = mutableListOf()

    private var news: NewsModel? = null
    fun addProduct(_news: NewsModel) {
        news = _news
    }

    fun clear(){
        news = null
    }

    fun getProduct(): NewsModel? {
        return news
    }


}