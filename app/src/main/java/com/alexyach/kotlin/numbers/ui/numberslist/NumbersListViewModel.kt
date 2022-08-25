package com.alexyach.kotlin.numbers.ui.numberslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexyach.kotlin.numbers.model.NumberModel
import com.alexyach.kotlin.numbers.repository.local.RepositoryLocalImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NumbersListViewModel : ViewModel() {


    /** Live Data */
    private val numbersListLiveData: MutableLiveData<List<NumberModel>> =
        MutableLiveData<List<NumberModel>>()
    fun getNumberListLiveData() : MutableLiveData<List<NumberModel>> {
        return numbersListLiveData
    }

    fun getNumbersList() {
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryLocalImpl().getNumbersAll {dataList ->
                numbersListLiveData.postValue(dataList)
            }
        }
    }

    fun deleteAllRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryLocalImpl().deleteAllFromLocal()
        }

        numbersListLiveData.postValue(emptyList())
    }

}