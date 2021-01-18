package com.provigil.util;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="result")
public class SurveillanceCostDetailsPayload {
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName="subscription")
	public List<SubscriptionPayload> subscriptionPayloads;

}
