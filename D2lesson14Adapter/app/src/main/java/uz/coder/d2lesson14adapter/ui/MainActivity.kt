package uz.coder.d2lesson14adapter.ui

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import uz.coder.d2lesson14adapter.R
import uz.coder.d2lesson14adapter.adapter.Adapter
import uz.coder.d2lesson14adapter.database.MyDatabase
import uz.coder.d2lesson14adapter.databinding.ActivityMainBinding
import uz.coder.d2lesson14adapter.databinding.DialogAddBinding
import uz.coder.d2lesson14adapter.model.BookModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: Adapter
    val db by lazy {
        MyDatabase(this)
    }
    lateinit var list: ArrayList<BookModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList(db.listAll())
        binding.fbBtn.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val dialogBinding = DialogAddBinding.inflate(LayoutInflater.from(this), null, false)
            dialog.setView(dialogBinding.root).apply {
                dialogBinding.save.setOnClickListener {
                    val name = dialogBinding.name.text.toString().trim()
                    val author = dialogBinding.author.text.toString().trim()
                    val year = dialogBinding.year.text.toString().trim()
                    val bookModel = BookModel(name = name, author = author, year = year)
                    db.add(bookModel)
                    list.add(bookModel)
                    adapter.notifyItemInserted(list.size)

                    dialog.dismiss()
                }
                dialog.show()
            }

        }
        adapter = Adapter(list, { bookModel, position ->
            db.delete(bookModel)
            list.removeAt(position)
            adapter.notifyItemRemoved(position)
            adapter.notifyItemRangeChanged(position, list.size)

        }, { bookModel, position ->
            val dialog = AlertDialog.Builder(this).create()
            val dialogBinding = DialogAddBinding.inflate(LayoutInflater.from(this), null, false)
            dialog.setView(dialogBinding.root).apply {
               dialogBinding.apply {
                   name.setText(bookModel.name)
                   author.setText(bookModel.author)
                   year.setText(bookModel.year)
               }

                Log.d(TAG, "onCreate: ${bookModel.id}")
                dialogBinding.save.setOnClickListener {
                    bookModel.name = dialogBinding.name.text.toString().trim()
                    bookModel.author = dialogBinding.author.text.toString().trim()
                    bookModel.year = dialogBinding.year.text.toString().trim()
                    db.edit(bookModel)
                    adapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
                dialog.show()
            }

        })
        binding.res.adapter = adapter


    }
}