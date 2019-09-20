package com.example.marketim.Classes

import android.content.Context
import com.example.marketim.R

class Helper {
    companion object {
        const val PREFS_USERNAME = "shared_preferences_username"
        const val PREFS_FILENAME = "com.example.marketim.prefs"
        const val PREFS_REMEMBER_ME = "shared_preferences_remember_me"
        const val URL_ORDERS = "https://kariyertechchallenge.mockable.io"

        fun getMonthString(context: Context, month: String): String {
            when (month){
                context.getString(R.string.mn_january) -> return context.getString(R.string.ms_january)
                context.getString(R.string.mn_february) -> return context.getString(R.string.ms_february)
                context.getString(R.string.mn_march) -> return context.getString(R.string.ms_march)
                context.getString(R.string.mn_april) -> return context.getString(R.string.ms_april)
                context.getString(R.string.mn_may) -> return context.getString(R.string.ms_may)
                context.getString(R.string.mn_june) -> return context.getString(R.string.ms_june)
                context.getString(R.string.mn_july) -> return context.getString(R.string.ms_july)
                context.getString(R.string.mn_august) -> return context.getString(R.string.ms_august)
                context.getString(R.string.mn_september) -> return context.getString(R.string.ms_september)
                context.getString(R.string.mn_october) -> return context.getString(R.string.ms_october)
                context.getString(R.string.mn_november) -> return context.getString(R.string.ms_november)
                context.getString(R.string.mn_december) -> return context.getString(R.string.ms_december)
            }

            return context.getString(R.string.ms_february)
        }
    }
}