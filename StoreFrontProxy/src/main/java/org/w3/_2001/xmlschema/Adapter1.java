//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.09 at 05:29:57 PM PKT 
//

package org.w3._2001.xmlschema;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1 extends XmlAdapter<String, LocalDateTime> {

	public LocalDateTime unmarshal(String value) {
		return (com.softech.ls360.storefront.proxy.webservice.binder.XSDateTimeCustomBinder.parseDateTime(value));
	}

	public String marshal(LocalDateTime value) {
		return (com.softech.ls360.storefront.proxy.webservice.binder.XSDateTimeCustomBinder.printDateTime(value));
	}

}
