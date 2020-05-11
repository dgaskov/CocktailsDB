package ru.nsu.alcoHelper;

import ru.nsu.alcoHelper.data.network.APIProvider;

public class Application extends android.app.Application {
    public static Application application;

    public static Application getInstance() {
        return application;
    }

    public static APIProvider apiProvider;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        apiProvider = new APIProvider();
    }
}
