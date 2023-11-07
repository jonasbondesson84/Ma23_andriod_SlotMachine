package com.example.slotmachine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var imageSlot1 : ImageView
    lateinit var imageSlot2 : ImageView
    lateinit var imageSlot3 : ImageView
    lateinit var winnerText: TextView
    lateinit var text: TextView
    var slotNumber1: Int = 0
    var slotNumber2: Int = 0
    var slotNUmber3: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageSlot1 = findViewById(R.id.imageView)
        imageSlot2 = findViewById(R.id.imageView2)
        imageSlot3 = findViewById(R.id.imageView3)
        winnerText = findViewById(R.id.textView)
        text = findViewById(R.id.textView2)

        val buttonGo: ImageButton = findViewById(R.id.imageButton)

        buttonGo.setOnClickListener() {
            slotNumber1 = randomNum()
            imageSlot1.setImageResource(getImage(slotNumber1))
            slotNumber2 = randomNum()
            imageSlot2.setImageResource(getImage(slotNumber2))
            slotNUmber3 = randomNum()
            imageSlot3.setImageResource(getImage(slotNUmber3))

            text.text = slotNumber1.toString()+slotNumber2.toString()+slotNUmber3.toString()

            if(checkWinner()) {
                winnerText.text = "We have a winner!"

            } else {
                winnerText.text = "You've lost"
            }

        }
    }

    fun randomNum(): Int {
        return Random.nextInt(3)
    }

    fun getImage(randNumber: Int): Int {
        when(randNumber) {
            0 -> {
                return R.drawable.cherries_35288_640
            }
            1 -> {
                return R.drawable.lemons_310283_640
            }
            2 -> return R.drawable.watermelon_25247_640
            else -> {return R.drawable.lemons_310283_640}
        }

    }

    fun checkWinner(): Boolean {
        return (slotNumber1 == slotNumber2 && slotNumber2 == slotNUmber3)
    }
}