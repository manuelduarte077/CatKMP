package dev.donmanuel.app.catkmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.UIKitView
import org.koin.core.module.Module
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import dev.donmanuel.app.catkmp.data.local.DatabaseDriverFactory
import dev.donmanuel.app.catkmp.data.local.IosDatabaseDriverFactory
import org.koin.dsl.module

actual val targetModule: Module = module {
    single<DatabaseDriverFactory> { IosDatabaseDriverFactory() }
}

@Composable
actual fun AlertDialog(
    title: String,
    message: String,
    onDismissRequest: () -> Unit
) {
    UIKitView(
        factory = {

            val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController

            val alert = UIAlertController.alertControllerWithTitle(
                title = title,
                message = message,
                preferredStyle = UIAlertControllerStyleAlert
            )

            val action =
                UIAlertAction.actionWithTitle("Aceptar", style = UIAlertActionStyleDefault, handler = {
                    alert.dismissViewControllerAnimated(flag = true, completion = null)
                    onDismissRequest()
                })

            alert.addAction(action)
            rootViewController?.presentViewController(alert, animated = true, completion = null)

            alert.view
        }
    )
}