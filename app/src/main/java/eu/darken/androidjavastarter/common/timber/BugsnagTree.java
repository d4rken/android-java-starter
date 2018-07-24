package eu.darken.androidjavastarter.common.timber;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;

import timber.log.Timber;


public class BugsnagTree extends Timber.Tree {
    private static final int BUFFER_SIZE = 200;

    // Adding one to the initial size accounts for the add before remove.
    private final Deque<String> buffer = new ArrayDeque<>(BUFFER_SIZE + 1);

    @Override
    protected void log(int priority, String tag, @NonNull String message, Throwable t) {
        message = System.currentTimeMillis() + " " + priorityToString(priority) + "/" + tag + ": " + message;
        synchronized (buffer) {
            buffer.addLast(message);
            if (buffer.size() > BUFFER_SIZE) {
                buffer.removeFirst();
            }
        }
    }

    public void update(@NonNull com.bugsnag.android.Error error) {
        synchronized (buffer) {
            int i = 1;
            for (String message : buffer) error.addToTab("Log", String.format(Locale.US, "%03d", i++), message);
            error.addToTab("Log", String.format(Locale.US, "%03d", i), Log.getStackTraceString(error.getException()));
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
