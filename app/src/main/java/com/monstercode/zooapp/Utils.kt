package com.monstercode.zooapp

import android.content.ClipData
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.annotation.NonNull


class Utils {
    companion object {
        private const val tag: String = "Utils"
        private const val minPasswordLength = 2
        fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && (email.contains("finance.go.ug")
                || email.contains("ssenyonjo")
                )

        fun isValidPassword(password: String): Boolean = password.trim().length >= minPasswordLength

        fun setClickableAnimation(context: Context?, view: View) {
            val attrs = intArrayOf(R.attr.selectableItemBackground)
            val typedArray = context!!.obtainStyledAttributes(attrs)
            val backgroundResource = typedArray.getResourceId(0, 0)
            view.setBackgroundResource(backgroundResource)
            typedArray.recycle()
        }

        fun capitalized(s: String?) = (s ?: "").toLowerCase().capitalize()

        fun getSharedP(@NonNull context: Context) = context.getSharedPreferences(
            context.getString(R.string.shared_prefs), Context.MODE_PRIVATE
        )

        fun removeTokens(context: Context?) {
            Log.d(tag, "Starting to remove tokens")
            if (context != null) {
                getSharedP(context).edit().clear().apply()
            }
        }


        fun toClipboard(
            context: Context,
            text: String?
        ) {
            val clipboard =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = ClipData.newPlainText("Copied Text", text ?: "Empty")
            clipboard.setPrimaryClip(clip)
        }


//        fun emptyDb(context: Context) {
//            doAsync {
//                AppDatabase(context).clearAllTables()
//            }
//        }
//


    }
}