//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.02 at 08:06:13 PM PKT 
//


package com.softech.ls360.storefront.proxy.webservice.messages.orderdetails;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.w3._2001.xmlschema.Adapter1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


/**
 * <p>Java class for Order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Order">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="storeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="storeNameIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalAdjustment" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalProductPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalSalesTax" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalShippingCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalShippingTax" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="orderCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderItems" type="{http://www.example.org/OrderService}OrderItemsList"/>
 *         &lt;element name="adjustment" type="{http://www.example.org/OrderService}OrderAdjustment"/>
 *         &lt;element name="refundDetail" type="{http://www.example.org/OrderService}RefundDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Order", propOrder = {
    "orderId",
    "orderDate",
    "storeId",
    "orderAmount",
    "storeNameIdentifier",
    "totalAdjustment",
    "totalProductPrice",
    "totalSalesTax",
    "totalShippingCharge",
    "totalShippingTax",
    "orderCurrency",
    "orderItems",
    "adjustment",
    "refundDetail"
})
public class Order
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String orderId;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "dateTime")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime orderDate;
    @XmlElement(required = true)
    protected String storeId;
    @XmlElement(required = true)
    protected BigDecimal orderAmount;
    @XmlElement(required = true)
    protected String storeNameIdentifier;
    @XmlElement(required = true)
    protected BigDecimal totalAdjustment;
    @XmlElement(required = true)
    protected BigDecimal totalProductPrice;
    @XmlElement(required = true)
    protected BigDecimal totalSalesTax;
    @XmlElement(required = true)
    protected BigDecimal totalShippingCharge;
    @XmlElement(required = true)
    protected BigDecimal totalShippingTax;
    @XmlElement(required = true)
    protected String orderCurrency;
    @XmlElement(required = true)
    protected OrderItemsList orderItems;
    @XmlElement(required = true)
    protected OrderAdjustment adjustment;
    protected RefundDetail refundDetail;

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the orderDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the value of the orderDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderDate(LocalDateTime value) {
        this.orderDate = value;
    }

    /**
     * Gets the value of the storeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * Sets the value of the storeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreId(String value) {
        this.storeId = value;
    }

    /**
     * Gets the value of the orderAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * Sets the value of the orderAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOrderAmount(BigDecimal value) {
        this.orderAmount = value;
    }

    /**
     * Gets the value of the storeNameIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreNameIdentifier() {
        return storeNameIdentifier;
    }

    /**
     * Sets the value of the storeNameIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreNameIdentifier(String value) {
        this.storeNameIdentifier = value;
    }

    /**
     * Gets the value of the totalAdjustment property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAdjustment() {
        return totalAdjustment;
    }

    /**
     * Sets the value of the totalAdjustment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAdjustment(BigDecimal value) {
        this.totalAdjustment = value;
    }

    /**
     * Gets the value of the totalProductPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalProductPrice() {
        return totalProductPrice;
    }

    /**
     * Sets the value of the totalProductPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalProductPrice(BigDecimal value) {
        this.totalProductPrice = value;
    }

    /**
     * Gets the value of the totalSalesTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalSalesTax() {
        return totalSalesTax;
    }

    /**
     * Sets the value of the totalSalesTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalSalesTax(BigDecimal value) {
        this.totalSalesTax = value;
    }

    /**
     * Gets the value of the totalShippingCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalShippingCharge() {
        return totalShippingCharge;
    }

    /**
     * Sets the value of the totalShippingCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalShippingCharge(BigDecimal value) {
        this.totalShippingCharge = value;
    }

    /**
     * Gets the value of the totalShippingTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalShippingTax() {
        return totalShippingTax;
    }

    /**
     * Sets the value of the totalShippingTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalShippingTax(BigDecimal value) {
        this.totalShippingTax = value;
    }

    /**
     * Gets the value of the orderCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderCurrency() {
        return orderCurrency;
    }

    /**
     * Sets the value of the orderCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderCurrency(String value) {
        this.orderCurrency = value;
    }

    /**
     * Gets the value of the orderItems property.
     * 
     * @return
     *     possible object is
     *     {@link OrderItemsList }
     *     
     */
    public OrderItemsList getOrderItems() {
        return orderItems;
    }

    /**
     * Sets the value of the orderItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderItemsList }
     *     
     */
    public void setOrderItems(OrderItemsList value) {
        this.orderItems = value;
    }

    /**
     * Gets the value of the adjustment property.
     * 
     * @return
     *     possible object is
     *     {@link OrderAdjustment }
     *     
     */
    public OrderAdjustment getAdjustment() {
        return adjustment;
    }

    /**
     * Sets the value of the adjustment property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderAdjustment }
     *     
     */
    public void setAdjustment(OrderAdjustment value) {
        this.adjustment = value;
    }

    /**
     * Gets the value of the refundDetail property.
     * 
     * @return
     *     possible object is
     *     {@link RefundDetail }
     *     
     */
    public RefundDetail getRefundDetail() {
        return refundDetail;
    }

    /**
     * Sets the value of the refundDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundDetail }
     *     
     */
    public void setRefundDetail(RefundDetail value) {
        this.refundDetail = value;
    }

}
