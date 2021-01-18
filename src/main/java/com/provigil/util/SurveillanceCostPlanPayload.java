package com.provigil.util;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="subscriptions")
public class SurveillanceCostPlanPayload {
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName="subscription")
	 public List<SubscriptionPlanPayload> subscriptionPlanPayloads;
}
