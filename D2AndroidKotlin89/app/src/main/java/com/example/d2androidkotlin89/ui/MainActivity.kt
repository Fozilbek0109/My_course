package com.example.d2androidkotlin89.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.d2androidkotlin89.adapter.MyAdapterCategory
import com.example.d2androidkotlin89.databinding.ActivityMainBinding
import com.example.d2androidkotlin89.databinding.DialogAddBinding
import com.example.d2androidkotlin89.db.MyDatabase
import com.example.d2androidkotlin89.model.CategoryModel
import com.example.d2androidkotlin89.model.TaomModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list:ArrayList<CategoryModel>
    val db:MyDatabase by lazy {
        MyDatabase(this)
    }
    private lateinit var myAdapterCategory: MyAdapterCategory
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        //
//        val taom = TaomModel(name = "Osma-Shorva", price = "20000", categoryId = 1)
//        val taom1 = TaomModel(name = "Shorva Barak", price = "25000", categoryId = 1)
//        val taom2 = TaomModel(name = "Steak", price = "100000", categoryId = 2)
//        val taom3 = TaomModel(name = "Qovirulgan Balik", price = "100000", categoryId = 3)
//
//        db.addTaom(taom)
//        db.addTaom(taom1)
//        db.addTaom(taom2)
//        db.addTaom(taom3)



        list = ArrayList(db.allListCategory())
        myAdapterCategory = MyAdapterCategory(list) { id ->
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }

        binding.apply {
            fab.setOnClickListener{
                val dialog = AlertDialog.Builder(this@MainActivity).create()
                val dialogBinding = DialogAddBinding.inflate(LayoutInflater.from(this@MainActivity),null,false)
                dialog.setView(dialogBinding.root)

                dialogBinding.save.setOnClickListener {
                    val name = dialogBinding.name.text.toString().trim()
                    val cat = CategoryModel(name = name)
                    list.add(cat)
                    db.addCategory(cat)
                    myAdapterCategory.notifyItemInserted(list.size)
                    dialog.dismiss()
                }

                dialog.show()

            }


            rec.adapter = myAdapterCategory
        }
    }
}