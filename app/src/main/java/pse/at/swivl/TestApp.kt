package pse.at.swivl

import android.app.Application

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        testApp = this
    }

    companion object {
        private var testApp: TestApp? = null
        fun getInstance() = testApp!!
    }
}