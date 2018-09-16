
package com.airtel.rs.wrapper.nsdl.jaxbobjects.req;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ebmHeader"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="lob" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="consumerTransactionId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="consumerName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="programmeName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="dataArea"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="verifyCustomerPanDetailsRequest"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="panNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "ebmHeader", "dataArea" })
@XmlRootElement(name = "verifyCustomerPanDetailsReqMsg")
public class VerifyCustomerPanDetailsReqMsg {

    @XmlElement(required = true)
    protected VerifyCustomerPanDetailsReqMsg.EbmHeader ebmHeader;
    @XmlElement(required = true)
    protected VerifyCustomerPanDetailsReqMsg.DataArea dataArea;

    /**
     * Gets the value of the ebmHeader property.
     *
     * @return
     *     possible object is
     *     {@link VerifyCustomerPanDetailsReqMsg.EbmHeader }
     *
     */
    public VerifyCustomerPanDetailsReqMsg.EbmHeader getEbmHeader() {
        return ebmHeader;
    }

    /**
     * Sets the value of the ebmHeader property.
     *
     * @param value
     *     allowed object is
     *     {@link VerifyCustomerPanDetailsReqMsg.EbmHeader }
     *
     */
    public void setEbmHeader(VerifyCustomerPanDetailsReqMsg.EbmHeader value) {
        this.ebmHeader = value;
    }

    /**
     * Gets the value of the dataArea property.
     *
     * @return
     *     possible object is
     *     {@link VerifyCustomerPanDetailsReqMsg.DataArea }
     *
     */
    public VerifyCustomerPanDetailsReqMsg.DataArea getDataArea() {
        return dataArea;
    }

    /**
     * Sets the value of the dataArea property.
     *
     * @param value
     *     allowed object is
     *     {@link VerifyCustomerPanDetailsReqMsg.DataArea }
     *
     */
    public void setDataArea(VerifyCustomerPanDetailsReqMsg.DataArea value) {
        this.dataArea = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="verifyCustomerPanDetailsRequest"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="panNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "verifyCustomerPanDetailsRequest" })
    public static class DataArea {

        @XmlElement(required = true)
        protected VerifyCustomerPanDetailsReqMsg.DataArea.VerifyCustomerPanDetailsRequest verifyCustomerPanDetailsRequest;

        /**
         * Gets the value of the verifyCustomerPanDetailsRequest property.
         *
         * @return
         *     possible object is
         *     {@link VerifyCustomerPanDetailsReqMsg.DataArea.VerifyCustomerPanDetailsRequest }
         *
         */
        public VerifyCustomerPanDetailsReqMsg.DataArea.VerifyCustomerPanDetailsRequest getVerifyCustomerPanDetailsRequest() {
            return verifyCustomerPanDetailsRequest;
        }

        /**
         * Sets the value of the verifyCustomerPanDetailsRequest property.
         *
         * @param value
         *     allowed object is
         *     {@link VerifyCustomerPanDetailsReqMsg.DataArea.VerifyCustomerPanDetailsRequest }
         *
         */
        public void setVerifyCustomerPanDetailsRequest(VerifyCustomerPanDetailsReqMsg.DataArea.VerifyCustomerPanDetailsRequest value) {
            this.verifyCustomerPanDetailsRequest = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="panNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "panNumber" })
        public static class VerifyCustomerPanDetailsRequest {

            @XmlElement(required = true)
            protected List<String> panNumber;

            public void setPanNumber(List<String> panNumber) {
                this.panNumber = panNumber;
            }

            /**
             * Gets the value of the panNumber property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the panNumber property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getPanNumber().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             *
             *
             */
            public List<String> getPanNumber() {
                if (panNumber == null) {
                    panNumber = new ArrayList<String>();
                }
                return this.panNumber;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="lob" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="consumerTransactionId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="consumerName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="programmeName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "lob", "consumerTransactionId", "consumerName", "programmeName" })
    public static class EbmHeader {

        @XmlElement(required = true)
        protected String lob;
        @XmlElement(required = true)
        protected String consumerTransactionId;
        @XmlElement(required = true)
        protected String consumerName;
        @XmlElement(required = true)
        protected String programmeName;

        /**
         * Gets the value of the lob property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getLob() {
            return lob;
        }

        /**
         * Sets the value of the lob property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setLob(String value) {
            this.lob = value;
        }

        /**
         * Gets the value of the consumerTransactionId property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getConsumerTransactionId() {
            return consumerTransactionId;
        }

        /**
         * Sets the value of the consumerTransactionId property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setConsumerTransactionId(String value) {
            this.consumerTransactionId = value;
        }

        /**
         * Gets the value of the consumerName property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getConsumerName() {
            return consumerName;
        }

        /**
         * Sets the value of the consumerName property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setConsumerName(String value) {
            this.consumerName = value;
        }

        /**
         * Gets the value of the programmeName property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getProgrammeName() {
            return programmeName;
        }

        /**
         * Sets the value of the programmeName property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setProgrammeName(String value) {
            this.programmeName = value;
        }

    }

}
