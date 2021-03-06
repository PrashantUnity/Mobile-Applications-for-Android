package com.minkov.demos.mvp;

import android.content.Context;

import com.minkov.demos.mvp.base.CurrentActivityProvider;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by minkov on 9/27/17.
 */

@Module
public class ConfigModule {
    // Mac
    // private static final String BASE_URL = "http://192.168.160.7:3001/";

    // PC
//    private static final String BASE_URL = "http://192.168.168.44:3001/";

    // Laptop
    private static final String BASE_URL = "http://192.168.160.17:3001/";

    /**
     * Provide {@link String} named "base-url"
     *
     * @return the url base of the RESTfull api
     */
    @Provides
    @Named("base-url")
    String providesBaseUrl() {
        return BASE_URL;
    }

    @Provides
    CurrentActivityProvider providesCurrentActivityProvider(Context context) {
        return ((PersonsApplication) context.getApplicationContext());
    }
}
