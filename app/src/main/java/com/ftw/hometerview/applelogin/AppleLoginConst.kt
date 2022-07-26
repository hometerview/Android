package com.ftw.hometerview.applelogin

class AppleLoginConst {

    companion object {
        //APPLE SIGN IN
        const val APPLE_CLIENT_ID = "ftw.com.hometerview"
        const val APPLE_REDIRECT_URI = "https://hometerview.firebaseapp.com/__/auth/handler"  //Must match with the apple developer site's redirect uri
        const val APPLE_SCOPE = "name%20email"
        const val APPLE_AUTHURL = "https://appleid.apple.com/auth/authorize"
    }
}