package com.provigil.calculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.provigil.model.SurveillanceCostPlan;
import com.provigil.model.SurveillanceCostPlan.AreaType;
import com.provigil.model.SurveillanceCostPlan.PlanType;
import com.provigil.util.SubscriptionPayload;
import com.provigil.util.SubscriptionPlanPayload;
import com.provigil.util.SurveillanceCostDetailsPayload;
import com.provigil.util.SurveillanceCostPlanPayload;


public class SurveillanceCostCal {
	
	private static String inputStreamToString(InputStream is) throws Exception {
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}

	public static void main(String[] args) {
		
		try {
			XmlMapper xmlMapper = new XmlMapper();
			File inputXmlFile = new File("input.xml");
			String xml = inputStreamToString(new FileInputStream(inputXmlFile));
			SurveillanceCostPlanPayload surveillanceCostPlanPayload = xmlMapper.readValue(xml, SurveillanceCostPlanPayload.class);
			SurveillanceCostPlan surveillanceCostPlan = new SurveillanceCostPlan();
			List<SubscriptionPayload> subscriptionPayloads = new ArrayList<SubscriptionPayload>();
			for (Iterator<SubscriptionPlanPayload> iterator = surveillanceCostPlanPayload.subscriptionPlanPayloads.iterator(); iterator.hasNext();) {
				SubscriptionPlanPayload subscriptionPlanPayload = iterator.next();
				
				int area = subscriptionPlanPayload.area;
				AreaType areaType = AreaType.valueOf(subscriptionPlanPayload.location.toUpperCase());
				PlanType planType = PlanType.valueOf(subscriptionPlanPayload.plan.toUpperCase());
				double planCost = surveillanceCostPlan.getCost(area, areaType, planType);
				SubscriptionPayload subscriptionPayload = new SubscriptionPayload();
				subscriptionPayload.id = subscriptionPlanPayload.id;
				subscriptionPayload.cost = (long)Math.round(planCost);
				subscriptionPayloads.add(subscriptionPayload);
			}
			SurveillanceCostDetailsPayload surveillanceCostDetailsPayload = new SurveillanceCostDetailsPayload();
			surveillanceCostDetailsPayload.subscriptionPayloads = subscriptionPayloads;

			xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
			File file = new File("output.xml");
			xmlMapper.writeValue(file, surveillanceCostDetailsPayload);
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.. Re-writing..");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
