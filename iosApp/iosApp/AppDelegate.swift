//
//  AppDelegate.swift
//  iosApp
//
//  Created by rob munro on 22/12/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit
import ComposeApp

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        OsricModules.shared.doInitKoin()
    
        return true
    }
}
