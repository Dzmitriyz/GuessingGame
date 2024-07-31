package com.example.guessinggame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class GameViewModel: ViewModel() {
    val words = listOf("Android", "Activity", "Fragment")
    val secretWord = words.random().uppercase()
    val secretWordDisplay = MutableLiveData<String>()
    var correctGuesses = ""
    val incorrectGuesses = MutableLiveData<String>("")
    val livesLeft = MutableLiveData<Int>(8)

    init {
        secretWordDisplay.value = deriverSecretWordDisplay()
    }

    fun deriverSecretWordDisplay(): String{
        var display = ""
        secretWord.forEach {
            display+=checkLetter(it.toString())
        }
        return display
    }
    fun checkLetter(str:String) = when (correctGuesses.contains(str)){
        true -> str
        false -> "_"
    }

    fun makeGuess(guess: String){
        if(guess.length ==1){
            if(secretWord.contains(guess)){
                correctGuesses +=guess
                secretWordDisplay.value = deriverSecretWordDisplay()
            }else{
                incorrectGuesses.value +="$guess"
                livesLeft.value = livesLeft.value?.minus(1)
            }
        }
    }

    fun isWon() = secretWord.equals(secretWordDisplay.value, true)

    fun isLost() = livesLeft.value ?:0 <=0

    fun wonLostMessage() : String{
        var message = ""
        if(isWon()) message = "You won!"
        else if (isLost()) message = "You lost!"
        message += " The words $secretWord"
        return message

    }
}