package com.house_shoreditch.app.util.launchers

import com.house_shoreditch.app.di.UIViewControllerHolder
import platform.MessageUI.MFMessageComposeViewController
import platform.MessageUI.MFMessageComposeViewControllerDelegateProtocol
import platform.MessageUI.MessageComposeResult
import platform.MessageUI.MessageComposeResult.*
import platform.darwin.NSObject

class SMSLauncher(private val rootViewControllerWrapper: UIViewControllerHolder) {

    private val messageComposeDelegate = object : NSObject(), MFMessageComposeViewControllerDelegateProtocol {
        override fun messageComposeViewController(
            controller: MFMessageComposeViewController,
            didFinishWithResult: MessageComposeResult
        ) {
            when (didFinishWithResult) {
                MessageComposeResultCancelled -> {
                    println("Message cancelled")
                }

                MessageComposeResultSent -> {
                    println("Message sent")
                }

                MessageComposeResultFailed -> {
                    println("Message failed to send")
                }

                else -> {
                    println("Unknown result")
                }
            }

            // Dismiss the view controller
            controller.dismissViewControllerAnimated(
                true,
                completion = { println("dismissViewControllerAnimated.complete") })
        }
    }

    fun sendSMS(phoneNumber: String, message: String) {
        if (!MFMessageComposeViewController.canSendText()) {
            println("Cannot send SMS. Check device capabilities.")
            return
        }

        val smsComposer = MFMessageComposeViewController().apply {
            recipients = listOf(phoneNumber)
            body = message
            messageComposeDelegate = this@SMSLauncher.messageComposeDelegate
        }

        rootViewControllerWrapper.viewController?.presentViewController(
            smsComposer,
            animated = true,
            completion = { println("SMS process completion") }
        )?:error("Cannot present SMS composer: viewController is cleaned up")
    }
}
