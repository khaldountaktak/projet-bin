package app.client;

import app.api.CalculatorRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;
import java.util.concurrent.Future;

public final class Client {
    private Client() {}

    public static void main(String[] args) throws Exception {
        Hashtable<String, String> props = new Hashtable<>();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
        props.put(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.put("org.omg.CORBA.ORBInitialHost", "localhost");
        props.put("org.omg.CORBA.ORBInitialPort", "3700");

        InitialContext ic = new InitialContext(props);

        String jndi = "calculator/CalculatorRemote";
        CalculatorRemote calc = (CalculatorRemote) ic.lookup(jndi);
        System.out.println("Lookup OK: " + jndi);

        // ---- Sync ----
        System.out.println("add(2,3) = " + calc.add(2,3));
        System.out.println("subtract(10,4) = " + calc.subtract(10,4));
        System.out.println("multiply(6,7) = " + calc.multiply(6,7));
        System.out.println("divide(22,7) = " + calc.divide(22,7));

        // ---- Async ----
        Future<Double> fAdd = calc.addAsync(100, 23);
        Future<Double> fSub = calc.subtractAsync(50, 8);
        Future<Double> fMul = calc.multiplyAsync(9, 9);
        Future<Double> fDiv = calc.divideAsync(10, 4);

        while (!(fAdd.isDone() && fSub.isDone() && fMul.isDone() && fDiv.isDone())) {
            Thread.sleep(150);
            System.out.print(".");
        }
        System.out.println();

        System.out.println("addAsync(100,23) = " + fAdd.get());
        System.out.println("subtractAsync(50,8) = " + fSub.get());
        System.out.println("multiplyAsync(9,9) = " + fMul.get());
        System.out.println("divideAsync(10,4) = " + fDiv.get());


    }
}
