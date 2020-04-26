package com.example.testebankdarisson.login

import com.data.source.local.SharedPreferencesManager
import com.data.source.remote.api.ApiResult
import com.data.source.remote.dto.ResponseLogin
import com.domain.model.UserAccount
import com.domain.usercase.loginUserCase.LoginUserCase
import com.example.testebankdarisson.CoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.presentation.ui.login.viewmodel.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
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
        val cpf = "270.018.580-30"
        junit.framework.Assert.assertEquals(loginViewModel.isValidateUserField(cpf), true)
    }

    @Test
    fun isValidPasswordField() {
        val password = "Dev@dev1602"
        junit.framework.Assert.assertEquals(loginViewModel.isValidPasswordField(password), true)
    }

    @Test
    fun `checkValidEmail$app_debug`() {
        val email = "dev@gmail.com"
        junit.framework.Assert.assertEquals(loginViewModel.checkValidEmail(email), true)
    }

    @Test
    fun `checkCpf$app_debug`() {
        val cpf = "270.018.580-30"
        junit.framework.Assert.assertEquals(loginViewModel.checkCpf(cpf), true)
    }

    @Test
    fun validateLogin()  = coroutineRule.runBlockingTest{
        val params = LoginUserCase.Params("06126533313", "Dari@123")
        val userAccount = UserAccount("1", "teste", "92500", "1234", 500.00)
        val response = ResponseLogin(userAccount, com.domain.error.Error("", ""))

        whenever(loginUsercaseMock.run(params)).thenReturn(ApiResult.Success(response))

        //whenever(loginUsercaseMock.invoke()).thenReturn(ApiResult.Success(response))

        loginViewModel.doLogin("06126533313", "Dari@123")


        verify(loginUsercaseMock, times(1)).invoke(params, {apiResult: ApiResult<ResponseLogin> ->  })
//        verify(loginViewModel, times(1)).saveUserPreferences(userAccount)


    }

}