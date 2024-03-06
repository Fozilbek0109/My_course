package uz.coder.coroutineflow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import uz.coder.coroutineflow.databinding.ActivityUser2Binding


class UserActivity2 : AppCompatActivity() {
    private val binding by lazy {
        ActivityUser2Binding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonAddUser.setOnClickListener {
            binding.editTextUsername.text.toString()
                .trim()
                .takeIf { it.isNotBlank() }
                ?.let {
                    viewModel.addUser(it)
                }
        }
        viewModel.user.observe(this) {
            binding.textViewUsers.text = it.joinToString()
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, UserActivity2::class.java)
    }
}