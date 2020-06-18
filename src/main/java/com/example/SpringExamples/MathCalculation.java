package com.example.SpringExamples;

import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

@Service
public class MathCalculation {
    private static final String className = MathCalculation.class.getName();

    public MathCalculation () {
        //Metrics.counter(MathCalculation.class.getName()).increment(1.0);
    }

    @Timed(value="com.example.MavenExamples.MathCalculation")
    public long factorialUsingForLoop(int n) {
        //return Metrics.timer(MathCalculation.class.getName()).record(() -> {
            long fact = 1;
            for (int i = 2; i <= n; i++) {
                fact = fact * i;
            }
            return fact;
        //});
    }
    public long factorialUsingRecursion(int n) {
        if (n <= 2) {
            return n;
        }
        return n * factorialUsingRecursion(n - 1);
    }
}
