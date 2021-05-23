package com.kirvigen.templateapplication.ui.fragments.listGames

import androidx.annotation.StringRes
import com.kirvigen.templateapplication.R

enum class ErrorState(@StringRes val id:Int){
    EMPTYLIST(R.string.empty_list),INTERNETERROR(R.string.internet_error)
}