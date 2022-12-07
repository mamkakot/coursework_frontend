package com.example.courseworkgum

import android.util.Log
import org.chromium.net.CronetException
import org.chromium.net.UrlRequest
import org.chromium.net.UrlResponseInfo
import java.nio.ByteBuffer

private const val TAG = "ChoreUrlRequestCallback"

class ChoreUrlRequestCallback: UrlRequest.Callback() {
    override fun onRedirectReceived(
        request: UrlRequest?,
        info: UrlResponseInfo?,
        newLocationUrl: String?
    ) {
        Log.i(TAG, "onRedirectReceived method called.")
        request?.followRedirect()
    }

    override fun onResponseStarted(request: UrlRequest?, info: UrlResponseInfo?) {
        Log.i(TAG, "onResponseStarted method called.")
        val httpStatusCode = info?.httpStatusCode
        if (httpStatusCode == 200) {
            // The request was fulfilled. Start reading the response.
            request?.read(myBuffer)
        } else if (httpStatusCode == 503) {
            // The service is unavailable. You should still check if the request
            // contains some data.
            request?.read(myBuffer)
        }
        responseHeaders = info?.allHeaders
    }

    override fun onReadCompleted(
        request: UrlRequest?,
        info: UrlResponseInfo?,
        byteBuffer: ByteBuffer?
    ) {
        Log.i(TAG, "onReadCompleted method called, info: $info")
        // You should keep reading the request until there's no more data.
        byteBuffer?.clear()
        request?.read(byteBuffer)
    }

    override fun onSucceeded(request: UrlRequest?, info: UrlResponseInfo?) {
        Log.i(TAG, "onSucceeded method called, info: $info\nrequest: $request")
    }

    override fun onFailed(request: UrlRequest?, info: UrlResponseInfo?, error: CronetException?) {
        Log.i(TAG, "onFailed method called. info: $info, error: $error \nrequest: $request")
    }
}