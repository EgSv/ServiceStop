package ru.startandroid.develop.servicestop

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

const val LOG_TAG = "myLogs"

class MyService : Service() {
    private var es: ExecutorService? = null
    private var someRes: Any? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "MyService onCreate")
        es = Executors.newFixedThreadPool(3)
        someRes = Any()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "MyService onDestroy")
        someRes = null
    }

    override fun onStartCommand(intent: Intent?,
                                flags: Int,
                                startId: Int): Int {
        Log.d(LOG_TAG, "MyService onStartCommand")
        val time: Int = intent!!.getIntExtra("time", 1)
        val mr = MyRun(time, startId)
        es!!.execute(mr)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(arg0: Intent?): IBinder? {
        return null
    }

    internal inner class MyRun(var time: Int,
                               var startId: Int) : Runnable {
        init {
            Log.d(LOG_TAG, "MyRun# $startId create")
        }

        override fun run() {
            Log.d(LOG_TAG, "MyRun# $startId start, time = $time")
            try {
                Thread.sleep(time.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            try {
                Log.d(LOG_TAG, "MyRun# $startId someRes = ${someRes!!.javaClass}")
            } catch (e: NullPointerException) {
                Log.d(LOG_TAG, "MyRun# $startId error, null pointer")
            }
            stop()
        }
        private fun stop() {
            Log.d(LOG_TAG, "MyRun# $startId  end, stopSelf($startId) = ${stopSelfResult(startId)}")
            //stopSelf(startId)
        }
    }
}