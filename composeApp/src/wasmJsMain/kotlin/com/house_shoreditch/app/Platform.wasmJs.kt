package com.house_shoreditch.app

class WasmPlatform: Platform {
    override val type: PlatformType = PlatformType.Web
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()
