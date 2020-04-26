package com.data.source.remote.dto

import android.os.Parcelable
import com.domain.error.Error
import com.domain.model.UserAccount
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseLogin (
    val userAccount: UserAccount,
    val error: Error
) : Parcelable

