package com.kazakago.blueprint.presentation.view.hierarchy.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.domain.model.hierarchy.about.*
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.global.theme.PreviewTheme
import com.kazakago.blueprint.presentation.view.global.ui.BackIconButton
import com.kazakago.blueprint.presentation.view.global.util.clickableWithRipple
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.about.AboutUiState
import java.net.URL
import java.util.*

@Composable
fun AboutScreen(
    uiState: AboutUiState,
    onClickBack: () -> Unit,
    onClickWebSite: (url: URL) -> Unit,
    onClickMail: (mail: Email) -> Unit,
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(id = R.string.about_title)) },
                navigationIcon = { BackIconButton(onClick = onClickBack) },
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = uiState.classify(
                    onLoading = { stringResource(id = R.string.loading) },
                    onCompleted = { stringResource(id = R.string.about_ver, it.appInfo.versionName.value, it.appInfo.versionCode.value) },
                ),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = uiState.classify(
                    onLoading = { stringResource(id = R.string.loading) },
                    onCompleted = { stringResource(id = R.string.about_develop_by, it.developerInfo.name) },
                ),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier
                    .clickableWithRipple(onClick = uiState.onDeveloperMailAddress { onClickMail(it) })
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(
                    text = stringResource(id = R.string.about_email),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Row(
                modifier = Modifier
                    .clickableWithRipple(onClick = uiState.onDeveloperSiteUrl { onClickWebSite(it) })
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Public,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(
                    text = stringResource(id = R.string.about_web_site),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = uiState.classify(
                    onLoading = { stringResource(id = R.string.loading) },
                    onCompleted = { stringResource(id = R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), it.developerInfo.name) },
                ),
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewAboutScreenOnLoading() {
    PreviewTheme {
        AboutScreen(
            uiState = AboutUiState.Loading,
            onClickBack = {},
            onClickMail = {},
            onClickWebSite = {},
        )
    }
}

@Preview
@Composable
fun PreviewAboutScreenOnCompleted() {
    PreviewTheme {
        AboutScreen(
            uiState = AboutUiState.Completed(
                appInfo = AppInfo(
                    versionCode = VersionCode(1),
                    versionName = VersionName("1.0.0"),
                ),
                developerInfo = DeveloperInfo(
                    name = "KazaKago",
                    mailAddress = Email("kazakago@gmail.com"),
                    siteUrl = URL("https://blog.kazakago.com"),
                ),
            ),
            onClickBack = {},
            onClickMail = {},
            onClickWebSite = {},
        )
    }
}
