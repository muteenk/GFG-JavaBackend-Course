package session2;

public class LazySingleton {

    private static LazySingleton singletonInstance = null;

    public String s;
    private LazySingleton() {
        s = "HELLO THIS IS LAZY SINGLETON";
    }

    public static synchronized LazySingleton getInstance() {
        if (singletonInstance == null)
            LazySingleton.singletonInstance = new LazySingleton();

        return LazySingleton.singletonInstance;
    }

}
