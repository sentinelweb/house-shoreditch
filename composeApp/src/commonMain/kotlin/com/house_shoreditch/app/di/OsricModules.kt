package com.house_shoreditch.app.di

import com.house_shoreditch.app.main.MainViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object OsricModules {

    fun initKoin() {
        startKoin {
            modules(listOf(mainModule))
        }
    }

    val mainModule = module {
        viewModel {
            MainViewModel()
        }
    }
}
