package com.house_shoreditch.app.di

import com.house_shoreditch.app.Secrets
import com.house_shoreditch.app.main.MainViewModel
import com.house_shoreditch.app.util.Launcher
import com.house_shoreditch.app.util.MessageMapper
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object OasisModules {

    fun initKoin() {
        startKoin {
            modules(allModules)
        }
    }

    private val mainModule = module {
        viewModel {
            MainViewModel(
                launcher = get(),
                secrets = Secrets
            )
        }
    }

    private val utilModule = module {
        factory { MessageMapper() }
        factory { Launcher() }
    }

    val allModules = listOf(mainModule, utilModule)
}
