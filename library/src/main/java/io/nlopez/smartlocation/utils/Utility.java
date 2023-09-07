package io.nlopez.smartlocation.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class Utility {
    public static void registerReceiver(@NonNull Context context,
                                        @Nullable BroadcastReceiver receiver, @NonNull IntentFilter filter) {
        registerReceiver(context, receiver, filter, true);
    }

    public static void registerReceiver(@NonNull Context context,
                                        @Nullable BroadcastReceiver receiver, @NonNull IntentFilter filter,
                                        boolean listenToBroadcastsFromOtherApps) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            int receiverFlags = ContextCompat.RECEIVER_NOT_EXPORTED;
            if (listenToBroadcastsFromOtherApps) {
                receiverFlags = ContextCompat.RECEIVER_EXPORTED;
            } else {
                receiverFlags = ContextCompat.RECEIVER_NOT_EXPORTED;
            }
            try {
                ContextCompat.registerReceiver(context, receiver, filter, receiverFlags);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                context.registerReceiver(receiver, filter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
