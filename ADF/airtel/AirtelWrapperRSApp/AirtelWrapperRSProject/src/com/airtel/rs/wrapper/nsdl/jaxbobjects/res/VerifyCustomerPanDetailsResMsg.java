
package com.airtel.rs.wrapper.nsdl.jaxbobjects.res;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="ebmHeader" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="lob" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="consumerTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="consumerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="programmeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="dataArea" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="verifyCustomerPanDetailsResponse" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="status" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                                       &lt;element name="statusDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="panDetails" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="panNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                                       &lt;element name="panStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                                       &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                                       &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                                       &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                                       &lt;element name="panTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                                       &lt;element name="lastUpdatedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
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
@XmlRootElement(name = "verifyCustomerPanDetailsResMsg")
public class VerifyCustomerPanDetailsResMsg {

    protected VerifyCustomerPanDetailsResMsg.EbmHeader ebmHeader;
    protected VerifyCustomerPanDetailsResMsg.DataArea dataArea;

    /**
     * Gets the value of the ebmHeader property.
     *
     * @return
     *     possible object is
     *     {@link VerifyCustomerPanDetailsResMsg.EbmHeader }
     *
     */
    public VerifyCustomerPanDetailsResMsg.EbmHeader getEbmHeader() {
        return ebmHeader;
    }

    /**
     * Sets the value of the ebmHeader property.
     *
     * @param value
     *     allowed object is
     *     {@link VerifyCustomerPanDetailsResMsg.EbmHeader }
     *
     */
    public void setEbmHeader(VerifyCustomerPanDetailsResMsg.EbmHeader value) {
        this.ebmHeader = value;
    }

    /**
     * Gets the value of the dataArea property.
     *
     * @return
     *     possible object is
     *     {@link VerifyCustomerPanDetailsResMsg.DataArea }
     *
     */
    public VerifyCustomerPanDetailsResMsg.DataArea getDataArea() {
        return dataArea;
    }

    /**
     * Sets the value of the dataArea property.
     *
     * @param value
     *     allowed object is
     *     {@link VerifyCustomerPanDetailsResMsg.DataArea }
     *
     */
    public void setDataArea(VerifyCustomerPanDetailsResMsg.DataArea value) {
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
     *         &lt;element name="verifyCustomerPanDetailsResponse" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="status" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                             &lt;element name="statusDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="panDetails" maxOccurs="unbounded" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="panNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                             &lt;element name="panStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                             &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                             &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                             &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                             &lt;element name="panTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                             &lt;element name="lastUpdatedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    @XmlType(name = "", propOrder = { "verifyCustomerPanDetailsResponse" })
    public static class DataArea {

        protected VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse verifyCustomerPanDetailsResponse;

        /**
         * Gets the value of the verifyCustomerPanDetailsResponse property.
         *
         * @return
         *     possible object is
         *     {@link VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse }
         *
         */
        public VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse getVerifyCustomerPanDetailsResponse() {
            return verifyCustomerPanDetailsResponse;
        }

        /**
         * Sets the value of the verifyCustomerPanDetailsResponse property.
         *
         * @param value
         *     allowed object is
         *     {@link VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse }
         *
         */
        public void setVerifyCustomerPanDetailsResponse(VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse value) {
            this.verifyCustomerPanDetailsResponse = value;
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
         *         &lt;element name="status" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *                   &lt;element name="statusDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="panDetails" maxOccurs="unbounded" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="panNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *                   &lt;element name="panStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *                   &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *                   &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *                   &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *                   &lt;element name="panTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *                   &lt;element name="lastUpdatedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        @XmlType(name = "", propOrder = { "status", "panDetails" })
        public static class VerifyCustomerPanDetailsResponse {

            protected VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse.Status status;
            protected List<VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse.PanDetails> panDetails;

            /**
             * Gets the value of the status property.
             *
             * @return
             *     possible object is
             *     {@link VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse.Status }
             *
             */
            public VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse.Status getStatus() {
                return status;
            }

            /**
             * Sets the value of the status property.
             *
             * @param value
             *     allowed object is
             *     {@link VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse.Status }
             *
             */
            public void setStatus(VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse.Status value) {
                this.status = value;
            }

            /**
             * Gets the value of the panDetails property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the panDetails property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getPanDetails().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse.PanDetails }
             *
             *
             */
            public List<VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse.PanDetails> getPanDetails() {
                if (panDetails == null) {
                    panDetails =
                        new ArrayList<VerifyCustomerPanDetailsResMsg.DataArea.VerifyCustomerPanDetailsResponse.PanDetails>();
                }
                return this.panDetails;
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
             *         &lt;element name="panNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
             *         &lt;element name="panStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
             *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
             *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
             *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
             *         &lt;element name="panTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
             *         &lt;element name="lastUpdatedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "",
                     propOrder =
                     { "panNumber", "panStatus", "lastName", "firstName", "middleName", "panTitle", "lastUpdatedDate"
                })
            public static class PanDetails {

                protected String panNumber;
                protected String panStatus;
                protected String lastName;
                protected String firstName;
                protected String middleName;
                protected String panTitle;
                protected String lastUpdatedDate;

                /**
                 * Gets the value of the panNumber property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getPanNumber() {
                    return panNumber;
                }

                /**
                 * Sets the value of the panNumber property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setPanNumber(String value) {
                    this.panNumber = value;
                }

                /**
                 * Gets the value of the panStatus property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getPanStatus() {
                    return panStatus;
                }

                /**
                 * Sets the value of the panStatus property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setPanStatus(String value) {
                    this.panStatus = value;
                }

                /**
                 * Gets the value of the lastName property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getLastName() {
                    return lastName;
                }

                /**
                 * Sets the value of the lastName property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setLastName(String value) {
                    this.lastName = value;
                }

                /**
                 * Gets the value of the firstName property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getFirstName() {
                    return firstName;
                }

                /**
                 * Sets the value of the firstName property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setFirstName(String value) {
                    this.firstName = value;
                }

                /**
                 * Gets the value of the middleName property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getMiddleName() {
                    return middleName;
                }

                /**
                 * Sets the value of the middleName property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setMiddleName(String value) {
                    this.middleName = value;
                }

                /**
                 * Gets the value of the panTitle property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getPanTitle() {
                    return panTitle;
                }

                /**
                 * Sets the value of the panTitle property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setPanTitle(String value) {
                    this.panTitle = value;
                }

                /**
                 * Gets the value of the lastUpdatedDate property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getLastUpdatedDate() {
                    return lastUpdatedDate;
                }

                /**
                 * Sets the value of the lastUpdatedDate property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setLastUpdatedDate(String value) {
                    this.lastUpdatedDate = value;
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
             *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
             *         &lt;element name="statusDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = { "statusCode", "statusDescription" })
            public static class Status {

                protected String statusCode;
                protected String statusDescription;


                /**
                 * Gets the value of the status property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getStatusCode() {
                    return statusCode;
                }


                /**
                 * Sets the value of the status property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setStatusCode(String statusCode) {
                    this.statusCode = statusCode;
                }

                /**
                 * Gets the value of the statusDescription property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getStatusDescription() {
                    return statusDescription;
                }

                /**
                 * Sets the value of the statusDescription property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setStatusDescription(String value) {
                    this.statusDescription = value;
                }

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
     *         &lt;element name="lob" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="consumerTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="consumerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="programmeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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

        protected String lob;
        protected String consumerTransactionId;
        protected String consumerName;
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
