package com.tzapps.book.fp.closure;

@FunctionalInterface
interface Command {
    void addAmount(Integer ammount);
}

public class App {

    public static Command CashRegister(MyLogger logger) {
        //some local state
        final Integer[] total = {0};

        return (Integer ammount) -> {
            total[0] += ammount;
            logger.info(total[0]);
        };
    }

    public static void main(String[] args) {
        final MyLogger logger = new MyLogger();

        //Obtain a reference to closure scope and use it
        Command cashRegister = CashRegister(logger);
        cashRegister.addAmount(10);
        cashRegister.addAmount(3);

        //Obtain new reference to closure scope and use it
        cashRegister = CashRegister(logger);
        cashRegister.addAmount(1);
        cashRegister.addAmount(3);

        /*
        *Total is: 10
        Total is: 13

        Total is: 1
        Total is: 4

        * */
    }
}

class MyLogger {
    void info(Integer total){
        System.out.println("Total is: " + total);
    }
}




