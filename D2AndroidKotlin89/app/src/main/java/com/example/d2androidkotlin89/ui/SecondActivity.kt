package com.example.d2androidkotlin89.ui

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.d2androidkotlin89.R
import com.example.d2androidkotlin89.adapter.MyAdapterTaom
import com.example.d2androidkotlin89.databinding.ActivitySecondBinding
import com.example.d2androidkotlin89.db.MyDatabase

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    val db by lazy {
        MyDatabase(this)
    }

    private lateinit var myAdapterTaom: MyAdapterTaom
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra("id", 0)
        val taomList = db.getTaomById(id)
        myAdapterTaom = MyAdapterTaom(taomList)
        binding.apply {
            rec.adapter = myAdapterTaom
        }


    }
}