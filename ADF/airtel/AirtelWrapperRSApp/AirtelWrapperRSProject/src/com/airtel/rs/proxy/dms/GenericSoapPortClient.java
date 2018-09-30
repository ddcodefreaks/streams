package com.airtel.rs.proxy.dms;

import weblogic.wsee.jws.jaxws.owsm.SecurityPoliciesFeature;
// This source file is generated by Oracle tools.
// Contents may be subject to change.
// For reporting problems, use the following:
// Generated by Oracle JDeveloper 12c 12.2.1.3.0.0914
public class GenericSoapPortClient {
    public static void main(String[] args) {
        GenericSoapService genericSoapService = new GenericSoapService();

        // Configure security feature
        SecurityPoliciesFeature securityFeatures =
            new SecurityPoliciesFeature(new String[] { "oracle/wss11_username_token_with_message_protection_client_policy" });
        GenericSoapPortType genericSoapPortType = genericSoapService.getGenericSoapPort(securityFeatures);
        // Add your code to call the desired methods.


    }
}
