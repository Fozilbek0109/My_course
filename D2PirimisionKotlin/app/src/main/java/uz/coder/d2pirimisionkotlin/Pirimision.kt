package uz.coder.d2pirimisionkotlin

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity

class Pirimision: AppCompatActivity() {

companion object{
    var i = 0
    fun userPirimision(context: Context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            // bir marta otkaz bo'ldi 2 - stack
            val dialog = AlertDialog.Builder(context).create()
            dialog.setTitle("Ruxsat")
            dialog.setMessage("Ilovada foydalanadigan texnolgia uchun ruxsat")
            dialog.setButton(
                AlertDialog.BUTTON_POSITIVE,
                "ok"
            ) { dialog, which ->
                Toast.makeText(context, "ruxsat keldi", Toast.LENGTH_SHORT).show()
                // ikkinchi so'rov
                ActivityCompat.requestPermissions(
                    context,
                    arrayOf(android.Manifest.permission.CAMERA),
                    2
                )
            }
            dialog.setButton(
                AlertDialog.BUTTON_NEGATIVE,
                "no",
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(context, "ruxsat yo'q", Toast.LENGTH_SHORT).show()
                    }
                })

        } else {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(android.Manifest.permission.CAMERA),
                1
            )
        }
    }
}

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else if (requestCode == 2) {
                if (grantResults[0] == 2) {
                    // ikki narta no bosilsa keyin ruxsat qilingan bosa bu yerga tushadi
                } else {
                    // ikki narta no bosilsa hali ruhsat olinmagan bolsa   bu yerga tushadi
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                    i = 2
                }
            } else {
                if (i == 2) {
                    // umuman ruxsat berilmasdan kelingan bolsa qolgan barchasida bu yerga tushadi
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
            }
        }
    }
