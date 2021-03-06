package com.vanced.manager.ui.dialogs

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vanced.manager.R
import com.vanced.manager.utils.InternetTools.openUrl
import com.vanced.manager.utils.MiuiHelper

object DialogContainer {

    fun showSecurityDialog(context: Context) {
        MaterialAlertDialogBuilder(context).apply {
            setTitle(context.resources.getString(R.string.welcome))
            setMessage(context.resources.getString(R.string.security_context))
            setPositiveButton(context.resources.getString(R.string.close)) { dialog, _ ->
                dialog.dismiss()
            }
            setOnDismissListener {
                if (MiuiHelper.isMiui()) {
                    showMiuiDialog(context)
                }
            }
            setOnCancelListener {
                if (MiuiHelper.isMiui()) {
                    showMiuiDialog(context)
                }
            }
            create()
            show()
        }
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit { putBoolean("firstLaunch", false) }
    }

    private fun showMiuiDialog(context: Context) {
        MaterialAlertDialogBuilder(context).apply {
            setTitle(context.getString(R.string.miui_one_title))
            setMessage(context.getString(R.string.miui_one))
            setNeutralButton(context.getString(R.string.close)) { dialog, _ -> dialog.dismiss() }
            setPositiveButton(context.getString(R.string.guide)) { _, _ ->
                openUrl(
                    "https://telegra.ph/How-to-install-v15-on-MIUI-02-11",
                    R.color.Telegram,
                    context
                )
            }
            setCancelable(false)
            create()
            show()
        }
    }

    fun statementFalse(context: Context) {
        MaterialAlertDialogBuilder(context).apply {
            setTitle("Wait what?")
            setMessage("So this statement is false huh? I'll go with True!")
            setPositiveButton("wut?") { dialog, _ -> dialog.dismiss() }
            create()
            show()
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit { putBoolean("statement", true) }
    }

    fun installAlertBuilder(msg: String, fullMsg: String?, context: Context) {
        MaterialAlertDialogBuilder(context).apply {
            setTitle(context.getString(R.string.error))
            setMessage(msg)
            when (msg) {
                context.getString(R.string.installation_signature) -> {
                    setPositiveButton(context.getString(R.string.guide)) { _, _ ->
                        openUrl("https://lmgtfy.com/?q=andnixsh+apk+verification+disable", R.color.Twitter, context)
                    }
                    setNeutralButton(context.getString(R.string.close)) { dialog, _ -> dialog.dismiss() }
                    if (fullMsg != null)
                        setNegativeButton(context.getString(R.string.advanced)) { _, _ -> basicDialog(context.getString(R.string.advanced), fullMsg, context) }
                }
                context.getString(R.string.installation_miui) -> {
                    setPositiveButton(context.getString(R.string.guide)) { _, _ ->
                        openUrl("https://telegra.ph/How-to-install-v15-on-MIUI-02-11", R.color.Telegram, context)
                    }
                    setNeutralButton(context.getString(R.string.close)) { dialog, _ -> dialog.dismiss() }
                    if (fullMsg != null)
                        setNegativeButton(context.getString(R.string.advanced)) { _, _ -> basicDialog(context.getString(R.string.advanced), fullMsg, context) }
                }
                else -> {
                    setPositiveButton(context.getString(R.string.close)) { dialog, _ -> dialog.dismiss() }
                    if (fullMsg != null)
                        setNegativeButton(context.getString(R.string.advanced)) { _, _ -> basicDialog(context.getString(R.string.advanced), fullMsg, context) }
                }
            }
            create()
            show()
        }
    }

    fun basicDialog(title: String, msg: String, context: Context) {
        MaterialAlertDialogBuilder(context).apply {
            setTitle(title)
            setMessage(msg)
            setPositiveButton(context.getString(R.string.close)) { dialog, _ -> dialog.dismiss() }
            create()
            show()
        }
    }

    fun launchVanced(context: Context) {
        val intent = Intent()
        intent.component =
            if (PreferenceManager.getDefaultSharedPreferences(context).getString("vanced_variant", "nonroot") == "root")
                ComponentName("com.google.android.youtube", "com.google.android.youtube.HomeActivity")
            else
                ComponentName("com.vanced.android.youtube", "com.google.android.youtube.HomeActivity")
                
        MaterialAlertDialogBuilder(context).apply {
            setTitle(context.getString(R.string.success))
            setMessage(context.getString(R.string.vanced_installed))
            setPositiveButton(context.getString(R.string.launch)) { _, _ ->
                startActivity(context, intent, null)
            }
            setNegativeButton(context.getString(R.string.close)) { dialog, _ -> dialog.dismiss() }
            create()
            show()
        }
    }
    
    fun launchMusic(activity: Activity) {
        val intent = Intent()
        intent.component = ComponentName("com.vanced.android.youtube.music", "com.vanced.android.youtube.music.MusicActivity")
        MaterialAlertDialogBuilder(activity).apply {
            setTitle(activity.getString(R.string.success))
            setMessage(activity.getString(R.string.music_installed))
            setPositiveButton(activity.getString(R.string.launch)) { _, _ ->
                startActivity(activity, intent, null)
            }
            setNegativeButton(activity.getString(R.string.close)) { dialog, _ -> dialog.dismiss() }
            create()
            show()
        }
    }

}
