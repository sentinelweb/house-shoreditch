package com.house_shoreditch.app.util.launchers

import com.house_shoreditch.app.di.UIViewControllerWrapper
import platform.Foundation.NSError
import platform.MessageUI.*
import platform.MessageUI.MessageComposeResult.*
import platform.darwin.NSObject

class EmailLauncher(private val rootViewControllerWarpper: UIViewControllerWrapper) {

    private val mailComposeDelegate = object : NSObject(), MFMailComposeViewControllerDelegateProtocol {
        override fun mailComposeController(
            controller: MFMailComposeViewController,
            didFinishWithResult: MFMailComposeResult,
            error: NSError?
        ) {
            when (didFinishWithResult) {
                MFMailComposeResult.MFMailComposeResultCancelled -> {
                    println("Mail cancelled")
                }
                MFMailComposeResult.MFMailComposeResultSaved -> {
                    println("Mail saved as draft")
                }
                MFMailComposeResult.MFMailComposeResultSent -> {
                    println("Mail successfully sent")
                }
                MFMailComposeResult.MFMailComposeResultFailed -> {
                    println("Mail failed to send: ${error?.localizedDescription}")
                }
                else -> {
                    println("Unknown result")
                }
            }
            // Dismiss the mail composer
            controller.dismissViewControllerAnimated(true, completion = null)
        }
    }

    fun composeEmail(recipient: String, subject: String, body: String) {
        if (!MFMailComposeViewController.canSendMail()) {
            println("Cannot send mail. Configure an email account on the device.")
            return
        }

        val mailComposeViewController = MFMailComposeViewController().apply {
            setSubject(subject)
            setToRecipients(listOf(recipient))
            setMessageBody(body, false)
            mailComposeDelegate = this@EmailLauncher.mailComposeDelegate
        }

        rootViewControllerWarpper.viewController.presentViewController(
            mailComposeViewController,
            animated = true,
            completion = {  }
        )
    }
}
