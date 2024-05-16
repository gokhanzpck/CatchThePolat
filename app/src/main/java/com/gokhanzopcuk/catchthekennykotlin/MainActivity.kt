package com.gokhanzopcuk.catchthekennykotlin

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gokhanzopcuk.catchthekennykotlin.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
     var score=0
    var imageArray =ArrayList<ImageView>()
    var runnable = Runnable {}
    var handler=Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        hideImages()
Toast.makeText(this,"Benim yaşayan bir düşmanım yok!",Toast.LENGTH_LONG).show()
        object :CountDownTimer(40000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text="Time: ${millisUntilFinished/1000}"
            }
            override fun onFinish() {
                binding.timeText.text="Tıme: 0"
               handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
             val alert=AlertDialog.Builder(this@MainActivity)
             alert.setTitle("Game Over ")
             alert.setMessage("Restart The Game?")
             alert.setPositiveButton("Yes",DialogInterface.OnClickListener{ dialogInterface, i ->

                 val intentFromMain=intent
                 finish()
                 startActivity(intentFromMain)
             })
                alert.setNegativeButton("No",DialogInterface.OnClickListener{DialogInterface,İ->
                    Toast.makeText(this@MainActivity,"Ben bitti demeden bitmez her şey yeni başlıyor ",Toast.LENGTH_LONG).show()
                })
                alert.show()
            }
        }.start()
    }
    fun hideImages(){
        runnable=object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random=Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,250)
            }
        }
        handler.post(runnable) }
        //view.gone = siliyor direk leyaout dan cıkarıyor,cıkan karakterin yerine başka bir şey gecebiliyor//görünmez yapıyor
        //view.ınvısıble=gone gibi siliyor ama komple layouttan cıkarmıyor//görünmez yapıyor
        //view.vısıble=görünür yapıyor

    fun increaseScore(view: View){
        score+=1
binding.scoreText.text= "Score:${score} "
    }
}