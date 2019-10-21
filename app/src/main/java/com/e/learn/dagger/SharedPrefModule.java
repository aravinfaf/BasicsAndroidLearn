package com.e.learn.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {

    Context context;

    public SharedPrefModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return context;
    }

    @Provides
    @Singleton
    public SharedPreferences providesharedpreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
