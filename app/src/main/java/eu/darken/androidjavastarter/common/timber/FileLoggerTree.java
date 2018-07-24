package eu.darken.androidjavastarter.common.timber;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import eu.darken.androidjavastarter.App;
import eu.darken.androidjavastarter.BuildConfig;
import timber.log.Timber;


@SuppressLint("LogNotTimber")
public class FileLoggerTree extends Timber.Tree {
    static final String TAG = App.logTag("FileLoggerTree");
    private OutputStreamWriter logWriter = null;
    private final File logFile;
    private final Context context;

    @SuppressLint({"SetWorldReadable"})
    public FileLoggerTree(Context context) throws IOException {
        this.context = context.getApplicationContext();

        File logDir = new File(this.context.getExternalCacheDir(), "logfiles");
        //noinspection ResultOfMethodCallIgnored
        logDir.mkdirs();
        logFile = new File(logDir, BuildConfig.APPLICATION_ID + "_logfile_" + System.currentTimeMillis() + ".log");

        if (logFile.createNewFile()) Log.i(TAG, "File logger writing to " + logFile.getPath());
        if (logFile.setReadable(true, false)) Log.i(TAG, "Debug run log read permission set");

        try {
            logWriter = new OutputStreamWriter(new FileOutputStream(logFile));
            logWriter.write("=== BEGIN ===\n");
            logWriter.write("Logfile: " + logFile + "\n");
            logWriter.flush();
            Log.i(TAG, "File logger started.");
            Runtime.getRuntime().addShutdownHook(new Thread(this::printEnd));
        } catch (IOException e) {
            e.printStackTrace();
            //noinspection ResultOfMethodCallIgnored
            logFile.delete();
            if (logWriter != null) logWriter.close();
        }
    }

    public File getLogFile() {
        return logFile;
    }

    private synchronized void printEnd() {
        if (logWriter == null) return;
        try {
            logWriter.write("=== END ===\n");
            logWriter.close();
        } catch (IOException ignore) {
        }
        Log.i(TAG, "File logger stopped.");
    }

    @Override
    protected void log(int priority, String tag, @NonNull String message, Throwable t) {
        if (logWriter == null) return;
        try {
            logWriter.write(String.valueOf(System.currentTimeMillis()) + " " + priorityToString(priority) + "/" + tag + ": " + message + "\n");
            logWriter.flush();
        } catch (IOException e) {
            Timber.tag(TAG).e(e);
            if (logWriter != null) {
                try { logWriter.close(); } catch (IOException ignore) {}
            }
            logWriter = null;
        }
    }

    private static String priorityToString(int priority) {
        switch (priority) {
            case Log.ERROR:
                return "E";
            case Log.WARN:
                return "W";
            case Log.INFO:
                return "I";
            case Log.DEBUG:
                return "D";
            case Log.VERBOSE:
                return "V";
            default:
                return String.valueOf(priority);
        }
    }

}
