package com.example.testebankdarisson.login

import com.data.source.local.SharedPreferencesManager
import com.domain.usercase.loginUserCase.LoginUserCase
import com.example.testebankdarisson.CoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.presentation.ui.login.viewmodel.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    lateinit var loginViewModel: LoginViewModel

    lateinit var loginUsercaseMock : LoginUserCase
    lateinit var sharedPreferencesManager : SharedPreferencesManager

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    @Before
    fun setup() {
        loginUsercaseMock = mock<LoginUserCase>()
        sharedPreferencesManager = mock<SharedPreferencesManager>()
        loginViewModel = LoginViewModel(loginUsercaseMock, sharedPreferencesManager)
    }

    @Test
    fun isValidateUserField() {
        val cpf = "793.599.650-49"
        junit.framework.Assert.assertEquals(loginViewModel.validateUser(cpf), true)
    }

    @Test
    fun isValidPasswordField() {
        val password = "Dari@123"
        junit.framework.Assert.assertEquals(loginViewModel.validatePassword(password), true)
    }

    @Test
    fun `checkValidEmail$app_debug`() {
        val email = "darissonbraga@gmail.com"
        junit.framework.Assert.assertEquals(loginViewModel.checkValidEmail(email), true)
    }

    @Test
    fun `checkCpf$app_debug`() {
        val cpf = "793.599.650-49"
        junit.framework.Assert.assertEquals(loginViewModel.checkCpf(cpf), true)
    }
}