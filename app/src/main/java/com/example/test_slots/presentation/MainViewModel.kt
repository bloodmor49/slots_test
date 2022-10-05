package com.example.test_slots.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_slots.domain.entities.Coin
import com.example.test_slots.domain.entities.Coin.Companion.START_COIN_VALUE
import com.example.test_slots.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val addBetUseCase: AddBetUseCase,
    private val insertCoinsUseCase: InsertCoinsUseCase,
    private val initializeCoinsUseCase: InitializeCoinsUseCase,
    private val removeBetUseCase: RemoveBetUseCase,
    private val spinUseCase: SpinUseCase,
) : ViewModel() {

    var countOfFinishedSpins = 0

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _coins = MutableLiveData<Coin>()
    val coins: LiveData<Coin>
        get() = _coins

    private val _currentBet = MutableLiveData(0)
    val currentBet: LiveData<Int?>
        get() = _currentBet

    fun addBet() {
        val newBet = addBetUseCase.invoke(_currentBet.value!!, _coins.value!!.number)
        _currentBet.postValue(newBet)
    }

    fun removeBet() {
        val newBet = removeBetUseCase.invoke(_currentBet.value!!)
        _currentBet.postValue(newBet)
    }

    fun getCoins() {
        coroutineScope.launch {
            val coinsFromDb = getCoinsUseCase.invoke()
            _coins.postValue(coinsFromDb)
        }
    }

    fun initializeCoins() {
        coroutineScope.launch {
            initializeCoinsUseCase.invoke()
            val coinsFromDb = getCoinsUseCase.invoke()
            _coins.postValue(coinsFromDb)
        }
    }

    fun spin(listOfResult: List<List<Int>>) {
        coroutineScope.launch {
            val curBet = _currentBet.value ?: 0
            val curCoins = _coins.value!!.number
            insertCoinsUseCase.invoke(Coin(number = curCoins - curBet))
            if (curCoins <= 0)
                insertCoinsUseCase.invoke(Coin(number = START_COIN_VALUE))
            spinUseCase.invoke(curBet, listOfResult)
            val coinsFromDb = getCoinsUseCase.invoke()
            _coins.postValue(coinsFromDb)
        }
    }
}