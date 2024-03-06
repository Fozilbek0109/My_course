package uz.coder.d2pirimisionkotlin

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import uz.coder.d2pirimisionkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btn.setOnClickListener {
                if (ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this@MainActivity, "ruxsat bor", Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(this@MainActivity, "ruxsat yo'q", Toast.LENGTH_SHORT).show()
                    Pirimision.userPirimision(this@MainActivity)
                }
            }
        }

    }
}