package com.kazakago.blueprint.presentation.view.hierarchy.about

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

class OssLicensesMenuActivityContract : ActivityResultContract<Unit, ActivityResult>() {
    override fun createIntent(context: Context, input: Unit) = Intent(context, OssLicensesMenuActivity::class.java)
    override fun parseResult(resultCode: Int, intent: Intent?) = ActivityResult(resultCode, intent)
}
