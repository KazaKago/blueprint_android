package com.kazakago.blueprint.presentation.hierarchy.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.kazakago.blueprint.domain.model.about.*
import com.kazakago.blueprint.presentation.R
import com.kazakago.blueprint.presentation.global.theme.PreviewTheme
import com.kazakago.blueprint.presentation.global.ui.BackIconButton
import com.kazakago.blueprint.presentation.global.util.QueryResult
import com.kazakago.blueprint.presentation.global.util.useActionView
import com.kazakago.blueprint.presentation.global.util.useSendTo
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import java.net.URI
import java.net.URL
import java.util.*

@Destination
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AboutScreen(
    navigator: DestinationsNavigator,
    query: QueryResult<AboutInfo> = queryAboutInfo(),
) {
    val actionView: (URL) -> Unit = useActionView()
    val sendTo: (URI) -> Unit = useSendTo()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.about_title)) },
                navigationIcon = { BackIconButton(onClick = navigator::popBackStack) },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                text = if (query.data != null) {
                    stringResource(id = R.string.about_ver, query.data.appInfo.versionName.value, query.data.appInfo.versionCode.value)
                } else {
                    stringResource(id = R.string.loading)
                },
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = if (query.data != null) {
                    stringResource(id = R.string.about_develop_by, query.data.developerInfo.name)
                } else {
                    stringResource(id = R.string.loading)
                },
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier
                    .clickable(onClick = {
                        if (query.data != null) {
                            sendTo(query.data.developerInfo.mailAddress.toURI())
                        }
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
                        if (query.data != null) {
                            actionView(query.data.developerInfo.siteUrl)
                        }
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
                text = if (query.data != null) {
                    stringResource(id = R.string.about_copyright, Calendar.getInstance().get(Calendar.YEAR), query.data.developerInfo.name)
                } else {
                    stringResource(id = R.string.loading)
                },
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Preview
@Composable
fun PreviewAboutScreenOnLoading() {
    PreviewTheme {
        AboutScreen(
            navigator = EmptyDestinationsNavigator,
            query = QueryResult.forPreview(),
        )
    }
}

@Preview
@Composable
fun PreviewAboutScreenOnCompleted() {
    PreviewTheme {
        AboutScreen(
            navigator = EmptyDestinationsNavigator,
            query = QueryResult.forPreview(
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
