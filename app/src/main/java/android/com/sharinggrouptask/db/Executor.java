package android.com.sharinggrouptask.db;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/******************************************************************************
 * Module: Executor
 *
 * File Name: Executor.java
 *
 * Description: Source file for Room Executor
 *
 * Author: Rana Tarek
 ******************************************************************************/
public class Executor {
    public static void IOThread(Runnable t) {
        ExecutorService IO_EXECUTOR = Executors.newSingleThreadExecutor();
        IO_EXECUTOR.execute(t);
    }
}
