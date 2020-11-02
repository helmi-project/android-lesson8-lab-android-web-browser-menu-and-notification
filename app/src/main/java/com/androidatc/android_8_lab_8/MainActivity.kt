package com.androidatc.android_8_lab_8

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebViewClient
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The id of the notification channel
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val id = "my_channel_02"

        // The user-visible name of the channel
        val name = getString(R.string.abc_action_bar_home_description)

        // The user-visible description of the channel
        val description = getString(R.string.abc_action_bar_home_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(id, name, importance)

        // Configure the notification channel
        mChannel.description = description
        mChannel.enableLights(true)

        // Sets the notification light color for notification posted to this channel,
        //if the device supports this feature
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)

        mNotificationManager.createNotificationChannel(mChannel)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mywebview.setWebViewClient(WebViewClient())

        when(item.itemId) {
            R.id.item1 -> {mywebview.loadUrl("http://www.androidatc.com")

                // The id of the channel
                val CHANNEL_ID = "my_channel_02"

                // Using NotificationCompat.Builder to add the notification objects
                val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle("Android ATC Notification")
                    .setContentText("Check Android ATC New Course !!")

                // Creates an explicit intent for an Activity in your app
                val resultIntent = Intent(this, ResultActivity::class.java)

                /*
                The stack builder object will contain an artificial back stack for the started Activity.
                This ensures that navigating backward from the Activity leads out of your app to the
                Home Screen.
                */
                val stackBuilder = TaskStackBuilder.create(this)

                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent)
                val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

                mBuilder.setContentIntent(resultPendingIntent)

                val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                /*
                mNotificationId is a unique integer your app uses to identify the notification.
                For example, to cancel the notification, you can pass its ID number to
                NotificationManager.cancel().
                */
                mNotificationManager.notify(2, mBuilder.build())

                return super.onOptionsItemSelected(item)
            }

            R.id.item2 -> {mywebview.loadUrl("http://www.pearsonvue.com/androidatc")
                return super.onOptionsItemSelected(item)
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun gotoweb(view: View) {

        // Configure your WebView to enable JavaScript, download images and enable scrollbar
        mywebview.settings.javaScriptEnabled = true
        mywebview.settings.loadsImagesAutomatically = true
        mywebview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        // The URL of the web sites will be taken from url_address TextView widget which will work
        // as address bar
        var url = url_address.text.toString()

        // To avoid WebView to launch the default browser
        mywebview.setWebViewClient(WebViewClient())

        // Shows the URL
        mywebview.loadUrl(url)
    }
}
