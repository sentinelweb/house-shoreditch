import UIKit
import SwiftUI
import OasisShoreditch

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return IosClassFactory.shared.getViewControllerHolder().createViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}

    func makeCoordinator() -> Coordinator {
        Coordinator()
    }

    class Coordinator {
        deinit {
            IosClassFactory.shared.getViewControllerHolder().cleanupViewController()
        }
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard)
    }
}



