package ru.nsu.dogsFetcher;

public class Application extends android.app.Application {
    public static Application application;

    public static Application getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
    }
}
