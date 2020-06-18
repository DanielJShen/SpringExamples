package com.example.SpringExamples.RestControllers;

import com.example.SpringExamples.MathCalculation;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculationController {
	private Map<Integer, MathCalculation> calculations = new HashMap<Integer, MathCalculation>();

	public CalculationController() {
		SimpleMeterRegistry oneSimpleMeter = new SimpleMeterRegistry();

		oneSimpleMeter.gaugeMapSize("calculations", null,calculations);
	}

	@GetMapping("/calculation")
	@Timed("com.example.MavenExamples.CalculationController.calculation")
	public Long calculation(@Autowired MathCalculation mathCalculation, @RequestParam(value = "n", defaultValue = "5") Integer n) {
		return mathCalculation.factorialUsingForLoop(n);
	}

	@GetMapping("/api/calculations")
	public Map listCalculations() {
		return calculations;
	}

	@GetMapping("/api/calculation/")
	public MathCalculation findPerson(@PathVariable Integer id) {
		return calculations.get(id);
	}
}
