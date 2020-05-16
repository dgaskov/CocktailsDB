package ru.nsu.cocktailDB;

import ru.nsu.cocktailDB.data.network.APIProvider;

public class Application extends android.app.Application {
    public static Application application;

    public static APIProvider apiProvider;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        apiProvider = new APIProvider();
    }
}
