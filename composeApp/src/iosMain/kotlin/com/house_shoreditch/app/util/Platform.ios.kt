package com.house_shoreditch.app.util

import platform.MessageUI.MFMailComposeViewController
import platform.MessageUI.MFMessageComposeViewController
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val type: PlatformType = PlatformType.Ios
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val isSmsAvailable: Boolean = MFMessageComposeViewController.canSendText()
    override val isEmailAvailable: Boolean = MFMailComposeViewController.canSendMail()
    override val isWhatsappAvailable: Boolean = true
}

actual fun getPlatform(): Platform = IOSPlatform()
