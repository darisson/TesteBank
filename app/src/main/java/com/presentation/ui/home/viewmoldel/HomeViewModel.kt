package com.presentation.ui.home.viewmoldel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.data.source.helper.AppHelper
import com.data.source.local.SharedPreferencesManager
import com.data.source.remote.api.ApiResult
import com.domain.model.Statement
import com.domain.model.UserAccount
import com.domain.usercase.HomeUserCase.HomeUserCase

class HomeViewModel(private val homeUserCase: HomeUserCase,
                    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    val liveDataResponse: MutableLiveData<ApiResult<List<Statement>>> = MutableLiveData()

    fun getStatements() {
        getUser()?.let { user ->
            homeUserCase(HomeUserCase.Params(user.userId)) {
                liveDataResponse.value = it
            }
        }
    }

    fun getUser() : UserAccount? {
        val userString = sharedPreferencesManager.recoverUser()
        return userString?.let {
            return AppHelper.convertStringToObj(it, UserAccount::class.java)
        }
    }

    fun logout() {
        sharedPreferencesManager.clearPreferences()
    }
}