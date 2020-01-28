package com.monstercode.zooapp

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.NonNull
import org.jetbrains.anko.design.indefiniteSnackbar
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.find


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

        fun logd(context: Context, message: String) {
            Log.d(context.javaClass.name, message)
        }

        // https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
        fun hideKeyboard(activity: Activity) {
            val imm: InputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view = activity.currentFocus
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }


        fun snack(context: Context, message: String) {
            val activity = context as Activity
            hideKeyboard(activity)
            activity.find<View>(android.R.id.content).longSnackbar(message).show()
        }

        fun indefiniteSnack(activity: Activity, message: String) {
            hideKeyboard(activity)
            activity.find<View>(android.R.id.content).indefiniteSnackbar(message).show()
        }

//        fun emptyDb(context: Context) {
//            doAsync {
//                AppDatabase(context).clearAllTables()
//            }
//        }
//


    }
}