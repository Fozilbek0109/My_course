package www.uzmd.das

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import www.uzmd.das.adapter.MyAdapterUstoz
import www.uzmd.das.databinding.ActivityUstozBinding
import www.uzmd.das.databinding.DialogAddUstozBinding
import www.uzmd.das.databinding.DialogUstozEditeBinding
import www.uzmd.das.servise.MyDatabase

class UstozActivity : AppCompatActivity() {

    val db: MyDatabase by lazy {
        MyDatabase(this)
    }
    private lateinit var list: ArrayList<UstozModell>
    private lateinit var myAdapterUstoz: MyAdapterUstoz
    private lateinit var binding: ActivityUstozBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUstozBinding.inflate(layoutInflater)
        list = ArrayList(db.listUstoz())



        myAdapterUstoz = MyAdapterUstoz(list, { ustozModell, pozition ->
            db.deletUstoz(ustozModell)
            list.remove(ustozModell)
            myAdapterUstoz.notifyItemRemoved(pozition)
            myAdapterUstoz.notifyItemChanged(pozition, list.size)
        }, { ustozModell, pozition2 ->
            val ustozEditeBinding = DialogUstozEditeBinding.inflate(
                LayoutInflater.from(this@UstozActivity),
                null,
                false
            )
            val dialogEdt = AlertDialog.Builder(this@UstozActivity).create()
            dialogEdt.setView(ustozEditeBinding.root)
            dialogEdt.show()
            ustozEditeBinding.ustozSaqalashBtn.setOnClickListener {
                val fanNameEdt = ustozEditeBinding.fanNomiEdt.text.toString().trim()
                ustozModell.fanNomi = fanNameEdt
                myAdapterUstoz.notifyItemChanged(pozition2)
                dialogEdt.dismiss()
            }

        }, { model, id ->
            val intent = Intent(this@UstozActivity, MavzularActivity::class.java)
            // faqat id kifoya qiladi
//            intent.putExtra("fanName", ustozModell.fanNomi)
//            intent.putExtra("pozition", pozition3)
            // metodimizda kelgan idni mazular activityda tutib olami
            intent.putExtra("id", id)
            intent.putExtra("fanName", model.fanNomi.toString())
            startActivity(intent)
        })


        setContentView(binding.root)


        val bindingDialog =
            DialogAddUstozBinding.inflate(LayoutInflater.from(this@UstozActivity), null, false)
        val dialog = AlertDialog.Builder(this@UstozActivity).create()

        bindingDialog.apply {
            ustozSaqalashBtn.setOnClickListener {
                var fanNomi = fanNomiEdt.text.toString().trim()
                // dastur ishlayotgan vaqtda id 0 bo'lib qolmasligi uchun modelga database listdagi oxirgi idni berib yuboramiz
                val byIdList = db.getByIdUstozList()
                val ustozModell =
                    UstozModell(byIdList.get(byIdList.size - 1), fanNomi)
                db.addUstoz(ustozModell)
                list.add(ustozModell)
                myAdapterUstoz.notifyItemChanged(list.size)
                dialog.dismiss()
            }
        }

        binding.apply {
            dialogBtn.setOnClickListener {
                dialog.setView(bindingDialog.root)
                dialog.show()
            }
            Log.e(TAG, "onCreate: ")
            myAdapterUstoz.notifyItemChanged(list.size)
            recView.adapter = myAdapterUstoz
        }

    }
}