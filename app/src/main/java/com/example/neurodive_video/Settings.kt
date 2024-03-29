package com.example.neurodive_video

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children

var automaticBrowsing = false
var genres = mutableListOf<String>()
var MusicLists = mutableMapOf<String, MutableList<String>>()
var VideoPull = mutableListOf<String>()

public fun initMusicS()
{
    val Rock = mutableListOf<String>("ZkW-K5RQdzo","c898WyjHDx4","PJwo6bMKBaw")
    val Jazz = mutableListOf<String>("VqhCQZaH4Vs","fHbC8Nhd46s","l7N2wssse14")
    val Rap = mutableListOf<String>("5QCaaAyz-yA","SRcnnId15BA","-T5I__Jdl3w")
    val Hiphop = mutableListOf<String>("-T5I__Jdl3w","vk6014HuxcE","fPO76Jlnz6c")
    val Folk = mutableListOf<String>("sKqUdAbjlto","23DniLl2ZGc","tM-EmkAZSvc")
    val Cla = mutableListOf<String>("sCtixpIWBto","y3AiGw8mkq0","MlAuHoRXLes")
    MusicLists.apply {
        this["Рок"] = Rock;
        this["Джаз"] = Jazz;
        this["Хип-хоп"] = Hiphop;
        this["Рэп"] = Rap;
        this["Фольклор"] = Folk;
        this["Классика"] = Cla}
}

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        if (automaticBrowsing)
            findViewById<CheckBox>(R.id.checkBox_browsing).isChecked = true

        initMusicS()

        val rows = findViewById<LinearLayout>(R.id.linearLayout).children
        for (row in rows) {
            val table_row = findViewById<TableRow>(row.id)
            if(table_row.getChildAt(1) != null){
                val textView = table_row.getChildAt(0) as TextView
                val checkBox = table_row.getChildAt(1) as CheckBox
                val text = textView.text.toString()
                if (genres.contains(text))
                    findViewById<CheckBox>(checkBox.id).isChecked = true
            }
        }
    }
    fun toVideo(view: View){
        initMusicPullS()
        val intent = Intent(this, Video::class.java)
        startActivity(intent)
    }
    fun autoBrowse(view: View){
        automaticBrowsing = !automaticBrowsing
    }
    fun chosenGenres(view: View){
        val id = view.id
        val name = resources.getResourceName(id)
        val rows = findViewById<LinearLayout>(R.id.linearLayout).children
        var text = ""
        for (row in rows) {
            val table_row = findViewById<TableRow>(row.id)
            val textView = table_row.getChildAt(0) as TextView
            text = textView.text.toString()
            val name2 = resources.getResourceName(textView.id)
            val num1 = name[name.length - 1].toInt()
            val num2 = name2[name2.length - 1].toInt()
            val substr = "TextView"
            if (substr in name2 && num2 == num1) {
                var checkBox = findViewById<CheckBox>(id)
                if (checkBox.isChecked){
                    if (!genres.contains(text))
                        genres.add(text)
                }
                else{
                    if (genres.contains(text))
                        genres.remove(text)
                }
                break
            }
        }
    }
    fun toMenu(view: View){
        initMusicPullS()
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
    }
    fun toBack(view: View){
        initMusicPullS()
        finish()
    }



    fun initMusicPullS()
    {
        VideoPull = mutableListOf<String>()

        for (gen in genres) {
            VideoPull+= MusicLists[gen]!!
        }
    }
}