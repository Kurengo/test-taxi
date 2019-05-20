package com.example.test_task;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.test_task.net.NetManager;
import com.example.test_task.net.NetManagerImpl;
import com.example.test_task.repository.RepositoryManager;

public class App extends Application {
    private NetManager netManager;
    private RepositoryManager repositoryManager;
    private static App instance;
    private Boolean wasInBackground;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initManagers();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbackImpl());
    }

    private void initManagers() {
        netManager = new NetManagerImpl();
        repositoryManager = new RepositoryManager(netManager);
    }

    public RepositoryManager getRepositoryManager() {
        return repositoryManager;
    }

    public boolean wasInBackground() {
        if (wasInBackground == null) {
            wasInBackground = true;
        }
        return wasInBackground;
    }

    public static App getInstance() {
        return instance;
    }

    private class ActivityLifecycleCallbackImpl implements Application.ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            if (wasInBackground != null) {
                wasInBackground = false;
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}