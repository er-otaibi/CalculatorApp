package com.example.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textResult: TextView
    lateinit var btnZero: Button
    lateinit var btnOne: Button
    lateinit var btnTwo: Button
    lateinit var btnThree: Button
    lateinit var btnFour: Button
    lateinit var btnFive: Button
    lateinit var btnSix: Button
    lateinit var btnSeven: Button
    lateinit var btnEight: Button
    lateinit var btnNine: Button
    lateinit var multiply: Button
    lateinit var result: Button
    lateinit var division: Button
    lateinit var add: Button
    lateinit var subtraction: Button
    lateinit var clear: Button
    lateinit var dot: Button
    lateinit var sign :Button
    lateinit var del: Button
    var numbersBtn = arrayListOf<Button>()
    var operatorsBtn = arrayListOf<Button>()
    var num1 =""
    var num2 = ""
    var operator = " "
    var count = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.textView)
        btnZero = findViewById(R.id.zero)
        btnOne = findViewById(R.id.num1)
        btnTwo = findViewById(R.id.num2)
        btnThree = findViewById(R.id.num3)
        btnFour = findViewById(R.id.num4)
        btnFive = findViewById(R.id.num5)
        btnSix = findViewById(R.id.num6)
        btnSeven = findViewById(R.id.num7)
        btnEight = findViewById(R.id.num8)
        btnNine = findViewById(R.id.num9)
        clear = findViewById(R.id.clear)
        dot = findViewById(R.id.point)
        del = findViewById(R.id.del)
        sign = findViewById(R.id.positive_negitive)

        multiply = findViewById(R.id.multiply)
        add = findViewById(R.id.add)
        subtraction = findViewById(R.id.minus)
        division = findViewById(R.id.divide)
        result = findViewById(R.id.equal)

        numbersBtn = arrayListOf(
            btnZero,
            btnOne,
            btnTwo,
            btnThree,
            btnFour,
            btnFive,
            btnSix,
            btnSeven,
            btnEight,
            btnNine
        )
        for (i in numbersBtn) {
            i.setOnClickListener { numberClicked(i) }
        }
        operatorsBtn = arrayListOf(multiply, add, subtraction, division)
        for (i in operatorsBtn) {
            i.setOnClickListener {
                operatorClicked(i)
            }
        }
        result.setOnClickListener { calculator() }
        clear.setOnClickListener { clearAll() }
        dot.setOnClickListener { decimal() }
        del.setOnClickListener { deleteLastNum() }
        sign.setOnClickListener { onClickPlusMinus() }


    }

    private fun calculator() {
         when (operator){
            "+" -> textResult.text = (num1.toFloat() + num2.toFloat()).toString()
            "-" -> textResult.text =(num1.toFloat() - num2.toFloat()).toString()
            "*" -> textResult.text =(num1.toFloat() * num2.toFloat()).toString()
             else -> {
                 if (num1.toFloat() != 0f && num2.toFloat() != 0f) {
                     textResult.text = (num1.toFloat() / num2.toFloat()).toString()
                 } else {
                     clearAll()
                     textResult.text = "ERROR"
                 }
             }
        }

        count = textResult.text.toString().toFloat()
        num1 = count.toString()
        num2 = ""
    }

    private fun operatorClicked(i: Button) {
        var op = i.text
        operator = op as String
        val text = num1 + op
        textResult.text = text
    }

    fun numberClicked(i: Button){
        var num = i.text.toString()
        if (operator == " "){
            //declare first num
            num1+=num
            textResult.text = num1
            }else {
                num2+=num
            val text = num1 + operator + num2
            textResult.text = text
        }
    }
    fun clearAll(){
        operator = " "
        num1 = ""
        num2 = ""
        textResult.text = "0"
    }

    fun decimal(){
        if(operator==" "&&!num1.contains(".")){numberClicked(dot)}
        if(operator!=" "&&!num2.contains(".")){numberClicked(dot)}
    }

    fun deleteLastNum() {
        if (operator == " ") {
            if (num1.isNotEmpty()) {
                num1 = num1.substring(0, num1.length - 1)
            }
            if (num1.isEmpty()) {
                textResult.text = "0"
            }else{textResult.text = num1}
        } else {
            if (num2.isNotEmpty()){
                num2 = num2.substring(0, num2.length - 1)
                textResult.text = num1 + operator + num2

            }else{
                operator=" "
                textResult.text = num1
            }
        }

    }
    fun onClickPlusMinus(){
        if(operator==" "){
            num1 = if(num1.startsWith("-")){
                num1.substring(1, num1.length)
            } else{
                "-$num1"
            }
            textResult.text = num1
        }else{
            num2 = if(num2.startsWith("-")){
                num2.substring(1, num2.length)
            } else{
                "-$num2"
            }
            val text = num1 + operator + num2
            textResult.text= text
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Number1", num1)
        outState.putString("Number2", num2)
        outState.putString("Operator", operator)

    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        num1 = savedInstanceState.getString("Number1" ).toString()
        num2 = savedInstanceState.getString("Number2" ).toString()
        operator = savedInstanceState.getString("Operator" ).toString()
        textResult.text = num1 + operator + num2
    }


}
