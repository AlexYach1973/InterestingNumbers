package com.alexyach.kotlin.numbers.ui.numberdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexyach.kotlin.numbers.model.NumberModel
import com.alexyach.kotlin.numbers.repository.ICallbackResponse
import com.alexyach.kotlin.numbers.repository.local.RepositoryLocalImpl
import com.alexyach.kotlin.numbers.repository.remote.RemoteImpl
import com.alexyach.kotlin.numbers.ui.numberslist.GetMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NumberDetailsViewModel : ViewModel() {

    /** Live Data */
    private val valueInfoLiveData: MutableLiveData<NumberDetailsAppState> =
        MutableLiveData<NumberDetailsAppState>()

    fun getValueInfoLiveData(): MutableLiveData<NumberDetailsAppState> {
        return valueInfoLiveData
    }


    fun getDetailsNumber(getMethod: GetMethod) {

        valueInfoLiveData.postValue(NumberDetailsAppState.Loading)

        when (getMethod) {
            GetMethod.GET_RANDOM -> {
                getRandomNumber()
            }
            GetMethod.GET_BY_NUMBER -> {
                getByNumber(GetMethod.GET_BY_NUMBER.getMethod)
            }
            GetMethod.GET_FROM_ROOM -> {
                getNumberFromRoom(GetMethod.GET_FROM_ROOM.getMethod)
            }
        }
    }

    // Огорнули в корутіну та використали для ретрофіт метод execute
    private fun getRandomNumber() {

        viewModelScope.launch(Dispatchers.IO) {

            RemoteImpl().getRandomNumber(object : ICallbackResponse {

                override fun callbackSuccess(callbackNumber: NumberModel) {
                    valueInfoLiveData.postValue(
                        NumberDetailsAppState
                            .SuccessGetDetails(callbackNumber)
                    )

                    /** Зберігаємо в ROOM */
                    saveNumberToRoom(callbackNumber)
                }

                override fun callbackError(e: Exception) {
                    valueInfoLiveData.postValue(
                        NumberDetailsAppState
                            .ErrorGetDetails(e.message ?: "Помилка")
                    )
                }
            })
        }
    }

    private fun getByNumber(value: String) {
        // Не огортали в корутіну, використвуючи для ретрофіт метод enqueue
        RemoteImpl().getDetailsByNumber(value, object : ICallbackResponse {

            override fun callbackSuccess(callbackNumber: NumberModel) {
                valueInfoLiveData.postValue(
                    NumberDetailsAppState
                        .SuccessGetDetails(callbackNumber)
                )

                /** Зберігаємо в ROOM */
                saveNumberToRoom(callbackNumber)
            }

            override fun callbackError(e: Exception) {
                valueInfoLiveData.postValue(
                    NumberDetailsAppState
                        .ErrorGetDetails(e.message ?: "Помилка")
                )
            }
        })
    }

    /** Зберігаємо в ROOM */
    private fun saveNumberToRoom(numberModel: NumberModel) {
        viewModelScope.launch(Dispatchers.IO) {
            RepositoryLocalImpl().saveNumberToRoom(numberModel)
        }

    }

    private fun getNumberFromRoom(number: String) {

        viewModelScope.launch(Dispatchers.IO) {

            RepositoryLocalImpl().getDetailsByNumber(number, object : ICallbackResponse {
                override fun callbackSuccess(callbackNumber: NumberModel) {
                    valueInfoLiveData.postValue(
                        NumberDetailsAppState
                            .SuccessGetDetails(callbackNumber)
                    )
                }

                override fun callbackError(e: Exception) {
                    valueInfoLiveData.postValue(
                        NumberDetailsAppState
                            .ErrorGetDetails(e.message ?: "Помилка")
                    )
                }
            })
        }
    }
}