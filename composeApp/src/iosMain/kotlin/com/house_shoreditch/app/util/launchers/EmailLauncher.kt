package com.house_shoreditch.app.util.launchers

import com.house_shoreditch.app.di.UIViewControllerWrapper
import platform.MessageUI.MFMailComposeViewController

class EmailLauncher(private val rootViewControllerWarpper: UIViewControllerWrapper) {

    fun composeEmail(recipient: String, subject: String, body: String) {
        if (!MFMailComposeViewController.canSendMail()) {
            println("Cannot send mail. Configure an email account on the device.")
            return
        }

        val mailComposeViewController = MFMailComposeViewController().apply {
            setSubject(subject)
            setToRecipients(listOf(recipient))
            setMessageBody(body, false)
//            delegate = rootViewController
        }

        rootViewControllerWarpper.viewController.presentViewController(
            mailComposeViewController,
            animated = true,
            completion = null
        )
    }
}
