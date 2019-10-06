package com.tzapps.book.fp.templatemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;


@FunctionalInterface
interface Network {
    boolean post(String message);
}


public class App {

    public static boolean logIn(String userName, String password) {
        System.out.println("Login " + userName + ":" + password);
        return true;
    }

    public static boolean sendData(String data) {
        System.out.println("Send data: " + data);
        return true;
    }

    public static void logOut() {
        System.out.println("Logout");
    }

    public static Network makeNetwork(BiFunction<String, String, Boolean> login,
                                      Function<String, Boolean> senddata,
                                      Runnable logout) {
        return (String message) -> {
            login.apply("user", "password");
            boolean response = senddata.apply(message);
            logout.run();
            return response;
        };
    }


    public static void main(String[] args) {
        Network facebook = makeNetwork(App::logIn, App::sendData, App::logOut);

        facebook.post("My Message");

        /*
Login user:password
Send data: My Message
Logout
*/

    }
}



