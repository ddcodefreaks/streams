package com.airtel.ucm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Properties;

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

    public static Properties ucmProp = new Properties();
    InputStream input = null;

    IdcClientManager manager;
    IdcClient idcClient;
    IdcContext userContext;
    HdaBinderSerializer serializer;
    DataBinder dataBinder;

    public UCMAdapter() {
        super();
        try {
            // Load Properties file.
            input = new FileInputStream("./ucm.properties");
            ucmProp.load(input);
            // Establish UCM RIDC Connection and create an Object of DataBinder.
            manager = new IdcClientManager();
            idcClient = manager.createClient(ucmProp.getProperty("UCM_URL"));
            userContext = new IdcContext(ucmProp.getProperty("USERNAME"), ucmProp.getProperty("PASSWORD"));
            serializer = new HdaBinderSerializer("UTF-8", idcClient.getDataFactory());
            dataBinder = idcClient.createBinder();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IdcClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        UCMAdapter uCMAdapter = new UCMAdapter();
        
        ServiceResponse response = null;
        DataBinder responseData = null;
        UCMAdapter ucmAdapter = new UCMAdapter();
        
        String GET_FILE_URL = "";
        String WEB_FILE_URL = "";
        String dDocName = "";
        String dID = "";
        String title = "test title";
        File primaryFile = new File("C:\\Users\\A1L8PDC8\\Desktop\\PAN.jpg");

        try {
            
//             Enable below code to test CheckIn-Standard Profile : Start CheckIn Code
//            response = ucmAdapter.checkInFile_StandardProfile(primaryFile, dDocName, dID, title);
//            responseData = response.getResponseAsBinder();
//            System.out.println("ContentID is " + responseData.getLocal("dDocName"));
//            System.out.println("dID is " + responseData.getLocal("dID"));
//            : End CheckIn Code

//             Enable below code to test CheckOut a file : Start CheckOut Code
//            response = ucmAdapter.checkOutFile("171815", "WCCDEV_172890");
//            responseData = response.getResponseAsBinder();
//            System.out.println("File checked out successfully");
//            : End CheckOut Code

//             Enable below code to test file delete in UCM: Start Delete Code
//            response = ucmAdapter.deleteFile("171816", "WCCDEV_172890");
//            responseData = response.getResponseAsBinder();
//            System.out.println("File deleted successfully");
//            : End Delete Code

//             Enable below code to test file download from UCM: Start GET File Code
//            File file = new File("C:\\Users\\a1fyu1t5\\Desktop\\SIT_File.jpg");
//            ucmAdapter.getFileFromUCM("171816", "WCCDEV_172890", file);
//            : End GET File code
            
//             Enable below code to test Search File in UCM: Start Search File Code
//            response = ucmAdapter.searchFile("N1VLTDVRD06162000402");
//            responseData = response.getResponseAsBinder();
//            DataResultSet resultSet = responseData.getResultSet("SearchResults");
//            for (DataObject dataObject : resultSet.getRows()) {
//                System.out.println("WebURL: " + dataObject.get("dOriginalName"));
//                System.out.println("Title is: " + dataObject.get("dDocTitle"));
//                System.out.println("Author is: " + dataObject.get("dDocAuthor"));
//                System.out.println("ContentID is " + dataObject.get("dDocName"));
//                System.out.println("dID is " + dataObject.get("dID"));
//                System.out.println("dInDate is " + dataObject.get("dInDate"));
//                System.out.println("format is " + dataObject.get("dFormat"));
//                
//                GET_FILE_URL=ucmProp.getProperty("UCM_URL")+"?IdcService=GET_FILE&dID="+dataObject.get("dID")+"&dDocName="+dataObject.get("dDocName")+"&AllowInterrupt=1";
//                System.out.println("Natie File URL: " + GET_FILE_URL);
//                WEB_FILE_URL=ucmProp.getProperty("UCM_URL")+"?IdcService=GET_FILE&dID="+dataObject.get("dID")+"&dDocName="+dataObject.get("dDocName")+"&Rendition=web&allowInterrupt=1&noSaveAs=1&dOriginalName="+dataObject.get("dOriginalName");
//                System.out.println("WEB FILE URL: " + WEB_FILE_URL);
//            }
//            : End Search File Code

//            Enable below code to get rootFolderPathGUID : Start GetFolderIDFromPath Code
            String folderIdFromPath = ucmAdapter.getFolderIdFromPath("/Airtel");
            System.out.println("folderIdFromPath :" + folderIdFromPath);
//            : End GetFolderIDFromPath Code

//            Enable below code to Create New Folder : Start CreateFolder Code
//            ucmAdapter.createFolder("Organization Invoices", "73F65120DC0AFD7278D5A36D1370A01E");
//            : End CreateFolder Code
            
//            Enable below code to create link to Folder : Start CreateFileLinkInFolder Code
//            ucmAdapter.createFileLinkInFolder("53678022118D6ED4E9C7B329CA80E970", "N1VLTDVRD06162000403");//FOlder GUID, Content!D
//            : End CreateFileLinkInFolder Code

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ServiceResponse checkInFile_StandardProfile(File primaryFile, String contentID, String documentID, String title) {
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
            dataBinder.putLocal("dSecurityGroup", "Secure");
            dataBinder.putLocal("dDocName", dDocName);
            dataBinder.putLocal("dID", dID);
            dataBinder.addFile("primaryFile", primaryFile);
            serializer.serializeBinder(System.out, dataBinder);
            response = idcClient.sendRequest(userContext, dataBinder);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IdcClientException e) {
            e.printStackTrace();
        }
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
        System.out.println("queryText: " + queryText);
        
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
    
    public String getFolderIdFromPath(String path){
        String fFolderGUID=null;
        ServiceResponse response = null;
        
        try {
            dataBinder.putLocal("IdcService", "FLD_INFO");
            dataBinder.putLocal("path",path);
            serializer.serializeBinder (System.out, dataBinder);
            response = idcClient.sendRequest(userContext,dataBinder);
            DataBinder responseData = response.getResponseAsBinder();
            String guid=responseData.getLocal("fFolderGUID");
            DataResultSet resultSet = responseData.getResultSet("FolderInfo");
            for (DataObject dataObject : resultSet.getRows()) {
              fFolderGUID = dataObject.get("fFolderGUID").toString();
              System.out.println(fFolderGUID);
            }                     
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());                
        } 
        
        return fFolderGUID;
    }
    
    public void createFolder(String folderName, String fParentGUID){
        String fFolderGUID=null;
        ServiceResponse response = null;
        
        try {
            dataBinder.putLocal("IdcService", "FLD_CREATE_FOLDER");
            dataBinder.putLocal("fParentGUID", fParentGUID);
            dataBinder.putLocal("fFolderName", folderName); 
            dataBinder.putLocal("fSecurityGroup","Secure"); 
            dataBinder.putLocal("fFolderType","owner");
            serializer.serializeBinder (System.out, dataBinder);
            response = idcClient.sendRequest(userContext,dataBinder);
            DataBinder responseData = response.getResponseAsBinder();
            serializer.serializeBinder (System.out, responseData);
            
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());                
        } 
    }
    
    public void createFileLinkInFolder(String folderGUID, String docName) {
        
        ServiceResponse response = null;
        
        try {
            System.out.println("Invoking service FLD_CREATE_FILE (folderGUID=\"" + folderGUID + "\")");
            dataBinder.putLocal("IdcService", "FLD_CREATE_FILE");
            dataBinder.putLocal("fParentGUID", folderGUID);
            dataBinder.putLocal("fFileType", "owner");
            dataBinder.putLocal("dDocName", docName);
            response = idcClient.sendRequest(userContext, dataBinder);
            DataBinder responseData = response.getResponseAsBinder();
            serializer.serializeBinder (System.out, responseData);
        } catch (IdcClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
