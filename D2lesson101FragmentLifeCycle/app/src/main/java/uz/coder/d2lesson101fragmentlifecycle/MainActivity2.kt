package uz.coder.d2lesson101fragmentlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.coder.d2lesson101fragmentlifecycle.databinding.ActivityMain2Binding

private lateinit var binding: ActivityMain2Binding
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fb.setOnClickListener {
            finish()
        }


    }
}