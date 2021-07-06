package com.nirmal.lovecalculator

import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.plattysoft.leonids.ParticleSystem
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    var ss = ""

    private fun calculate(str: Char): Int {
        var i: Int
        var f = 0
        var n = 0
        i = 0
        while (i < ss.length) {
            if (ss[i] == str) {
                f += 1
                break
            }
            i++
        }
        ss = ss + str
        if (f == 0) {
            i = str.toInt()
            n = i % 2 + i % 10 + i % 8
            if (i % 16 < 10) {
                n += i % 16
            }
        }
        return n
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels

        txtCalculate.setOnClickListener {
            var s1: String = edtBoy.getText().toString()
            var s2: String = edtGirl.getText().toString()

            if (!s1.isEmpty() && !s2.isEmpty()) {

                txtBoy.text = s1
                txtGirl.text = s2
                val st: String
                var i: Int
                var v: Int
                var s = 0
                val r: Int
                s1 = s1.toUpperCase(Locale.getDefault())
                s2 = s2.toUpperCase(Locale.getDefault())
                i = 0
                while (i < s1.length) {
                    if (s1[i] == ' ') { // v = calcvalue(s1.charAt(i));
// s += v;
                        break
                    }
                    v = calculate(s1[i])
                    s += v
                    i++
                }
                i = 0
                while (i < s2.length) {
                    if (s2[i] == ' ') { // v = calcvalue(s2.charAt(i));
// s += v;
                        break
                    }
                    v = calculate(s2[i])
                    s += v
                    i++
                }
                ss = ""
                r = (Math.random() * 10).toInt()
                if (r != 0) s += r else s -= 5
                if (s <= 90) s += 9 else if (s <= 95) s += 4
                if (s > 100) {
                    i = s % 100
                    s -= i * 2
                }
                if (s in 1..4) {
                    s += 10
                }
                if (s <= 0) {
                    s = r * 2
                }
                st = s.toString()
                val value = "$st%"
                txtPersentage.setText(value)
                /*Toast.makeText(getApplicationContext(), st,
							Toast.LENGTH_LONG).show();*/

                first.visibility = View.GONE
                second.visibility = View.VISIBLE

                val phoneViewAnim =
                    TranslateAnimation(0f, -(width + 200f), 0f, 0f)
                phoneViewAnim.duration = 500
                phoneViewAnim.fillAfter = false

                val otpViewAnim = TranslateAnimation(width + 200f, 0f, 0f, 0f)
                otpViewAnim.duration = 500
                otpViewAnim.fillAfter = true

                first.startAnimation(phoneViewAnim)
                second.startAnimation(otpViewAnim)
                Handler().postDelayed({
                    ParticleSystem(this, 100, R.drawable.heart_small, 5000)
                        .setSpeedRange(0.2f, 0.5f)
                        .oneShot(txtPersentage, 100)

                    ParticleSystem(this, 100, R.drawable.confeti3, 5000)
                        .setSpeedRange(0.2f, 0.5f)
                        .oneShot(txtPersentage, 100)
                },500)

            } else {
                Toast.makeText(
                    applicationContext, "No Input",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        txtCalCulateAgain.setOnClickListener {
            edtBoy.setText("")
            edtGirl.setText("")
            first.visibility = View.VISIBLE
            second.visibility = View.GONE


            val phoneViewAnim =
                TranslateAnimation(-(width + 200f), 0f, 0f, 0f)
            phoneViewAnim.duration = 500
            phoneViewAnim.fillAfter = true

            val otpViewAnim = TranslateAnimation(0f, width + 200f, 0f, 0f)
            otpViewAnim.duration = 500
            otpViewAnim.fillAfter = false

            first.startAnimation(phoneViewAnim)
            second.startAnimation(otpViewAnim)
        }
    }
}
