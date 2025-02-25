package com.house_shoreditch.app.di

import com.house_shoreditch.app.main.MainViewController
import com.house_shoreditch.app.util.launchers.EmailLauncher
import com.house_shoreditch.app.util.launchers.SMSLauncher
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module
import platform.UIKit.UIViewController
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.ref.WeakReference

object IosKoinConfig {
    fun startKoin() {
        startKoin {
            modules(
                OasisModules.allModules + listOf(iosControllerModule, iosLauncherModule)
            )
        }
    }

    private val iosControllerModule = module {
        single<UIViewControllerHolder> { UIViewControllerHolder() }
    }

    private val iosLauncherModule = module {
        factory { EmailLauncher(get()) }
        factory { SMSLauncher(get()) }
    }
}

// https://github.com/InsertKoinIO/koin/issues/1492
@OptIn(ExperimentalNativeApi::class)
class UIViewControllerHolder() {
    private var viewControllerRef: WeakReference<UIViewController>? = null
    val viewController: UIViewController? get() = viewControllerRef?.get()

    fun createViewController(): UIViewController {
        MainViewController().apply {
            viewControllerRef = WeakReference(this)
            return@createViewController this
        }
    }

    fun cleanupViewController() {
        viewControllerRef?.clear()
        viewControllerRef = null
    }
}

object IosClassFactory : KoinComponent {
    fun getViewControllerHolder() = getKoin().get<UIViewControllerHolder>()
}
