package com.wel.wat.breathalyser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var sexValue: Double = 0.7
    var weightValue: Double = 0.0
    var alcoholValue: Double = 0.0
    var percentageValue: Double = 0.0
    var millilitersValue: Double = 0.0
    var alcohol: Double = 0.0
    var sober: Double = 0.0
    var unitPerHour: Double = 0.0
    var percentage: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alcochlButton: Button = findViewById(R.id.add_alcohol)

        alcochlButton.setOnClickListener {
            val weightEdit: EditText = findViewById(R.id.weight)
            val mlEdit: EditText = findViewById(R.id.milliliters)
            val percentageEditText: EditText = findViewById(R.id.procentage)
            when {
                weightEdit.text.toString().isEmpty() -> {
                    weightEdit.error = "You need to enter your weight!"
                }
                mlEdit.text.toString().isEmpty() -> {
                    mlEdit.error = "You need to enter amount of alcochol"
                }
                percentageEditText.text.toString().isEmpty() -> {
                    percentageEditText.error = "You need to enter percentages of alcohol"
                }
                else -> {
                    weightValue = weightEdit.text.toString().toDouble()
                    percentageValue = percentageEditText.text.toString().toDouble()
                    millilitersValue = mlEdit.text.toString().toDouble()
                    alcoholValue = (millilitersValue*(percentageValue/100))*0.8
                    addAlcohol()
                    sober()
                }
            }

        }


    }

    @SuppressLint("SetTextI18n")
    private fun addAlcohol() {
        val resoultText: TextView = findViewById(R.id.result)
        this.alcohol = this.alcohol+alcoholValue
        percentage = this.alcohol/(sexValue*weightValue)
        resoultText.text = "$percentage"
    }

    @SuppressLint("SetTextI18n")
    private fun sober(){
        val soberText: TextView = findViewById(R.id.sober)
        if(sexValue==0.6) {
            unitPerHour = 8 / (sexValue * weightValue)
        }
            else {
            unitPerHour = 10 / (sexValue * weightValue)
        }
        sober = percentage/unitPerHour
        soberText.text = "You are going to be sober in $sober hours"
//        val timer = object: CountDownTimer(percentage.toLong(), unitPerHour.toLong()) {
//            override fun onTick(millisUntilFinished: Long) {
//                soberText.text = "You are going to be sober in $sober hours"
//            }
//
//            override fun onFinish() {
//                soberText.text = "You are sober!!"
//
//            }
//        }
//        timer.start()
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.Male ->
                    if (checked) {
                        sexValue = 0.7
                    }
                R.id.Female ->
                    if (checked) {
                        sexValue = 0.6
                    }
            }
        }

}
}