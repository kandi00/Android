package com.example.quizapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.model.QuestionModel
import com.example.quizapp.repository.Repository
import com.example.quizapp.utils.BackEndResponse
import kotlinx.coroutines.launch

class MainViewModel(private val repository : Repository) : ViewModel() {

    val myResponse: MutableLiveData<BackEndResponse<QuestionModel>> = MutableLiveData()
    fun getPost(){
        viewModelScope.launch() {
            val response = repository.getPost()
            myResponse.value = response.body()
            Log.i("taaag", response.body()?.response_code.toString())
        }
    }
}