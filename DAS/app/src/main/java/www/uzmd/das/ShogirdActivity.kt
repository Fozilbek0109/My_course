package www.uzmd.das

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import www.uzmd.das.adapter.MyAdapterUstoz
import www.uzmd.das.adapter.MyShogirdAdapter
import www.uzmd.das.databinding.ActivityShogirdBinding
import www.uzmd.das.servise.MyDatabase

class ShogirdActivity : AppCompatActivity() {
    val db: MyDatabase by lazy {
        MyDatabase(this)
    }
    lateinit var list: ArrayList<UstozModell>
    lateinit var binding: ActivityShogirdBinding
    lateinit var myShogirdAdapter: MyShogirdAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShogirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList(db.listUstoz())
        myShogirdAdapter = MyShogirdAdapter(list)
        binding.recShogird.adapter = myShogirdAdapter
    }
}