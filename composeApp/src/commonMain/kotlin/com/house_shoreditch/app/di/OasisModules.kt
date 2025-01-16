package com.house_shoreditch.app.di

import com.house_shoreditch.app.main.MainViewModel
import com.house_shoreditch.app.util.LinkLauncher
import com.house_shoreditch.app.util.MessageMapper
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import uk.co.sentinelweb.cuer.hub.Secrets

object OasisModules {

    fun initKoin() {
        startKoin {
            modules(allModules)
        }
    }

    private val mainModule = module {
        viewModel {
            MainViewModel(
                linkLauncher = get(),
                secrets = Secrets
            )
        }
    }

    private val utilModule = module {
        factory { MessageMapper() }
        factory { LinkLauncher() }
    }

    val allModules = listOf(mainModule, utilModule)
}
