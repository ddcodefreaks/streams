package com.airtel.rs.wrapper.dms;

import com.airtel.rs.wrapper.dms.pojo.DMSReq;
import com.airtel.rs.wrapper.dms.pojo.DMSResp;

//import com.oracle.ucm.Generic;


public class DMSWrapper {
    public DMSWrapper() {
        super();
    }
    
    public DMSResp searchDoc(DMSReq dmsReq){
        
//        //add code to call DMS Proxy service to search document
//        Generic request = new Generic();
//        request.setWebKey("cs");
//        Service service = new Service();
//        request.setService(service);
//        service.setIdcService("DOC_INFO");
//        Service.Document document = new Service.Document();
//        service.setDocument(document);
//         
//        List<Field> fields = document.getField();
//        Field field = new Field();
//        field.setName("dID");
//        field.setValue("1");
//        fields.add(field);
//         
//        // Make the request
//        Generic response = genericSoapPortType.genericSoapOperation(request);
//        Service serviceResponse = response.getService();
//        
//        //code to search document ends
//        
        
        DMSResp dmsResp = new DMSResp();
        return dmsResp;
    }
}
