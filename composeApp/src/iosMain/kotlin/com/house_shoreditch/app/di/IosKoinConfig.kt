package com.house_shoreditch.app.di

import com.house_shoreditch.app.main.MainViewController
import com.house_shoreditch.app.util.launchers.EmailLauncher
import com.house_shoreditch.app.util.launchers.SMSLauncher
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.UIKit.UIViewController

// https://proandroiddev.com/how-to-get-your-koin-dependency-in-ios-efb6d83ee165
// https://github.com/InsertKoinIO/koin/issues/1492
data class UIViewControllerWrapper(val viewController: UIViewController)

object IosKoinConfig {

    fun startKoin() = start(
        listOf(
            iosControllerModule,
            iosLauncherModule
        )
    )


    private fun start(iosModules: List<Module>) {
        println("internal start")
        startKoin {
            modules(
                OasisModules.allModules + iosModules
            )
        }
        println("internal started")
    }

    private val iosControllerModule = module {
        single<UIViewControllerWrapper> { UIViewControllerWrapper(MainViewController()) }
    }

    private val iosLauncherModule = module {
        factory { EmailLauncher(get()) }
        factory { SMSLauncher(get()) }
    }
}

object IosClassFactory : KoinComponent {
    private val rootViewControllerWrapper: UIViewControllerWrapper by inject()
    val rootViewController: UIViewController by lazy { rootViewControllerWrapper.viewController }
}
