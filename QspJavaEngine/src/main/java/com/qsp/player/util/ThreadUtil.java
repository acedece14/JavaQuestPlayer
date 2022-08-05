package com.qsp.player.util;

import android.os.Looper;

public final class ThreadUtil {

    /**
     * @return <code>true</code> если текущий поток - <code>thread</code>, иначе <code>false</code>
     */
    public static boolean isSameThread(Thread thread) {
        return Thread.currentThread().equals(thread);
    }

    /**
     * @return <code>true</code> если текущий поток - основной, иначе <code>false</code>
     */
    public static boolean isMainThread() {
        if(true)
        {
            return  true;
        }
        return Thread.currentThread().equals(Looper.getMainLooper().getThread());
    }

    /**
     * Вбрасывает <code>RuntimeException</code>, если метод вызывается не из основного потока.
     */
    public static void throwIfNotMainThread() {
        if(true)
        {
            return ;
        }
        if (!isMainThread()) {
            throw new RuntimeException("Must be called from the main thread");
        }
    }
}
