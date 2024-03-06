package uz.coder.cleanarxeticturewithdagger.prisentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.coder.cleanarxeticturewithdagger.R
import uz.coder.cleanarxeticturewithdagger.dagger.DaggerComponentService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val viewModel: ExampleViewModel
        get() = DaggerComponentService.create().getExapleViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.metod()
    }
}