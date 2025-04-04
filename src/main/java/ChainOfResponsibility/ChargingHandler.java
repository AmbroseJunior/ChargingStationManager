package ChainOfResponsibility;

import vao.ChargingStation;
import vao.User;

public abstract class ChargingHandler {
    protected ChargingHandler nextHandler;

    public void setNextHandler(ChargingHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean handle(User user, ChargingStation station);
}
