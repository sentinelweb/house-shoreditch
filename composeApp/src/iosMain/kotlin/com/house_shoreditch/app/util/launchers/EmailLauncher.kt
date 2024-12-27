package com.house_shoreditch.app.util.launchers

import platform.MessageUI.MFMailComposeViewController
import platform.UIKit.UIViewController

class EmailLauncher(private val rootViewController: UIViewController) {

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

        rootViewController.presentViewController(mailComposeViewController, animated = true, completion = null)
    }
}
