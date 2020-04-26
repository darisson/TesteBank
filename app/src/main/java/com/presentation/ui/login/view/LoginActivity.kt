package com.presentation.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import com.br.myapplication.R
import com.data.source.remote.api.ApiResult
import com.presentation.ui.home.view.HomeActivity
import com.presentation.ui.login.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private val loginButton: Button by lazy { findViewById<Button>(R.id.btnLogin) }
    private val groupProgress: Group by lazy { findViewById<Group>(R.id.groupProgress) }
    private val editUserLogin: EditText by lazy { findViewById<EditText>(R.id.edtUserLogin) }
    private val editPasswordLogin: EditText by lazy { findViewById<EditText>(R.id.edtPasswordLogin) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        observerLoginResponse()
        checkHasUser()
    }

    private fun checkHasUser() {
        viewModel.getUser()?.let {
            setupHomeActivity()
        }
    }

    private fun observerLoginResponse() {
        viewModel.liveDataResponse.observe(this, Observer {
            groupProgress.visibility = View.GONE
            if (it is ApiResult.Success) {
                viewModel.saveUserPreferences(it.response.userAccount)
                setupHomeActivity()
            } else if (it is ApiResult.Error){
                Toast.makeText(this, it.throwable.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun setupViews() {
        loginButton.setOnClickListener {
            if (!viewModel.isValidateUserField(editUserLogin.text.toString())) {
                editUserLogin.error = ("Usuario Invalido")
            } else if (!viewModel.isValidPasswordField(editPasswordLogin.text.toString())) {
                editPasswordLogin.error = ("Senha Invalida")
            } else {
                groupProgress.visibility = View.VISIBLE
                viewModel.doLogin(editUserLogin.text.toString(), editPasswordLogin.text.toString())
            }
        }
    }
}