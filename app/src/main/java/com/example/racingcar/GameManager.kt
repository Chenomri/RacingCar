package com.example.racingcar

import com.example.racingcar.utilities.Constants.Companion.COLS
import com.example.racingcar.utilities.Constants.Companion.ROWS
import kotlin.random.Random

class GameManager(private val lifeCount : Int =3) {
    var wrongAnswers: Int = 0
        private set
    var  stoneMatrix: Array<Array<Int>> = Array(ROWS) { Array(COLS) { 0 } }
        private set
     var  carLoaction : Int? = null
        private set

    private var skipLine : Boolean = false

    init {
        carLoaction= COLS/2
    }

    fun isGameLost() : Boolean  {
        val checked =stoneMatrix[stoneMatrix.size-1][carLoaction!!] == 1
        if(checked)
            wrongAnswers++
        return checked
    }

    fun runStoneIteration(){

        for (i in 0 until stoneMatrix.size - 1)
            stoneMatrix[stoneMatrix.size - 1 - i] = stoneMatrix[stoneMatrix.size - i - 2]
        stoneMatrix[0] = arrayOf(0, 0, 0 , 0 , 0)
        if(!skipLine) {
            stoneMatrix[0][Random.nextInt(5)]=1
        }
        skipLine=!skipLine

    }

    fun playerMove(side : Boolean ){
        if(side  && carLoaction != COLS -1) {//right
            this.carLoaction = carLoaction!! + 1
        }
        else if(!side && carLoaction != 0) {//left
            this.carLoaction = carLoaction!! - 1
        }
    }

    fun getCarLocation():Array<Int>
    {
        val carLoactionMatrix :Array<Int> = arrayOf(0,0,0,0,0)
        carLoactionMatrix[carLoaction!!]=1
        return carLoactionMatrix

    }

    val isGameEnded: Boolean
        get() = wrongAnswers == lifeCount

}