package mx.openpay.challenge.tools

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import mx.openpay.challenge.R
import mx.openpay.challenge.ui.HomeActivity
import kotlin.random.Random

object Utils {

    fun showNotification(activity: Activity, description: String) {
        var builder: Notification.Builder? = null
        val notificationChannel: NotificationChannel
        val notificationManager: NotificationManager =
            activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(activity, HomeActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(
                    activity.getString(R.string.app_notification_chanel_id),
                    activity.getString(R.string.app_notification),
                    NotificationManager.IMPORTANCE_HIGH
                )
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(
                activity,
                activity.getString(R.string.app_notification_chanel_id)
            )
                .setContentTitle(activity.getString(R.string.app_name))
                .setContentText(description)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        activity.resources, R.mipmap.ic_launcher
                    )
                ).setContentIntent(pendingIntent)
        }
        notificationManager.notify(Random(100).nextInt(), builder?.build())
    }

}