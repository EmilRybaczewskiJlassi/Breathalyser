package com.wel.wat.breathalyser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alcochlButton: Button = findViewById(R.id.add_alcohol)

        alcochlButton.setOnClickListener {
            val test: TextView = findViewById(R.id.test)
            val sex = test.text.toString().toDouble()

            val weightEdit: EditText = findViewById(R.id.weight)
            val weight = weightEdit.text.toString().toDouble()

            val mlEdit: EditText = findViewById(R.id.milliliters)
            val procentageEditText: EditText = findViewById(R.id.procentage)
            val pr = procentageEditText.text.toString().toDouble()
            val ml = mlEdit.text.toString().toDouble()
            val alcohol = (ml*(pr/100))*0.8

            addAlcohol(alcohol, sex, weight)
        }


    }

    @SuppressLint("SetTextI18n")
    private fun addAlcohol(alcohol: Double, sex: Double, weight: Double) {
        val resoultText: TextView = findViewById(R.id.result)
        var percentage = alcohol/(sex*weight)
        resoultText.text = "$percentage".plus("‰")
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val xText: TextView = findViewById(R.id.test)
            val checked = view.isChecked
            when (view.getId()) {
                R.id.Male ->
                    if (checked) {
                        xText.text = "0.7"
                    }
                R.id.Female ->
                    if (checked) {
                        xText.text = "0.6"
                    }
            }
        }

}
}