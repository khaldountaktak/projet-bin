package app.bean;

import app.api.CalculatorRemote;
import jakarta.ejb.Stateless;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.AsyncResult;
import java.util.concurrent.Future;

@Stateless(name = "CalculatorBean")
public class CalculatorBean implements CalculatorRemote {

    // ---- Sync ----
    public double add(double a, double b)       { return a + b; }
    public double subtract(double a, double b)  { return a - b; }
    public double multiply(double a, double b)  { return a * b; }
    public double divide(double a, double b) {
        if (b == 0.0) throw new ArithmeticException("Division by zero");
        return a / b;
    }

    // ---- Async ----
    @Asynchronous public Future<Double> addAsync(double a, double b)      { return new AsyncResult<>(add(a,b)); }
    @Asynchronous public Future<Double> subtractAsync(double a, double b) { return new AsyncResult<>(subtract(a,b)); }
    @Asynchronous public Future<Double> multiplyAsync(double a, double b) { return new AsyncResult<>(multiply(a,b)); }
    @Asynchronous public Future<Double> divideAsync(double a, double b)   { return new AsyncResult<>(divide(a,b)); }
}
