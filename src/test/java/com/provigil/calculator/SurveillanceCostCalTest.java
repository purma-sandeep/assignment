package com.provigil.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.provigil.model.SurveillanceCostPlan;
import com.provigil.model.SurveillanceCostPlan.AreaType;
import com.provigil.model.SurveillanceCostPlan.PlanType;

/**
 * Unit test for SurveillanceCostCal App.
 */
public class SurveillanceCostCalTest {

	@Test
	public void shouldReturnCostAccordingToChosenPlan() {
		SurveillanceCostPlan surveillanceCostPlan = new SurveillanceCostPlan();
		assertEquals(5000.0, surveillanceCostPlan.getCost(2500, AreaType.INDOOR, PlanType.MONTHLY), "monthly cost for 2500 Sq.ft indoor area must be 5000.0");
        assertEquals(3750.0, surveillanceCostPlan.getCost(2500, AreaType.INDOOR, PlanType.YEARLY), "yearly cost for 2500 Sq.ft indoor area must be 3750.0");
        assertEquals(8500.0, surveillanceCostPlan.getCost(4000, AreaType.OUTDOOR, PlanType.MONTHLY), "yearly cost for 4000 Sq.ft outdoor area must be 8500.0");
        assertEquals(6500.0, surveillanceCostPlan.getCost(4000, AreaType.OUTDOOR, PlanType.YEARLY), "yearly cost for 4000 Sq.ft outdoor area must be 6500.0");
        assertEquals(28750.0, surveillanceCostPlan.getCost(25000, AreaType.INDOOR, PlanType.MONTHLY), "yearly cost for 25000 Sq.ft indoor area must be 28750.0");
        assertEquals(18250.0, surveillanceCostPlan.getCost(25000, AreaType.INDOOR, PlanType.YEARLY), "yearly cost for 25000 Sq.ft indoor area must be 18250.0");
        assertEquals(65750.0, surveillanceCostPlan.getCost(65000, AreaType.INDOOR, PlanType.MONTHLY), "yearly cost for 65000 Sq.ft indoor area must be 65750.0");
        assertEquals(46000.0, surveillanceCostPlan.getCost(55000, AreaType.OUTDOOR, PlanType.YEARLY), "yearly cost for 55000 Sq.ft outdoor area must be 46000.0");
	}
}
