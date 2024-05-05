package com.kazakago.blueprint.ui.feature.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.model.about.AboutInfo
import com.kazakago.blueprint.model.about.AppInfo
import com.kazakago.blueprint.model.about.DeveloperInfo
import com.kazakago.blueprint.model.about.Email
import com.kazakago.blueprint.model.about.VersionCode
import com.kazakago.blueprint.model.about.VersionName
import com.kazakago.blueprint.ui.R
import com.kazakago.blueprint.ui.global.hooks.useActionView
import com.kazakago.blueprint.ui.global.hooks.useSendTo
import com.kazakago.blueprint.ui.global.theme.AppTheme
import com.kazakago.blueprint.ui.global.ui.BackIconButton
import com.kazakago.blueprint.ui.global.ui.DefaultLayout
import com.kazakago.swr.compose.state.SWRState
import kotlinx.serialization.Serializable
import java.net.URI
import java.net.URL
import java.util.Calendar

@Serializable
object About

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    state: SWRState<String, AboutInfo> = useAboutInfo(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.about_title)) },
                navigationIcon = { BackIconButton(onClick = onNavigateBack) },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        DefaultLayout(
            state = state,
            snackbarHostState = snackbarHostState,
            modifier = Modifier.padding(paddingValues),
        ) {
            AboutContent(it)
        }
    }
}

@Composable
private fun AboutContent(
    aboutInfo: AboutInfo,
    modifier: Modifier = Modifier,
) {
    val actionView: (URL) -> Unit = useActionView()
    val sendTo: (URI) -> Unit = useSendTo()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(32.dp))
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
            text = stringResource(id = R.string.about_ver, aboutInfo.appInfo.versionName.value, aboutInfo.appInfo.versionCode.value),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.about_develop_by, aboutInfo.developerInfo.name),
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .clickable(onClick = {
                    sendTo(aboutInfo.developerInfo.mailAddress.toURI())
                })
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
                .clickable(onClick = {
                    actionView(aboutInfo.developerInfo.siteUrl)
                })
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
            text = stringResource(id = R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), aboutInfo.developerInfo.name),
            style = MaterialTheme.typography.bodySmall,
        )
        Spacer(modifier = Modifier.size(32.dp))
    }
}

@Preview
@Composable
fun AboutScreenPreview() {
    AppTheme {
        AboutScreen(
            onNavigateBack = {},
            state = SWRState.empty(
                data = AboutInfo(
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
            ),
        )
    }
}
