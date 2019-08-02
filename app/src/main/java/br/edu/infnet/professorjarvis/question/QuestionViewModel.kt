package br.edu.infnet.professorjarvis.question

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionViewModel : ViewModel(){

    val text = MutableLiveData<String>()
    val options = MutableLiveData<List<String>>()
    val correctOption = MutableLiveData<Int>()
    val category = MutableLiveData<String>()

    val pageNumber = MutableLiveData<Int>().apply { value = 0 }

}