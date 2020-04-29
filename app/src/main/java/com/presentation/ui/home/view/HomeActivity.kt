package com.presentation.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.br.myapplication.R
import com.data.source.remote.api.ApiResult
import com.presentation.ui.home.adapter.StatementsAdapter
import com.presentation.ui.home.utils.formatAgency
import com.presentation.ui.home.utils.formatToMonetary
import com.presentation.ui.home.viewmoldel.HomeViewModel
import com.presentation.ui.login.view.AuthActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        chargeLoginParameters()
        statementsResponse()
        logout()
        viewModel.getStatements()
    }

    private fun statementsResponse() {
        viewModel.liveDataResponse.observe(this, Observer {
            progressLoad.visibility = View.GONE
            if (it is ApiResult.Success) {
                recyclerStatements.adapter = StatementsAdapter(this, it.response)
            }
        })
    }

    private fun chargeLoginParameters() {
        viewModel.getUser()?.let {
            textUser.text = it.name
            txtAccount.text = "${it.bankAccount} / ${it.agency.formatAgency()}"
            txtBalance.text = it.balance.formatToMonetary()
        }
    }

    private fun logout() {
        logout_btn.setOnClickListener {
            viewModel.logout()
            goToLoginActivity()
        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}
