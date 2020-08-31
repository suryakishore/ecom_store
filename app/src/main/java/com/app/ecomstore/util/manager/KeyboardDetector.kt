package com.app.ecomstore.util.manager

import android.app.Activity
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import io.reactivex.Observable

/*listens the keyboard events*/
open class KeyboardDetector constructor(private val activity: Activity?) {
    companion object {
        val TAG = KeyboardDetector::class.java
        const val MIN_KEYBOARD_HEIGHT_RATIO = 0.15
    }

    fun observe(): Observable<KeyboardStatus> {
        if (activity == null) {
            return Observable.just(KeyboardStatus.CLOSED)
        }
        val rootView = (activity.findViewById<View>(android.R.id.content) as ViewGroup?)
        val windowHeight = DisplayMetrics().let {
            activity.windowManager.defaultDisplay.getMetrics(it)
            it.heightPixels
        }

        return Observable.create<KeyboardStatus> { emitter ->
            val listener = ViewTreeObserver.OnGlobalLayoutListener {
                if (rootView == null) {
                    emitter.onNext(KeyboardStatus.CLOSED)
                    return@OnGlobalLayoutListener
                }
                val rect = Rect().apply { rootView.getWindowVisibleDisplayFrame(this) }
                val keyboardHeight = windowHeight - rect.height()

                if (keyboardHeight > windowHeight * MIN_KEYBOARD_HEIGHT_RATIO) {
                    emitter.onNext(KeyboardStatus.OPENED)
                } else {
                    emitter.onNext(KeyboardStatus.CLOSED)
                }
            }

            rootView?.let {
                it.viewTreeObserver.addOnGlobalLayoutListener(listener)

                emitter.setCancellable {
                    it.viewTreeObserver.removeOnGlobalLayoutListener(listener)
                }
            }
        }.distinctUntilChanged()
    }
}

enum class KeyboardStatus {
    OPENED,
    CLOSED
}