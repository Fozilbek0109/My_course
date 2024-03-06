package uz.coder.d2lesson14adapter.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.coder.d2lesson14adapter.model.BookModel
import uz.coder.d2lesson14adapter.service.BookService

class MyDatabase(context: Context):SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION),BookService {
    companion object{
        const val DB_NAME = "temp"
        const val DB_VERSION = 1
        const val TABLE_NAME = "books"
        const val BOOK_ID = "id"
        const val BOOK_NAME = "name"
        const val BOOK_AUTHOR = "book_author"
        const val BOOK_YEAR = "book_year"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table $TABLE_NAME($BOOK_ID integer not null primary key autoincrement," +
                "$BOOK_NAME text not null," +
                "$BOOK_AUTHOR text not null," +
                "$BOOK_YEAR text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }

    override fun add(bookModel: BookModel) {
        val writableDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(BOOK_NAME,bookModel.name)
        contentValues.put(BOOK_AUTHOR,bookModel.author)
        contentValues.put(BOOK_YEAR,bookModel.year)
        writableDatabase.insert(TABLE_NAME,null,contentValues)
    }

    override fun edit(bookModel: BookModel) {
        val db = this.writableDatabase
        val  con = ContentValues()
        con.put(BOOK_NAME,bookModel.name)
        con.put(BOOK_AUTHOR,bookModel.author)
        con.put(BOOK_YEAR,bookModel.year)
        db.update(TABLE_NAME,con,"$BOOK_ID = ?", arrayOf(bookModel.id.toString()))
    }

    override fun delete(bookModel: BookModel) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"$BOOK_ID = ?", arrayOf(bookModel.id.toString()))
    }

    override fun listAll():List<BookModel> {
        val db = this.readableDatabase
        val list = mutableListOf<BookModel>()
        val query = "select * from $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val author = cursor.getString(2)
                val year = cursor.getString(3)
                val bookModel = BookModel(id,name,author,year)
                list.add(bookModel)
            }while (cursor.moveToNext())
        }
        return  list
    }

    override fun getBookCount(): Int {

        TODO("Not yet implemented")
    }

    override fun getBookId(): Int {
        TODO("Not yet implemented")
    }


}