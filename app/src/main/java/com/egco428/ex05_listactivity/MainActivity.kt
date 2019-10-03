package com.egco428.ex05_listactivity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.course_item.view.*
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var imgcourse = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = DataProvider.getData()
        //val iterator = data.iterator()
       // val builder = StringBuilder()

        /* while (iterator.hasNext()){
            val course = iterator.next()
            builder.append(course.title).append("\n")
        }
        courseListView.text =builder.toString()
    }*/


        val courseArrayAdapter = CourseArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        list.setAdapter(courseArrayAdapter)
        list.setOnItemClickListener { adapterView, view, position, id ->
            val course = data.get(position)
            //Log.d("Course Catelog","Course: ${course.title}")

            imgcourse = courseArrayAdapter.getImg(position%3)

            displayDetail(course)
        }


    }
    private fun displayDetail(course: Course){
        Log.d("Course Catelog","Course: ${course.title}")

        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("CourseTitle",course.title)
        intent.putExtra("CourseDesc",course.description)
        intent.putExtra("CourseNo",course.courseNumber)
        intent.putExtra("CourseCredits",course.credits)
        intent.putExtra("image",imgcourse)

        startActivity(intent)

    }
    private class CourseArrayAdapter(var context:Context, var resouce :Int ,var objects:ArrayList<Course>):BaseAdapter(){
        val imgcourse = listOf(R.drawable.image10101,R.drawable.image10102,R.drawable.image10103)

        fun getImg(position: Int):Int{
            return imgcourse[position]
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val course_item : View
            if(convertView == null){
                val layoutInflator = LayoutInflater.from(parent!!.context)
                course_item = layoutInflator.inflate(R.layout.course_item, parent, false)
                val viewHolder = ViewHolder(course_item.titleText,course_item.imageCourse)
                course_item.tag = viewHolder
            }else{
                course_item = convertView
            }

            val viewHolder = course_item.tag as ViewHolder
            /*val imgPos = position%3+1
            val res = context.resources.getIdentifier("image10101$imgPos","drawable",context.packageName)
            viewHolder.imageCourse.setImageResource(res)*/
            viewHolder.titleText.text = objects.get(position).toString()
            val mod = position % 3
            if (mod == 0) {
                viewHolder.imageCourse.setImageResource(R.drawable.image10101)
            } else if(mod == 1){
                viewHolder.imageCourse.setImageResource(R.drawable.image10102)
            } else{
                viewHolder.imageCourse.setImageResource(R.drawable.image10103)
            }

            return course_item

        }
        private class ViewHolder(val titleText: TextView, val imageCourse: ImageView)

        override fun getItem(position: Int): Any {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return objects.size
        }

    }
}
