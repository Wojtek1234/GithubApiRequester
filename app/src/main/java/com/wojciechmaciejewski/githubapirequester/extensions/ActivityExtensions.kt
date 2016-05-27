package pl.stsg.e_learning.extension

import android.app.Activity
import android.app.DialogFragment
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.view.View

inline fun <reified T : View> Activity.find(id: Int): T = findViewById(id) as T
fun AppCompatActivity.showDialogFragment(): (dialog: DialogFragment) -> Unit {
    return {
        it.show(fragmentManager, null)
    }
}

fun AppCompatActivity.startActivityFromClass(): (classa: Class<out Activity>) -> Unit {
    return {
        val intent = Intent(this, it)
        startActivity(intent)
    }
}

inline fun doAfterBeforeLollipop(after: () -> Unit, before: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        after()
    } else {
        before()
    }
}

inline fun doAfterLollipop(after: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        after()
    }
}