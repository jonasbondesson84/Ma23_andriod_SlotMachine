package com.example.slotmachine

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.postDelayed
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    lateinit var slotAnimation1: AnimationDrawable
    lateinit var slotAnimation2: AnimationDrawable
    lateinit var slotAnimation3: AnimationDrawable
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




        winnerText = findViewById(R.id.textView)
        text = findViewById(R.id.textView2)
        winnerText.visibility = View.INVISIBLE

        val buttonGo: ImageButton = findViewById(R.id.imageButton)

        buttonGo.setOnClickListener() {

            buttonGo.visibility = View.INVISIBLE
            winnerText.visibility = View.INVISIBLE

            imageSlot1 = findViewById<ImageView?>(R.id.imageView).apply {
                setBackgroundResource(R.drawable.slotanimation)
                slotAnimation1 = background as AnimationDrawable
            }
            imageSlot2 = findViewById<ImageView>(R.id.imageView2).apply {
                setBackgroundResource(R.drawable.slotanimation)
                slotAnimation2 = background as AnimationDrawable
            }
            imageSlot3 = findViewById<ImageView>(R.id.imageView3).apply {
                setBackgroundResource(R.drawable.slotanimation)
                slotAnimation3 = background as AnimationDrawable
            }
            imageSlot1.setImageResource(R.drawable.hd_transparent_picture)
            imageSlot2.setImageResource(R.drawable.hd_transparent_picture)
            imageSlot3.setImageResource(R.drawable.hd_transparent_picture)
            if (!slotAnimation1.isRunning) { // Kontrollera om animationen redan körs
                slotAnimation1.setVisible(true, false)
                slotAnimation2.setVisible(true, false)
                slotAnimation3.setVisible(true, false)

                slotAnimation1.start()
                slotAnimation2.start()
                slotAnimation3.start()

                Handler().postDelayed(Random.nextLong(1000, 1100)) {
                    slotNumber1 = getFrameIndex(slotAnimation1, slotAnimation1.current)
                    slotAnimation1.stop()
                    slotAnimation1.setVisible(false, false) // Återställ animationen

                    Handler().postDelayed(Random.nextLong(1000, 1100)) {
                        slotNumber2 = getFrameIndex(slotAnimation2, slotAnimation2.current)
                        slotAnimation2.stop()
                        slotAnimation2.setVisible(false, false)

                        Handler().postDelayed(Random.nextLong(1000, 1100)) {
                            slotNUmber3 = getFrameIndex(slotAnimation3, slotAnimation3.current)
                            slotAnimation3.stop()
                            slotAnimation3.setVisible(false, false)

                            text.text =
                                slotNumber1.toString() + slotNumber2.toString() + slotNUmber3.toString()
                            if (checkWinner()) {
                                winnerText.text = "We have a winner!"
                            } else {
                                winnerText.text = "You've lost"
                            }
                            buttonGo.visibility = View.VISIBLE
                            winnerText.visibility = View.VISIBLE
                        }

                    }

                }
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

    fun getFrameIndex(slotAnimation: AnimationDrawable, currentFrame: Drawable): Int {

        var checkFrame : Drawable
        for (i in 0..slotAnimation.numberOfFrames) {
            checkFrame = slotAnimation.getFrame(i);
            if (checkFrame == currentFrame) {
                return i;
            }
        }
        return 99
    }
}