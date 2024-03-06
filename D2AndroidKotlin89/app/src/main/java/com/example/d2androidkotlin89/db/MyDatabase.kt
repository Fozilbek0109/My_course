package com.example.d2androidkotlin89.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.d2androidkotlin89.model.CategoryModel
import com.example.d2androidkotlin89.model.TaomModel

class MyDatabase(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
), MyDatabaseService {
    companion object {
        const val DATABASE_NAME = "main.db"
        const val DATABASE_VERSION = 1
        const val TABLE_CATEGORY = "menu"
        const val CATEGORY_ID = "id"
        const val CATEGORY_NAME = "name"
        const val TABLE_TAOM = "taomlar"
        const val TAOM_ID = "id"
        const val TAOM_NAME = "name"
        const val TAOM_PRICE = "price"

        const val TAOM_CATEGORY_ID = "category_id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query1 = "CREATE TABLE $TABLE_CATEGORY(" +
                "$CATEGORY_ID integer not null primary key autoincrement," +
                "$CATEGORY_NAME text not null)"
        db?.execSQL(query1)

        val query2 = "CREATE TABLE $TABLE_TAOM(" +
                "$TAOM_ID integer not null primary key autoincrement," +
                "$TAOM_NAME text not null," +
                "$TAOM_PRICE text not null," +
                "$TAOM_CATEGORY_ID integer REFERENCES $TABLE_CATEGORY($CATEGORY_ID))"
        db?.execSQL(query2)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun addCategory(categoryModel: CategoryModel) {
        val db = this.writableDatabase
        val con = ContentValues()
        con.put(CATEGORY_NAME, categoryModel.name)
        db.insert(TABLE_CATEGORY, null, con)
    }

    override fun deleteCategory(categoryModel: CategoryModel) {
        TODO("Not yet implemented")
    }

    override fun editCategory(categoryModel: CategoryModel) {
        TODO("Not yet implemented")
    }

    override fun allListCategory(): List<CategoryModel> {
        var list = mutableListOf<CategoryModel>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_CATEGORY"
        val c = db.rawQuery(query, null)
        if (c.moveToFirst()) {
            do {
                val id = c.getInt(0)
                val name = c.getString(1)
                val c1 = CategoryModel(id,name)
                list.add(c1)
            } while (c.moveToNext())
        }
        return list
    }

    override fun addTaom(taomModel: TaomModel) {
        val db = this.writableDatabase
        val con = ContentValues()
        con.put(TAOM_NAME, taomModel.name)
        con.put(TAOM_PRICE, taomModel.price)
        con.put(TAOM_CATEGORY_ID, taomModel.categoryId)
        db.insert(TABLE_TAOM, null, con)
    }

    override fun deleteTaom(taomModel: TaomModel) {
        TODO("Not yet implemented")
    }

    override fun editTaom(taomModel: TaomModel) {
        TODO("Not yet implemented")
    }

    override fun allListTaom(): List<TaomModel> {
        TODO("Not yet implemented")
    }

    override fun getTaomById(id: Int): List<TaomModel> {
        val db = this.readableDatabase
        val list = mutableListOf<TaomModel>()
//        val query = "Select * from $TABLE_TAOM where $CATEGORY_ID = $id"
//        val c = db.rawQuery(query, null)

        val c = db.query(
            TABLE_TAOM,
            arrayOf(TAOM_ID, TAOM_NAME, TAOM_PRICE, TAOM_CATEGORY_ID),
            "$TAOM_CATEGORY_ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        if (c.moveToFirst()){
            do {
                val id1 = c.getInt(0)
                val name = c.getString(1)
                val price = c.getString(2)
                val catId = c.getInt(3)
                val m = TaomModel(id1,name,price,catId)
                list.add(m)

            }while (c.moveToNext())
        }
        c.close()
        return list
    }
}