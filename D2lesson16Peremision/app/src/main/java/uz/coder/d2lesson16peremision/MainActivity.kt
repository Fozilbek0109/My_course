package uz.coder.d2lesson16peremision

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import uz.coder.d2lesson16peremision.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCam.setOnClickListener{
            if (ContextCompat.checkSelfPermission(this@MainActivity,android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this@MainActivity, "rusat berilgan", Toast.LENGTH_SHORT).show()
                val intent = Intent(Camera::class.java)
                intent.data =
                startActivity(intent)
            }else{
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.CAMERA),1)
            }
        }
    }


}
