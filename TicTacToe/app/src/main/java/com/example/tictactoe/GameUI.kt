package com.example.tictactoe

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityGameUiBinding
import com.shashank.sony.fancytoastlib.FancyToast


class GameUI : AppCompatActivity() {

    lateinit var binding: ActivityGameUiBinding

    lateinit var playerone: String
    lateinit var playertwo: String
    private var scoreX:Int = 0
    private var scoreO:Int = 0
    var flag = 0
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameUiBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent
        playerone = intent.getStringExtra("Player_one")!!
        playertwo = intent.getStringExtra("Player_two")!!

        updateScoresOnUI()
    }

    private fun updateScoresOnUI() {
        binding.playerone.text = "$playerone : $scoreX"
        binding.playertwo.text = "$playertwo : $scoreO"
    }

    fun check(view: View) {
        val btn = view as Button
        if (btn.text == "") {
            count++
            if (flag == 0) {
                btn.text = "X"
                btn.setTextColor(resources.getColor(R.color.Red));
                flag = 1
            } else {
                btn.text = "O"
                btn.setTextColor(resources.getColor(R.color.Yellow));
                flag = 0
            }

            val btn1 = binding.btn1.text.toString()
            val btn2 = binding.btn2.text.toString()
            val btn3 = binding.btn3.text.toString()
            val btn4 = binding.btn4.text.toString()
            val btn5 = binding.btn5.text.toString()
            val btn6 = binding.btn6.text.toString()
            val btn7 = binding.btn7.text.toString()
            val btn8 = binding.btn8.text.toString()
            val btn9 = binding.btn9.text.toString()

            fun reset() {
                binding.btn1.text = ""
                binding.btn2.text = ""
                binding.btn3.text = ""
                binding.btn4.text = ""
                binding.btn5.text = ""
                binding.btn6.text = ""
                binding.btn7.text = ""
                binding.btn8.text = ""
                binding.btn9.text = ""
            }

            fun toast(btn: String) {
                if (btn == "X"){
                    scoreX++
                    FancyToast.makeText(this,"Winner is $playerone, Points : $scoreX",
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,true).show()

                    count = 0
                } else {
                    scoreO++
                    FancyToast.makeText(this,"Winner is $playertwo, Points : $scoreX",
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,true).show()

                    count = 0
                }
                updateScoresOnUI()
                reset()
            }

            // Winning conditions
            when {
                btn1 == btn2 && btn2 == btn3 && btn3 != "" -> toast(btn1)
                btn4 == btn5 && btn5 == btn6 && btn6 != "" -> toast(btn4)
                btn7 == btn8 && btn8 == btn9 && btn9 != "" -> toast(btn7)
                btn1 == btn4 && btn4 == btn7 && btn7 != "" -> toast(btn1)
                btn2 == btn5 && btn5 == btn8 && btn8 != "" -> toast(btn2)
                btn3 == btn6 && btn6 == btn9 && btn9 != "" -> toast(btn3)
                btn1 == btn5 && btn5 == btn9 && btn9 != "" -> toast(btn1)
                btn3 == btn5 && btn5 == btn7 && btn3 != "" -> toast(btn1)
                count == 9 -> {
                    FancyToast.makeText(this,"Match Draw",
                        FancyToast.LENGTH_LONG,
                        FancyToast.DEFAULT,true).show()
                    reset()
                    count = 0
                }
            }

            binding.newgame.setOnClickListener{
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Are you sure?")
                dialog.setMessage("All data will be reset")
                dialog.setPositiveButton("Yes"){ dialog, which ->
                    reset()
                    count = 0
                    scoreO = 0
                    scoreX = 0
                    startActivity(Intent(this@GameUI,UserInfo::class.java))
                }
                dialog.setNegativeButton("No"){dialog,which ->
                    FancyToast.makeText(this,"Canceled",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show()
                }
                val alertDialog:AlertDialog = dialog.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }

    }

}
