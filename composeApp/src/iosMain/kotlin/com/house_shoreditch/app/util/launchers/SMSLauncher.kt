package com.house_shoreditch.app.util.launchers

import com.house_shoreditch.app.di.UIViewControllerWrapper
import platform.MessageUI.MFMessageComposeViewController
import platform.MessageUI.MFMessageComposeViewControllerDelegateProtocol
import platform.MessageUI.MessageComposeResult
import platform.darwin.NSObject

class SMSLauncher(private val rootViewControllerWrapper: UIViewControllerWrapper) {
    fun sendSMS(phoneNumber: String, message: String) {
        if (!MFMessageComposeViewController.canSendText()) {
            println("Cannot send SMS. Check device capabilities.")
            return
        }

        val smsComposer = MFMessageComposeViewController().apply {
            recipients = listOf(phoneNumber)
            body = message
            messageComposeDelegate = object : NSObject(), MFMessageComposeViewControllerDelegateProtocol {
                override fun messageComposeViewController(
                    controller: MFMessageComposeViewController,
                    didFinishWithResult: MessageComposeResult
                ) {
                    controller.dismissViewControllerAnimated(true, null)
                }
            }
        }

        rootViewControllerWrapper.viewController.presentViewController(smsComposer, animated = true, completion = null)
    }
}
