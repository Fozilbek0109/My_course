package uz.coder.coroutineflow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import uz.coder.coroutineflow.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUserBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupLitsener()
        observeViewModel()
    }

    private fun setupLitsener() {
        binding.buttonAddUser.setOnClickListener {
            binding.editTextUsername.text.toString()
                .trim()
                .takeIf { it.isNotBlank() }
                ?.let {
                    viewModel.addUser(it)
                }
        }
        binding.buttonNextScreen.setOnClickListener {
            startActivity(UserActivity2.newIntent(this))
        }

    }

    private fun observeViewModel() {
        viewModel.user.observe(this) {
            binding.textViewUsers.text = it.joinToString()
        }
    }


    companion object {
        fun newIntent(context: Context) = Intent(context, UserActivity::class.java)
    }
}