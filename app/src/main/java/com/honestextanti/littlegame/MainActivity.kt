package com.honestextanti.littlegame

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tapMeButton: Button
    private lateinit var gameScoreTextView: TextView
    private lateinit var timeLeftTextView: TextView

    private var score = 0
    private var gameStarted = false
    private lateinit var countDownTimer: CountDownTimer
    private val initialCountDown: Long = 60000
    private val countDownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.tepMeButton)
        gameScoreTextView = findViewById(R.id.scoreTextView)
        timeLeftTextView = findViewById(R.id.timeTextView)
        tapMeButton.setOnClickListener {
            incrementScore()
        }
        resetGame()
    }

    private fun resetGame(){
        score = 0
        gameScoreTextView.text = getString(R.string.score, score)
        val initialTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(R.string.time, initialTimeLeft)

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval){
            override fun onTick(p0: Long) {
                val timeLeft = p0 / 1000
                timeLeftTextView.text = getString(R.string.time, timeLeft)
            }

            override fun onFinish() {
                endGame()
            }

        }
        gameStarted = false
    }

    private fun incrementScore() {
        if (!gameStarted){
            startGame()
        }
        score += 1
        val newScore = getString(R.string.score, score)
        gameScoreTextView.text = newScore
    }

    private fun startGame(){
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame(){
        Toast.makeText(this, getString(R.string.game_over_message, score), Toast.LENGTH_LONG).show()
        resetGame()
    }
}