package com.example.tictactoe
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast

class UserInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        var playerOne = findViewById<EditText>(R.id.firstPlayer)
        var playerTwo = findViewById<EditText>(R.id.secondPlayer)
        var startGame = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.startGame)

        startGame.setOnClickListener {

            var firstPlayer  = playerOne.text.toString()
            var secondPlayer = playerTwo.text.toString()

            if (firstPlayer.isEmpty()){
                FancyToast.makeText(this,"Please Enter First Player Name",FancyToast.LENGTH_SHORT,FancyToast.WARNING,true).show()
            }
            else if (secondPlayer.isEmpty()){
                FancyToast.makeText(this,"Please Enter Second Player Name",FancyToast.LENGTH_SHORT,FancyToast.WARNING,true).show()
            }
            else run {
                val intent = Intent(this@UserInfo, GameUI::class.java)

                intent.putExtra("Player_one", firstPlayer)
                intent.putExtra("Player_two", secondPlayer)

                startActivity(intent)
                finish()
            }

        }
    }

}