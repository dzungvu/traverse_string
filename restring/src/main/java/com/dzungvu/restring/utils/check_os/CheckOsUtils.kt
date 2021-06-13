package com.dzungvu.restring.utils.check_os

import android.os.Build

fun Any.atLeastQ(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
}