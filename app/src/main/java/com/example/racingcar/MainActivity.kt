package com.example.racingcar

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.racingcar.utilities.Constants
import com.example.racingcar.utilities.Constants.Companion.COLS
import com.example.racingcar.utilities.Constants.Companion.DISQUALIFICATIONS
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


class MainActivity : AppCompatActivity() {

    private lateinit var main_BTN_left: FloatingActionButton
    private lateinit var main_BTN_right: FloatingActionButton
    private lateinit var main_HEARTS_Linear: Array<ShapeableImageView>
    private lateinit var main_ROW_CAR_Linear: Array<ShapeableImageView>
    private lateinit var main_ROW_stone_0: Array<ShapeableImageView>
    private lateinit var main_ROW_stone_1: Array<ShapeableImageView>
    private lateinit var main_ROW_stone_2: Array<ShapeableImageView>
    private lateinit var main_ROW_stone_3: Array<ShapeableImageView>
    private lateinit var main_ROW_stone_4: Array<ShapeableImageView>
    private lateinit var main_ROW_stone_5: Array<ShapeableImageView>
    private lateinit var main_ROW_stone_6: Array<ShapeableImageView>
    private lateinit var main_ROW_stone_7: Array<ShapeableImageView>
    private lateinit var game_Matrix: Array<Array<ShapeableImageView>>
    private lateinit var gameManger: GameManager
    private var startTime: Long = 0
    private var timerOn = false
    private lateinit var timerJob: Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        initViews()
        gameManger = GameManager(DISQUALIFICATIONS)
        startTimer()
    }

    private fun initViews() {
        main_BTN_left.setOnClickListener { view: View? -> Clicked(false) }
        main_BTN_right.setOnClickListener { view: View? -> Clicked(true) }
    }



    private fun upDateStoneUI() {
        val stoneLocationMatrix = gameManger.stoneMatrix
        for (i in 0 until stoneLocationMatrix.size) {
            for (j in 0 until COLS) {
                if (stoneLocationMatrix[i][j] == 1)
                    game_Matrix[i].get(j).visibility = View.VISIBLE
                else
                    game_Matrix[i].get(j).visibility = View.INVISIBLE
            }

        }
    }

    private fun findViews() {

        main_BTN_left = findViewById(R.id.main_BTN_left)
        main_BTN_right = findViewById(R.id.main_BTN_right)
        main_HEARTS_Linear = arrayOf(
            findViewById(R.id.main_IMG_heart0),
            findViewById(R.id.main_IMG_heart1),
            findViewById(R.id.main_IMG_heart2)

        )
        main_ROW_CAR_Linear = arrayOf(
            findViewById(R.id.main_IMG_car0),
            findViewById(R.id.main_IMG_car1),
            findViewById(R.id.main_IMG_car2),
            findViewById(R.id.main_IMG_car3),
            findViewById(R.id.main_IMG_car4)
        )
        main_ROW_stone_0 = arrayOf(
            findViewById(R.id.main_IMG_stone_r0_c0),
            findViewById(R.id.main_IMG_stone_r0_c1),
            findViewById(R.id.main_IMG_stone_r0_c2),
            findViewById(R.id.main_IMG_stone_r0_c3),
            findViewById(R.id.main_IMG_stone_r0_c4)
        )
        main_ROW_stone_1 = arrayOf(
            findViewById(R.id.main_IMG_stone_r1_c0),
            findViewById(R.id.main_IMG_stone_r1_c1),
            findViewById(R.id.main_IMG_stone_r1_c2),
            findViewById(R.id.main_IMG_stone_r1_c3),
            findViewById(R.id.main_IMG_stone_r1_c4)
        )
        main_ROW_stone_2 = arrayOf(
            findViewById(R.id.main_IMG_stone_r2_c0),
            findViewById(R.id.main_IMG_stone_r2_c1),
            findViewById(R.id.main_IMG_stone_r2_c2),
            findViewById(R.id.main_IMG_stone_r2_c3),
            findViewById(R.id.main_IMG_stone_r2_c4)
        )
        main_ROW_stone_3 = arrayOf(
            findViewById(R.id.main_IMG_stone_r3_c0),
            findViewById(R.id.main_IMG_stone_r3_c1),
            findViewById(R.id.main_IMG_stone_r3_c2),
            findViewById(R.id.main_IMG_stone_r3_c3),
            findViewById(R.id.main_IMG_stone_r3_c4)
        )
        main_ROW_stone_4 = arrayOf(
            findViewById(R.id.main_IMG_stone_r4_c0),
            findViewById(R.id.main_IMG_stone_r4_c1),
            findViewById(R.id.main_IMG_stone_r4_c2),
            findViewById(R.id.main_IMG_stone_r4_c3),
            findViewById(R.id.main_IMG_stone_r4_c4)
        )
        main_ROW_stone_5 = arrayOf(
            findViewById(R.id.main_IMG_stone_r5_c0),
            findViewById(R.id.main_IMG_stone_r5_c1),
            findViewById(R.id.main_IMG_stone_r5_c2),
            findViewById(R.id.main_IMG_stone_r5_c3),
            findViewById(R.id.main_IMG_stone_r5_c4)
        )
        main_ROW_stone_6 = arrayOf(
            findViewById(R.id.main_IMG_stone_r6_c0),
            findViewById(R.id.main_IMG_stone_r6_c1),
            findViewById(R.id.main_IMG_stone_r6_c2),
            findViewById(R.id.main_IMG_stone_r6_c3),
            findViewById(R.id.main_IMG_stone_r6_c4)
        )
        main_ROW_stone_7 = arrayOf(
            findViewById(R.id.main_IMG_stone_r7_c0),
            findViewById(R.id.main_IMG_stone_r7_c1),
            findViewById(R.id.main_IMG_stone_r7_c2),
            findViewById(R.id.main_IMG_stone_r7_c3),
            findViewById(R.id.main_IMG_stone_r7_c4)
        )
        game_Matrix = arrayOf(
            main_ROW_stone_0,
            main_ROW_stone_1,
            main_ROW_stone_2,
            main_ROW_stone_3,
            main_ROW_stone_4,
            main_ROW_stone_5,
            main_ROW_stone_6,
            main_ROW_stone_7,

            )


    }

    private fun Clicked(b: Boolean) {
        gameManger.playerMove(b)
        updateUI()
    }

    private fun updateUI() {

        updateCarUi()
        upDateStoneUI()
        if (gameManger.isGameLost()) {
            main_HEARTS_Linear[DISQUALIFICATIONS - gameManger.wrongAnswers].visibility = View.INVISIBLE
            vibrate()
            toast("")
        }
        if (gameManger.isGameEnded) {
            toast("game over")
            stopTimer()
        }
    }

    private fun updateCarUi() {
        val carLocationMatrix = gameManger.getCarLocation()
        for (i in 0 until carLocationMatrix.size) {
            if (carLocationMatrix[i] == 1)
                main_ROW_CAR_Linear[i].visibility = View.VISIBLE
            else
                main_ROW_CAR_Linear[i].visibility = View.INVISIBLE
        }
    }

    private fun stopTimer() {
        timerOn = false
        timerJob.cancel()
    }

    private fun startTimer() {
        if (!timerOn) {
            startTime = System.currentTimeMillis()
            timerOn = true
            timerJob = lifecycleScope.launch {
                while (timerOn) {
                    gameManger.runStoneIteration()
                    updateUI()
                    delay(Constants.DELAY)
                }
            }
        }
    }

    private fun toast(text: String) {
        Toast
            .makeText(
                this,
                text,
                Toast.LENGTH_SHORT
            ).show()
    }

    private fun vibrate() {
        val vibrator: Vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                this.getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            this.getSystemService(VIBRATOR_SERVICE) as Vibrator
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val vibrationEffect = VibrationEffect.createOneShot(
                100,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
            vibrator.vibrate(vibrationEffect)
        } else {
            vibrator.vibrate(100)
        }
    }

}