package www.uzmd.das

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import www.uzmd.das.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            ustozLy.setOnClickListener {
                val intent = Intent(this@MainActivity, UstozActivity::class.java)
                startActivity(intent)
            }
            shogirdLt.setOnClickListener {
                val intent = Intent(this@MainActivity, ShogirdActivity::class.java)
                startActivity(intent)
            }
        }
    }
}