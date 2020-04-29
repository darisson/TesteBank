package com.presentation.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.br.myapplication.R
import com.data.source.remote.api.ApiResult
import com.presentation.ui.home.view.HomeActivity
import com.presentation.ui.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkingViews()
        LoginResponse()
        checkUserAndCallHomeActivity()
    }

    private fun checkUserAndCallHomeActivity() {
        viewModel.getUser()?.let {
            GoToHomeActivity()
        }
    }

    private fun LoginResponse() {
        viewModel.liveDataResponse.observe(this, Observer {
            group_progress.visibility = View.GONE
            if (it is ApiResult.Success) {
                viewModel.saveUserPreferences(it.response.userAccount)
                GoToHomeActivity()
            } else if (it is ApiResult.Error){
                Toast.makeText(this, it.throwable.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun GoToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun checkingViews() {
        btn_login.setOnClickListener {
            if (!viewModel.validateUser(loginUser.text.toString())) {
                loginUser.error = ("Usuario Invalido")
            } else if (!viewModel.validatePassword(passwordEdit.text.toString())) {
                passwordEdit.error = ("Senha Invalida")
            } else {
                group_progress.visibility = View.VISIBLE
                viewModel.doLogin(loginUser.text.toString(), passwordEdit.text.toString())
            }
        }
    }
}
