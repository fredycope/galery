package com.backbase.assignment.data.utils

import android.view.View

interface OnClickList {
    fun goToFragment(result: Any, view: View)
    fun clickFavorite(result: Any)
}