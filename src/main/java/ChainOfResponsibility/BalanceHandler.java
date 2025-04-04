package ChainOfResponsibility;

import vao.ChargingStation;
import vao.User;

public class BalanceHandler extends ChargingHandler{
    private static final double MinimumBalance = 100;
    @Override
    public boolean handle(User user, ChargingStation station) {
        if (user.getAccountBalance() < MinimumBalance) {
            System.out.println("Insufficient balance: " + MinimumBalance);
            return false;
        }
        return nextHandler != null? nextHandler.handle(user, station) : true;

    }
}
