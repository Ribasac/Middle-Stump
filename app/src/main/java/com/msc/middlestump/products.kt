package com.msc.middlestump

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class products(
    @StringRes val stringResourceId : Int,
    @DrawableRes val imageResourceId : Int
    )