package www.uzmd.das.servise

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import www.uzmd.das.KitobModell
import www.uzmd.das.UstozModell

class MyDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), MyFunService {
    companion object {
        const val DATABASE_NAME = "data.db"
        const val DATABASE_VERSION = 1
        const val TABEL_USTOZ = "ustoz"
        const val TABEL_SHOGIRD = "shogird"
        const val USTOZ_ID = "id"
        const val USTOZ_FAN_NAME = "name"

        const val CATEGORY_ID = "cat_id"


        //**//
        const val SHGRD_ID = "id"
        const val SHGRD_FAN_NAME = "fanName"
        const val SHGRD_MAVZUSI = "mavzu"
        const val SHGRD_FAN_MAZMUNI = "mazmun"
        const val SHGRD_CAT_ID = "cat_id"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "CREATE TABLE $TABEL_USTOZ($USTOZ_ID INTEGER PRIMARY KEY AUTOINCREMENT," + "$USTOZ_FAN_NAME TEXT NOT NULL)"
        p0?.execSQL(query)
        val query2 =
            "CREATE TABLE $TABEL_SHOGIRD($SHGRD_ID INTEGER PRIMARY KEY AUTOINCREMENT," + "$SHGRD_FAN_NAME TEXT NOT NULL," + "$SHGRD_MAVZUSI TEXT NOT NULL," + "$SHGRD_FAN_MAZMUNI TEXT NOT NULL," + "$SHGRD_CAT_ID TEXT NOT NULL)"
        p0?.execSQL(query2)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    override fun addUstoz(u: UstozModell) {
        val database = this.writableDatabase
        val con = ContentValues()
        con.put(USTOZ_FAN_NAME, u.fanNomi)
        database.insert(TABEL_USTOZ, null, con)
    }


    override fun editUstoz(u: UstozModell) {
        val database = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(USTOZ_FAN_NAME, u.fanNomi)
        database.update(TABEL_USTOZ, contentValues, "$USTOZ_ID=?", arrayOf(u.id.toString()))
    }

    override fun deletUstoz(u: UstozModell) {
        val database = this.writableDatabase
        database.delete(TABEL_USTOZ, "$USTOZ_ID=?", arrayOf(u.id.toString()))
    }

    override fun listUstoz(): List<UstozModell> {
        val data = this.readableDatabase
        val list = mutableListOf<UstozModell>()
        val query = "SELECT * FROM $TABEL_USTOZ"
        val cursor = data.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(0)
                var fanNomiDatadan = cursor.getString(1)
                val ustozModell = UstozModell(fanNomi = fanNomiDatadan, id = id)
                list.add(ustozModell)
            } while (cursor.moveToNext())
        }
        return list
    }

    // bu list getByIdList metodi uchun
    private lateinit var listUstozById: MutableList<Int>
    override fun getByIdUstozList(): List<Int> {
        val db = this.readableDatabase

        //bu so'rov ustozdagi id larni olib keladi
        val query = "select $USTOZ_ID from $TABEL_USTOZ"
        // kelgan id larni saqlash uchun list

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            listUstozById = mutableListOf()
            do {
                var id = cursor.getInt(0)
                //bizdagi fanlarga 1 id mavjud shuning uchun buyerga 1 index keladi vayana shuni qaytarib yuboradi
                // buni oldini olish uchun id larni bittaga oshirib qo'yamiz va listga joylaymiz
                id++
                listUstozById.add(id)
            } while (cursor.moveToNext())
        } else {
            // dastur ishga tushganda cursor hecnimani o'qimaydi shu sabab bizga birinchi id kerak bo'ladi
            // if operatoriga tushmagaligi sabab bu yerdaham listdan object olishimiz kerak
            listUstozById = mutableListOf()
            listUstozById.add(1)
        }
        return listUstozById
    }

    private lateinit var listKitobById: MutableList<Int>
    override fun getByIdMavzularList(): List<Int> {
        val db = this.readableDatabase

        //bu so'rov ustozdagi id larni olib keladi
        val query = "select $SHGRD_ID from $TABEL_SHOGIRD"
        // kelgan id larni saqlash uchun list

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            listKitobById = mutableListOf()
            do {
                var id = cursor.getInt(0)
                //bizdagi fanlarga 1 id mavjud shuning uchun buyerga 1 index keladi vayana shuni qaytarib yuboradi
                // buni oldini olish uchun id larni bittaga oshirib qo'yamiz va listga joylaymiz
                id++
                listKitobById.add(id)
            } while (cursor.moveToNext())
        } else {
            // dastur ishga tushganda cursor hecnimani o'qimaydi shu sabab bizga birinchi id kerak bo'ladi
            // if operatoriga tushmagaligi sabab bu yerdaham listdan object olishimiz kerak
            listKitobById = mutableListOf()
            listKitobById.add(1)
        }
        return listKitobById

    }

    override fun addKitob(u: KitobModell) {
        val database = this.writableDatabase
        val con = ContentValues()
        con.put(SHGRD_FAN_NAME, u.fanName)
        con.put(SHGRD_MAVZUSI, u.fanMavzu)
        con.put(SHGRD_FAN_MAZMUNI, u.fanMazmun)
        con.put(CATEGORY_ID, u.fanCatgoryId)
        database.insert(TABEL_SHOGIRD, null, con)
    }

    // kitob list bizga faqat ustozni idsi birxi bolganlarini listga qo'shib bersin va u bitta id qabul qilsin
    override fun listKitob(catigoryId: Int): List<KitobModell> {
        val db = this.readableDatabase
        val mutableList = mutableListOf<KitobModell>()
        val query = "SELECT * FROM $TABEL_SHOGIRD"
        val cursor = db.query(
            TABEL_SHOGIRD,
            arrayOf(SHGRD_ID, SHGRD_FAN_NAME, SHGRD_MAVZUSI, SHGRD_FAN_MAZMUNI, SHGRD_CAT_ID),
            "$SHGRD_CAT_ID = ?",
            arrayOf(catigoryId.toString()),
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(0)
                var fanName = cursor.getString(1)
                var fanMavzusi = cursor.getString(2)
                var fanMazmuni = cursor.getString(3)
                var fanCategory = cursor.getInt(4)
                val kitobModell = KitobModell(
                    id,
                    fanName,
                    fanMavzusi,
                    fanMazmuni,
                    fanCategory
                )
                mutableList.add(kitobModell)
            } while (cursor.moveToNext())
        }
        return mutableList
    }


}