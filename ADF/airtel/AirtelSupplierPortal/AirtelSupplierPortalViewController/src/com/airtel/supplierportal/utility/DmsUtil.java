package com.airtel.supplierportal.utility;

import com.airtel.supplierportal.bean.forgotpassword.ForgotPassword;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.file.Files;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import oracle.adf.share.logging.ADFLogger;

import oracle.dss.dataView.Title;

import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.protocol.ServiceResponse;

public class DmsUtil {
    
    private static final ADFLogger _logger = ADFLogger.createADFLogger(DmsUtil.class);

    /**
     * default constructor.
     */
    public DmsUtil() {
        super();
    }

    /**
     * method to upload file in usm.
     * @param primaryFile
     * @param contentID
     * @param documentID
     * @param title
     * @return
     */
    public static Map<String, String> uploadFiletoUCM(File primaryFile, String contentID, String documentID,
                                                      String title) {
        _logger.config("Entering method uploadFiletoUSM :: primaryFile :: "+primaryFile + " :: contentId :: "+contentID +" :: documentID :: "+documentID);
        ServiceResponse response = null;
        DataBinder responseData = null;
        UCMAdapter ucmAdapter = new UCMAdapter();
//        Properties ucmProp = ucmAdapter.ucmProp;

        String dDocName = contentID != null ? contentID : "";
        String dID = documentID != null ? documentID : "";
        String GET_FILE_URL = "";
        String WEB_FILE_URL = "";

        Map<String, String> docMeta = new HashMap<>();
        try {
            response = ucmAdapter.checkInFile_StandardProfile(primaryFile, dDocName, dID, title);
            responseData = response.getResponseAsBinder();
            _logger.info("ContentID is " + responseData.getLocal("dDocName"));
            docMeta.put("dDocName", responseData.getLocal("dDocName"));

            _logger.info("dID is " + responseData.getLocal("dID"));
            docMeta.put("dID", responseData.getLocal("dID"));

            _logger.info("fileName is " + responseData.getLocal("dOriginalName"));
            docMeta.put("dOriginalName", responseData.getLocal("dOriginalName"));

            GET_FILE_URL =
                CommonConstants.UCM_URL + "?IdcService=GET_FILE&dID=" + responseData.getLocal("dID") + "&dDocName=" +
                responseData.getLocal("dDocName") + "&AllowInterrupt=1";
            _logger.info("Natie File URL: " + GET_FILE_URL);
            docMeta.put("nativeURL", GET_FILE_URL);

            WEB_FILE_URL =
                CommonConstants.UCM_URL + "?IdcService=GET_FILE&dID=" + responseData.getLocal("dID") + "&dDocName=" +
                responseData.getLocal("dDocName") + "&Rendition=web&allowInterrupt=1&noSaveAs=1&dOriginalName=" +
                responseData.getLocal("dOriginalName");
            _logger.info("WEB FILE URL: " + WEB_FILE_URL);
            docMeta.put("webURL", WEB_FILE_URL);

        } catch (IdcClientException e) {
            _logger.severe("Exception raised:::"+e.getMessage());
            e.printStackTrace();
        }
        _logger.config("Exit method uploadFileToUCM");
        return docMeta;
    }
    
    
    
    /**
     * methos to search document in UCM.
     * @param contentID
     * @return
     */
    public static Map<String, String> searchDocInUCM(String contentID) {
        _logger.config("Entering method searchDocInUCM :: contentID :: "+contentID);
        ServiceResponse response = null;
        DataBinder responseData = null;
        UCMAdapter ucmAdapter = new UCMAdapter();
//        Properties ucmProp = ucmAdapter.ucmProp;

        String GET_FILE_URL = "";
        String WEB_FILE_URL = "";
        String dDocName = contentID != null ? contentID : "";

        Map<String, String> docMeta = new HashMap<>();

        try {
            response = ucmAdapter.searchFile(dDocName);
            responseData = response.getResponseAsBinder();
            DataResultSet resultSet = responseData.getResultSet("SearchResults");
            for (DataObject dataObject : resultSet.getRows()) {
                _logger.info("WebURL: " + dataObject.get("dOriginalName"));
                docMeta.put("dOriginalName", dataObject.get("dOriginalName"));

                _logger.info("Title is: " + dataObject.get("dDocTitle"));
                docMeta.put("dDocTitle", dataObject.get("dDocTitle"));

                _logger.info("Author is: " + dataObject.get("dDocAuthor"));
                docMeta.put("dDocAuthor", dataObject.get("dDocAuthor"));

                _logger.info("ContentID is " + dataObject.get("dDocName"));
                docMeta.put("dDocName", dataObject.get("dDocName"));

                _logger.info("dID is " + dataObject.get("dID"));
                docMeta.put("dID", dataObject.get("dID"));

                _logger.info("dInDate is " + dataObject.get("dInDate"));
                docMeta.put("dInDate", dataObject.get("dInDate"));

                GET_FILE_URL =
                    CommonConstants.UCM_URL + "?IdcService=GET_FILE&dID=" + dataObject.get("dID") +
                    "&dDocName=" + dataObject.get("dDocName") + "&AllowInterrupt=1";
                _logger.info("Natie File URL: " + GET_FILE_URL);
                docMeta.put("nativeURL", GET_FILE_URL);

                WEB_FILE_URL =
                    CommonConstants.UCM_URL + "?IdcService=GET_FILE&dID=" + dataObject.get("dID") +
                    "&dDocName=" + dataObject.get("dDocName") +
                    "&Rendition=web&allowInterrupt=1&noSaveAs=1&dOriginalName=" + dataObject.get("dOriginalName");
                _logger.info("WEB FILE URL: " + WEB_FILE_URL);
                docMeta.put("webURL", WEB_FILE_URL);
                
                _logger.info("format is " + dataObject.get("dFormat"));
                docMeta.put("dFormat", dataObject.get("dFormat"));
            }

        } catch (IdcClientException ice) {
            _logger.severe("Exception raised:::"+ice.getMessage());
            ice.printStackTrace();
        }
        _logger.config("Exit method searchDocInUCM");
        return docMeta;
    }
    
    
    
    
    
    /**
     * helper method used to fetch file bytes from UCM
     * @param dID
     * @param contentID
     * @return
     */
    public static byte[] getFileFromUCM(String dID, String contentID) {
        _logger.config("Entering method getFileFromUCM");
        _logger.config(dID);
        _logger.config(contentID);
        ServiceResponse response = null;
        DataBinder responseData = null;
        byte[] bytes = null;
        UCMAdapter ucmAdapter = new UCMAdapter();
        try {
           bytes = ucmAdapter.getFile(dID, contentID);
       } catch (Exception e) {
            _logger.severe("Exception raised while getting file from UCM :::"+e.getMessage());
            e.printStackTrace();
        }
        _logger.config("Exit method getFileFromUCM");
        return bytes;
    }


    /**
     * method to store the file in tem location.
     * @param inputStream
     * @param fileName
     * @return
     */
    public static File storeTmpFile(InputStream inputStream, String fileName) {
        _logger.config("Entering method storeTmpFile :: inputStream :: "+inputStream + " :: fileName :: "+fileName);
        File file = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            Date date = new Date();
            _logger.info(dateFormat.format(date)); //2016-11-16-12-08-43
            String tmpDirName = dateFormat.format(date).concat("/");
            OutputStream outputStream = null;
            File directory = new File(CommonConstants.UPLOAD_TMP_DIRECTORY + tmpDirName);
                if (! directory.exists()){
                    directory.mkdir();
                    // If you require it to make the entire directory path including parents,
                    // use directory.mkdirs(); here instead.
                }
            _logger.info("File Path::::" + CommonConstants.UPLOAD_TMP_DIRECTORY + tmpDirName + fileName);
            
            file = new File(CommonConstants.UPLOAD_TMP_DIRECTORY + tmpDirName + fileName);
            outputStream = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            _logger.info("Done!");
        } catch (FileNotFoundException fnfe) {
            _logger.severe("Exception raised:::"+fnfe.getMessage());
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            _logger.severe("Exception raised:::"+ioe.getMessage());
            ioe.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException io) {
                _logger.severe("Exception raised:::"+io.getMessage());
                io.printStackTrace();
            }
        }
        
        _logger.config("Exit method storeTmpFile");
        return file;
    }
  
    /**
     * method for chekc for maliciuos files.
     * @param file
     * @return
     */
    public static Boolean probeAndClean(File file){
        _logger.config("Inside probeAndClean method");
        String filePath = file.getAbsolutePath();
        _logger.info(filePath);
        
        String fileType = identifyFileTypeUsingFilesProbeContentType(filePath);
        if (fileType.equalsIgnoreCase("image/png") ||
            fileType.equalsIgnoreCase("image/jpg") || 
            fileType.equalsIgnoreCase("image/jpeg") ||
            fileType.equalsIgnoreCase("application/pdf") ) {
            
            return CommonConstants.BOOLEAN_TRUE;
        } else{
            _logger.info("Deleting file ....");
            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                _logger.severe("Exception raised:::"+e.getMessage());
                e.printStackTrace();
            }
            System.out.print("done");
        }
        _logger.config("Exit method probeAndClean");
        return CommonConstants.BOOLEAN_FALSE;
    }

    /**
     * mthod to identify the type of file.
     * @param fileName
     * @return
     */
    public static String identifyFileTypeUsingFilesProbeContentType(final String fileName) {
        _logger.config("Entering method indentifyFileTypeUsingFileProbeContentType :: fileName :: "+ fileName);
       String fileType = "Undetermined";
       final File file = new File(fileName);
       try
       {
          fileType = Files.probeContentType(file.toPath());
       }
       catch (IOException ioException)
       {
          _logger.info(
               "ERROR: Unable to determine file type for " + fileName
                  + " due to exception " + ioException);
       }
       _logger.config("Entering method indentifyFileTypeUsingProbeAndContentType");
       return fileType;
    }
}
