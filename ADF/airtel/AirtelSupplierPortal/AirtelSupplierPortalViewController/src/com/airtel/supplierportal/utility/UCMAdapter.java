package com.airtel.supplierportal.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import oracle.adf.share.logging.ADFLogger;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.model.serialize.HdaBinderSerializer;
import oracle.stellent.ridc.protocol.ServiceResponse;

public class UCMAdapter {
    
    private static final ADFLogger _logger = ADFLogger.createADFLogger(UCMAdapter.class);
    public Properties ucmProp = new Properties();
    InputStream input = null;

    IdcClientManager manager;
    IdcClient idcClient;
    IdcContext userContext;
    HdaBinderSerializer serializer;
    DataBinder dataBinder;
    
    
    
    /**
     * defualt constructor.
     */
    public UCMAdapter() {
        
        super();
        _logger.config("Insede default constructor of UCMAdapter class");
        try {
            // Load Properties file.
//            input = new FileInputStream("./ucm.properties");
//            ucmProp.load(input);
            // Establish UCM RIDC Connection and create an Object of DataBinder.
            manager = new IdcClientManager();
//            idcClient = manager.createClient(ucmProp.getProperty("UCM_URL"));UCM_URL
            idcClient = manager.createClient(CommonConstants.UCM_URL);
            userContext = new IdcContext(CommonConstants.UCM_USERNAME, CommonConstants.UCM_PASSWORD);
            serializer = new HdaBinderSerializer("UTF-8", idcClient.getDataFactory());
            dataBinder = idcClient.createBinder();
        } catch (IdcClientException e) {
            _logger.config("Exception raised : "+e.getMessage());
            e.printStackTrace();
            
        }
        
        _logger.config("Exit constructor");
    }

    /*public static void main(String[] args) {
        
        UCMAdapter uCMAdapter = new UCMAdapter();
        
        ServiceResponse response = null;
        DataBinder responseData = null;
        UCMAdapter ucmAdapter = new UCMAdapter();
        
        String GET_FILE_URL = "";
        String WEB_FILE_URL = "";
        String dDocName = "";
        String dID = "";
        String title = "test title";
        File primaryFile = new File("C:\\Users\\a1fyu1t5\\Desktop\\download.jpg");

        try {
            
    //             Enable below code to test CheckIn-Standard Profile : Start CheckIn Code
    //            response = ucmAdapter.checkInFile_StandardProfile(primaryFile, dDocName, dID, title);
    //            responseData = response.getResponseAsBinder();
    //            _logger.info("("ContentID is " + responseData.getLocal("dDocName"));
    //            _logger.info("("dID is " + responseData.getLocal("dID"));
    //            : End CheckIn Code

    //             Enable below code to test CheckOut a file : Start CheckOut Code
    //            response = ucmAdapter.checkOutFile("171815", "WCCDEV_172890");
    //            responseData = response.getResponseAsBinder();
    //            _logger.info("("File checked out successfully");
    //            : End CheckOut Code

    //             Enable below code to test file delete in UCM: Start Delete Code
    //            response = ucmAdapter.deleteFile("171816", "WCCDEV_172890");
    //            responseData = response.getResponseAsBinder();
    //            _logger.info("("File deleted successfully");
    //            : End Delete Code

    //             Enable below code to test file download from UCM: Start GET File Code
    //            File file = new File("C:\\Users\\a1fyu1t5\\Desktop\\SIT_File.jpg");
    //            ucmAdapter.getFileFromUCM("171816", "WCCDEV_172890", file);
    //            : End GET File code
            
    //             Enable below code to test Search File in UCM: Start Search File Code
            response = ucmAdapter.searchFile("WCCDEV_172901");
            responseData = response.getResponseAsBinder();
            DataResultSet resultSet = responseData.getResultSet("SearchResults");
            for (DataObject dataObject : resultSet.getRows()) {
                _logger.info("("WebURL: " + dataObject.get("dOriginalName"));
                _logger.info("("Title is: " + dataObject.get("dDocTitle"));
                _logger.info("("Author is: " + dataObject.get("dDocAuthor"));
                _logger.info("("ContentID is " + dataObject.get("dDocName"));
                _logger.info("("dID is " + dataObject.get("dID"));
                _logger.info("("dInDate is " + dataObject.get("dInDate"));
                
                GET_FILE_URL=ucmProp.getProperty("UCM_URL")+"?IdcService=GET_FILE&dID="+dataObject.get("dID")+"&dDocName="+dataObject.get("dDocName")+"&AllowInterrupt=1";
                _logger.info("("Natie File URL: " + GET_FILE_URL);
                WEB_FILE_URL=ucmProp.getProperty("UCM_URL")+"?IdcService=GET_FILE&dID="+dataObject.get("dID")+"&dDocName="+dataObject.get("dDocName")+"&Rendition=web&allowInterrupt=1&noSaveAs=1&dOriginalName="+dataObject.get("dOriginalName");
                _logger.info("("WEB FILE URL: " + WEB_FILE_URL);
            }
    //            : End Search File Code
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /**
     * method to check-in document in UCM
     * @param primaryFile
     * @param contentID
     * @param documentID
     * @param title
     * @return
     */
    public ServiceResponse checkInFile_StandardProfile(File primaryFile, String contentID, String documentID, String title) {
        _logger.config("Entering method checkINFile_StandardProfile :: primaryFile ::"+primaryFile +" :: contentId :: "+contentID+" :: documentId :: "+documentID + " :: title :: "+ title );
        String dDocName = "";
        String dID = "";
        ServiceResponse response = null;
        try {
            dDocName = contentID;
            dID = documentID;
            dataBinder.putLocal("IdcService", "CHECKIN_UNIVERSAL");
            dataBinder.putLocal("dDocTitle", title); //CheckIn via Standard Profile
            dataBinder.putLocal("dDocType", "Document");
            dataBinder.putLocal("dDocAccount", "");
            dataBinder.putLocal("dSecurityGroup", CommonConstants.UCM_PRIVATE_SECURITY_GROUP);
            dataBinder.putLocal("dDocName", dDocName);
            dataBinder.putLocal("dID", dID);
            dataBinder.addFile("primaryFile", primaryFile);
            serializer.serializeBinder(System.out, dataBinder);
            response = idcClient.sendRequest(userContext, dataBinder);
        } catch (IOException e) {
            _logger.severe("Exception raised : "+e.getMessage());
            e.printStackTrace();
        } catch (IdcClientException e) {
            _logger.severe("Exception raised : "+e.getMessage());
            e.printStackTrace();
        }
        _logger.config("Exit method checkInFile_StandardProfile");
        return response;
    }

    public ServiceResponse checkOutFile(String dID, String contentID) {
        ServiceResponse response = null;
        try {
            dataBinder.putLocal("IdcService", "CHECKOUT");
            dataBinder.putLocal("dID", dID + "");
            dataBinder.putLocal("dDocName", contentID);
            response = idcClient.sendRequest(userContext, dataBinder);
        } catch (IdcClientException e) {
            e.printStackTrace();
        }
        return response;
    }

    public ServiceResponse deleteFile(String dID, String contentID) {
        ServiceResponse response = null;
        try {
            dataBinder.putLocal("IdcService", "DELETE_DOC");
            dataBinder.putLocal("dID", dID + "");
            dataBinder.putLocal("dDocName", contentID);
            response = idcClient.sendRequest(userContext, dataBinder);
        } catch (IdcClientException e) {
            e.printStackTrace();
        }
        return response;
    }

    public byte[] getFile(String dID, String contentID) {

        InputStream fstream = null;
        ByteArrayOutputStream bos = null;
        byte[] result = null;
        byte[] buf = new byte[1024];

        ServiceResponse response = null;
        try {
            dataBinder.putLocal("IdcService", "GET_FILE");
            dataBinder.putLocal("dID", dID + "");
            dataBinder.putLocal("dDocName", contentID);
            response = idcClient.sendRequest(userContext, dataBinder);
            fstream = response.getResponseStream();
            int thisLine = 0;
            bos = new ByteArrayOutputStream();
            for (int readNum; (readNum = fstream.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            result = bos.toByteArray();
        } catch (IdcClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fstream != null)
                try {
                    fstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null) {
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public void getFileFromUCM(String dID, String contentID, File file) {
        byte[] bytes = getFile(dID, contentID);
        if (bytes.length > 0) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public ServiceResponse searchFile(String contentID) {
        String queryText = "dDocName <matches> `" + contentID + "`";
        _logger.info("queryText: " + queryText);
        
        ServiceResponse response = null;
        try {
            HdaBinderSerializer serializer = new HdaBinderSerializer("UTF-8", idcClient.getDataFactory());
            dataBinder.putLocal("IdcService", "GET_SEARCH_RESULTS");
            dataBinder.putLocal("QueryText", queryText);
    //            Enable if you want to go for FullText search. Also, enable FullText search in UCM before using this feature.
    //            dataBinder.putLocal("SearchEngineName", "databasefulltext");
            dataBinder.putLocal("ResultCount", "20");
            serializer.serializeBinder(System.out, dataBinder);
            response = idcClient.sendRequest(userContext, dataBinder);
        } catch (IdcClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
