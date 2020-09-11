package co.leaders.board.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import co.leaders.board.R
import co.leaders.board.api.ApiService
import co.leaders.board.extensions.executeAsync
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_submit.*

class SubmitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        buttonSubmit.setOnClickListener {

            val fname = TextInputEditTextFirstName.editableText.toString()
            val lname = TextInputEditTextLastName.editableText.toString()
            val email = TextInputEditTextEmail.editableText.toString()
            val link = TextInputEditTextLink.editableText.toString()

            if (TextUtils.isEmpty(fname)) {
                TextInputEditTextFirstName.error = "Enter your first name"
            } else if (TextUtils.isEmpty(lname)) {
                TextInputEditTextLastName.error = "Enter your last name"
            } else if (TextUtils.isEmpty(email)) {
                TextInputEditTextEmail.error = "Enter your email"
            } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                TextInputEditTextEmail.error = "Enter your a valid email"
            } else if (TextUtils.isEmpty(link)) {
                TextInputEditTextEmail.error = "Enter your project link"
            } else if (Patterns.WEB_URL.matcher(link).matches()) {
                TextInputEditTextLink.error = "Enter your a valid link"
            } else {

                submitFormData(fname, lname, email, link)

            }


        }

    }

    private fun submitFormData( fname:String,  lname: String,  email:String,  link: String) {
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(R.layout.confirm_dialog)
            .show()

        if (dialog is AlertDialog) {

            val confirmButton = dialog.findViewById<MaterialButton>(R.id.confirm_button)
            val cancelButton = dialog.findViewById<MaterialButton>(R.id.btnCancel)

            cancelButton?.setOnClickListener {
                dialog.dismiss()
            }

            confirmButton?.setOnClickListener {

                executeAsync({

                    ApiService.getInstance("https://docs.google.com/forms/d/e/")
                        .submitFormData(
                            email,
                            fname,
                            lname,
                            link
                        )
                        .execute()

                }, {
                    if (it.isSuccessful) {

                        MaterialAlertDialogBuilder(this)
                            .setView(R.layout.sucess_dialog)
                            .show()

                    } else {

                        MaterialAlertDialogBuilder(this)
                            .setView(R.layout.fail_dialog)
                            .show()

                    }

                })

            }
        }
    }

    companion object {
        const val TAG: String = "SubmitActivity"
    }
}