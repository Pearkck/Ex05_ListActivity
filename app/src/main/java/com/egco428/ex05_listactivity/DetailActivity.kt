package com.egco428.ex05_listactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val courseTitle = intent.getStringExtra("CourseTitle")
        val courseDesc = intent.getStringExtra("CourseDesc")
        val courseNo = intent.getIntExtra("CourseNo", 0)
        val courseCredits = intent.getDoubleExtra("CourseCredits", 0.00)
        

        titleText.text = courseTitle
        descriptionText.text = courseDesc
        creditText.text = courseCredits.toString()
        Notext.text = courseNo.toString()
        imageCourse.setImageResource(intent.getIntExtra("image",1))

        button.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}


