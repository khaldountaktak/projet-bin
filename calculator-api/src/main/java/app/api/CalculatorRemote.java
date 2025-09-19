package app.api;

import jakarta.ejb.Remote;
import jakarta.ejb.Asynchronous;
import java.util.concurrent.Future;

@Remote
public interface CalculatorRemote {
    // Sync
    double add(double a, double b);
    double subtract(double a, double b);
    double multiply(double a, double b);
    double divide(double a, double b) throws ArithmeticException;

    // Async
    @Asynchronous Future<Double> addAsync(double a, double b);
    @Asynchronous Future<Double> subtractAsync(double a, double b);
    @Asynchronous Future<Double> multiplyAsync(double a, double b);
    @Asynchronous Future<Double> divideAsync(double a, double b);
}
