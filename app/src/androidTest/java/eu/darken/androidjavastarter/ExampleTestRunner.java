package eu.darken.androidjavastarter;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class ExampleTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        return super.newApplication(cl, ExampleApplicationMock.class.getName(), context);
    }
}