package com.example.rickandmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.MyResponse
import com.example.rickandmorty.model.CharacterView
import com.example.rickandmorty.model.toCharacterView
import com.example.domain.usecase.FetchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
) : ViewModel() {

    private var _myList: MutableLiveData<List<CharacterView>> = MutableLiveData(emptyList())
    val myList: LiveData<List<CharacterView>> get() = _myList

    private var _showLoading = MutableLiveData(false)
    val showLoading: MutableLiveData<Boolean> get() = _showLoading

    private var _error: MutableLiveData<MyResponse.Error> = MutableLiveData()
    val error: LiveData<MyResponse.Error> get() = _error

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.postValue(true)
            val characterList = fetchCharactersUseCase.invoke()
            characterList.collect { myResponse ->
                when (myResponse) {
                    is MyResponse.Success -> {
                        _showLoading.postValue(false)
                        _myList.postValue(myResponse.data.map { it.toCharacterView() })
                    }
                    is MyResponse.Error -> {
                        _showLoading.postValue(false)
                        _error.postValue(MyResponse.Error(myResponse.error))
                    }
                    else -> {
                    }
                }
            }
        }
    }
}