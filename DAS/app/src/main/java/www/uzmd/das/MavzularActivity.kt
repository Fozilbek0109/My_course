package www.uzmd.das

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import www.uzmd.das.adapter.MyAdapterDarslar
import www.uzmd.das.databinding.ActivityMavzularBinding
import www.uzmd.das.databinding.DialogAddUstozBinding
import www.uzmd.das.databinding.DialogMavzuQoshishBinding
import www.uzmd.das.servise.MyDatabase

class MavzularActivity : AppCompatActivity() {
    val db: MyDatabase by lazy {
        MyDatabase(this)
    }
    private lateinit var list: ArrayList<KitobModell>

    //ustoz_list nega kerakligini tushinmadim
    //    private lateinit var user_list: MutableList<UstozModell>
    private lateinit var myAdapterDarslar: MyAdapterDarslar
    private lateinit var binding: ActivityMavzularBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMavzularBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        user_list = ArrayList(db.listUstoz())
//        var last_id = user_list.last().id.toString()
        val catigory_id = intent.getIntExtra("id",0)
        val fanName = intent.getStringExtra("fanName")
        list = ArrayList(db.listKitob(catigory_id))
        myAdapterDarslar = MyAdapterDarslar(list)
        val dialogMavzuQoshishBinding = DialogMavzuQoshishBinding.inflate(
            LayoutInflater.from(this@MavzularActivity),
            null,
            false
        )
        val dialog = AlertDialog.Builder(this@MavzularActivity).create()
        dialog.setView(dialogMavzuQoshishBinding.root)
        binding.apply {
            mavzuQushish.setOnClickListener {
                dialog.show()
            }
            myAdapterDarslar.notifyItemChanged(list.size)
            recView.adapter = myAdapterDarslar
        }
        dialogMavzuQoshishBinding.apply {
            darsSaqalshBtn.setOnClickListener {
                val fanMavzusiEd = darsMaqsadiEd.text.toString().trim()
                val fanMazmuni = darsMatniEd.text.toString().trim()
                val byIdMavzularList = db.getByIdMavzularList()
                val kitobModell =
                    KitobModell(byIdMavzularList.get(byIdMavzularList.size-1),
                        fanName,
                        fanMavzusiEd,
                        fanMazmuni,
                        catigory_id
                        )
                list.add(kitobModell)
                db.addKitob(kitobModell)
                myAdapterDarslar.notifyItemInserted(list.size)
                dialog.dismiss()
            }
        }
    }
}