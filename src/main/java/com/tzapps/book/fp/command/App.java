package com.tzapps.book.fp.command;

import java.util.ArrayList;
import java.util.List;

class CashRegister {
    Integer total = 0;

    public void addCash(Integer toAdd) {
        total += toAdd;
    }

    public int getTotal() {
        return total;
    }
}

@FunctionalInterface
interface Command {
    void execute();
}


class CashRegisterExecutor {
    //history
    private final List<Command> purchases = new ArrayList<>();

    public void executePurchase(Command purchase) {
        purchases.add(purchase);
        purchase.execute();
    }
}


public class App {

    //Command 1
    public static Command makePurchase(CashRegister register, Integer amount) {
        return () -> {
            System.out.println("Purchase in amount: " + amount);
            register.addCash(amount);
        };
    }

    //Command 2
    public static Command makeBonusPurchase(CashRegister register, Integer amount, Float bonus) {
        return () -> {
            System.out.println("Purchase in amount: " + amount + " with bonus: " + bonus);
            register.addCash((int) (amount + amount * bonus));
        };
    }

    public static void main(String[] args) {
        final CashRegisterExecutor executor = new CashRegisterExecutor();
        final CashRegister cashRegister = new CashRegister();

        //Command 1
        executor.executePurchase(makePurchase(cashRegister, 5));

        //Command 2
        executor.executePurchase(makeBonusPurchase(cashRegister, 2, 0.5f));

        //Command 3
        Command maxPurchase = () -> {
            final Integer MAX = 500;
            System.out.println("Purchase in MAX amount " + MAX);
            cashRegister.addCash(MAX);

        };
        executor.executePurchase(maxPurchase);

        System.out.println(cashRegister.getTotal());
    }
}



