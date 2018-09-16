package com.airtel.ucm;

import java.io.IOException;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.model.serialize.HdaBinderSerializer;
import oracle.stellent.ridc.protocol.ServiceResponse;

public class TestRIDCSearch {
    public static void main(String[] args) {
        IdcClientManager manager = new IdcClientManager();
        try {
            IdcClient idcClient = manager.createClient("http://10.13.80.155:16200/_dav/cs/idcplg");
            IdcContext userContext = new IdcContext("weblogic");
            HdaBinderSerializer serializer = new HdaBinderSerializer("UTF-8", idcClient.getDataFactory());

            DataBinder dataBinder = idcClient.createBinder();
            dataBinder.putLocal("IdcService", "GET_SEARCH_RESULTS");
            
            dataBinder.putLocal("QueryText", "dDocName `N1VLTDVRD06162000208`");
            serializer.serializeBinder(System.out, dataBinder);
            ServiceResponse response = idcClient.sendRequest(userContext, dataBinder);

            DataBinder responseData = response.getResponseAsBinder();
            serializer.serializeBinder(System.out, responseData);
            DataResultSet resultSet = responseData.getResultSet("SearchResults");

            for (DataObject dataObject : resultSet.getRows()) {
                System.out.println("Title is: " + dataObject.get("dDocTitle"));
            }
        } catch (IdcClientException ice) {
            ice.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
