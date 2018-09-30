package com.airtel.buyer.utility;

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

import oracle.adf.share.logging.ADFLogger;

import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.protocol.ServiceResponse;

/**
 * The Class DmsUtil.
 */
public class DmsUtil {

    /** The Constant _logger. */
    private static final ADFLogger _logger = ADFLogger.createADFLogger(DmsUtil.class);

    /**
     * Instantiates a new dms util.
     */
    public DmsUtil() {
        super();
    }

    /**
     * Upload fileto UCM.
     *
     * @param primaryFile the primary file
     * @param contentID the content ID
     * @param documentID the document ID
     * @param title the title
     * @return the map
     */
    public static Map<String, String> uploadFiletoUCM(File primaryFile, String contentID, String documentID,
                                                      String title) {
        _logger.config("Inside DmsUtil ::: uploadFiltetoUCM ::: @param primaryFile, @param contentId, @param documentId, @param title");
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
            _logger.config("ContentID is " + responseData.getLocal("dDocName"));
            docMeta.put("dDocName", responseData.getLocal("dDocName"));

            _logger.config("dID is " + responseData.getLocal("dID"));
            docMeta.put("dID", responseData.getLocal("dID"));

            _logger.config("fileName is " + responseData.getLocal("dOriginalName"));
            docMeta.put("dOriginalName", responseData.getLocal("dOriginalName"));

            GET_FILE_URL =
                CommonConstants.UCM_URL + "?IdcService=GET_FILE&dID=" + responseData.getLocal("dID") + "&dDocName=" +
                responseData.getLocal("dDocName") + "&AllowInterrupt=1";
            _logger.config("Natie File URL: " + GET_FILE_URL);
            docMeta.put("nativeURL", GET_FILE_URL);

            WEB_FILE_URL =
                CommonConstants.UCM_URL + "?IdcService=GET_FILE&dID=" + responseData.getLocal("dID") + "&dDocName=" +
                responseData.getLocal("dDocName") + "&Rendition=web&allowInterrupt=1&noSaveAs=1&dOriginalName=" +
                responseData.getLocal("dOriginalName");
            _logger.config("WEB FILE URL: " + WEB_FILE_URL);
            docMeta.put("webURL", WEB_FILE_URL);

        } catch (IdcClientException e) {
            _logger.severe("Exception raised ::: "+e.getMessage(),e);
            e.printStackTrace();
        }catch (Exception e) {
            _logger.severe("Exception raised ::: "+e.getMessage(),e);
            e.printStackTrace();
        }
        _logger.config("Exiting from DmsUtil ::: uploadFiltetoUCM ::: @param primaryFile, @param contentId, @param documentId, @param title");
        return docMeta;
    }

    /**
     * Search doc in UCM.
     *
     * @param contentID the content ID
     * @return the map
     */
    public static Map<String, String> searchDocInUCM(String contentID) {
        _logger.config("Inside DmsUtil ::: searchDocInUCM ::: @parma string contentId");
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
                _logger.config("WebURL: " + dataObject.get("dOriginalName"));
                docMeta.put("dOriginalName", dataObject.get("dOriginalName"));

                _logger.config("Title is: " + dataObject.get("dDocTitle"));
                docMeta.put("dDocTitle", dataObject.get("dDocTitle"));

                _logger.config("Author is: " + dataObject.get("dDocAuthor"));
                docMeta.put("dDocAuthor", dataObject.get("dDocAuthor"));

                _logger.config("ContentID is " + dataObject.get("dDocName"));
                docMeta.put("dDocName", dataObject.get("dDocName"));

                _logger.config("dID is " + dataObject.get("dID"));
                docMeta.put("dID", dataObject.get("dID"));

                _logger.config("dInDate is " + dataObject.get("dInDate"));
                docMeta.put("dInDate", dataObject.get("dInDate"));

                GET_FILE_URL =
                    CommonConstants.UCM_URL + "?IdcService=GET_FILE&dID=" + dataObject.get("dID") + "&dDocName=" +
                    dataObject.get("dDocName") + "&AllowInterrupt=1";
                _logger.config("Natie File URL: " + GET_FILE_URL);
                docMeta.put("nativeURL", GET_FILE_URL);

                WEB_FILE_URL =
                    CommonConstants.UCM_URL + "?IdcService=GET_FILE&dID=" + dataObject.get("dID") + "&dDocName=" +
                    dataObject.get("dDocName") + "&Rendition=web&allowInterrupt=1&noSaveAs=1&dOriginalName=" +
                    dataObject.get("dOriginalName");
                _logger.config("WEB FILE URL: " + WEB_FILE_URL);
                docMeta.put("webURL", WEB_FILE_URL);

                _logger.config("format is " + dataObject.get("dFormat"));
                docMeta.put("dFormat", dataObject.get("dFormat"));
            }

        } catch (IdcClientException ice) {
            ice.printStackTrace();
        }
        _logger.config("Exiting from DmsUtil ::: searchDocInUcm ::: @param string contientId and @return map object docMeta ");
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
     * Store tmp file.
     *
     * @param inputStream the input stream
     * @param fileName the file name
     * @return the file
     */
    public static File storeTmpFile(InputStream inputStream, String fileName) {
        _logger.config("Inside DmsUtil ::: storeTmpFile ::: @param InputStream inputString, @param String fileName and @return File file");
        File file = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            Date date = new Date();
            _logger.config(dateFormat.format(date)); //2016-11-16-12-08-43
            String tmpDirName = dateFormat.format(date).concat("/");
            OutputStream outputStream = null;
            File directory = new File(CommonConstants.UPLOAD_TMP_DIRECTORY + tmpDirName);
            if (!directory.exists()) {
                directory.mkdir();
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }
            _logger.config("File Path::::" + CommonConstants.UPLOAD_TMP_DIRECTORY + tmpDirName + fileName);

            file = new File(CommonConstants.UPLOAD_TMP_DIRECTORY + tmpDirName + fileName);
            outputStream = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            _logger.config("Done!");
        } catch (FileNotFoundException fnfe) {
            _logger.severe("Exception raised :::" + fnfe.getMessage(), fnfe);
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            _logger.severe("Exception raised :::" + ioe.getMessage(), ioe);
            ioe.printStackTrace();
        } catch (Exception e) {
            _logger.severe("Exception raised :::" + e.getMessage(), e);
            e.printStackTrace();
        }
        finally {
            try {
                _logger.config("Finally block start");
                inputStream.close();
                _logger.config("Finally block end");
            } catch (IOException io) {
                _logger.severe("Exception raised ::: "+io.getMessage(),io);
                io.printStackTrace();
            }
        }
        _logger.config("Exiting from DmsUtil ::: storeTmpFile ::: @param InputStream inputString, @param String fileName and @return File file");
        return file;
    }

    /**
     * Probe and clean.
     *
     * @param file the file
     * @return the boolean
     */
    public static Boolean probeAndClean(File file) {
        _logger.config("Inside DmsUtil :::  probeAndClean ::: @param File file , @return Boolean");
        try {
            String filePath = file.getAbsolutePath();
            _logger.config(filePath);

            String fileType = identifyFileTypeUsingFilesProbeContentType(filePath);
            if (fileType.equalsIgnoreCase("image/png") || fileType.equalsIgnoreCase("image/jpg") ||
                fileType.equalsIgnoreCase("image/jpeg") || fileType.equalsIgnoreCase("application/pdf")) {

                return CommonConstants.BOOLEAN_TRUE;
            } else {
                _logger.config("Deleting file ....");
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                _logger.config("done");
            }
        } catch (Exception e) {
            _logger.severe("Exception raised ::: "+e.getMessage(),e);
            e.printStackTrace();
        }
        _logger.config("Exiting from DmsUtil :::  probeAndClean ::: @param File file , @return Boolean");
        return CommonConstants.BOOLEAN_FALSE;
    }

    /**
     * Identify file type using files probe content type.
     *
     * @param fileName the file name
     * @return the string
     */
    public static String identifyFileTypeUsingFilesProbeContentType(final String fileName) {
        _logger.config("Inside DmsUtil ::: identifyFileTypeUsingFilesProbeContentType ::: @param final String fileName, @return String");
        String fileType = "Undetermined";
        final File file = new File(fileName);
        try {
            fileType = Files.probeContentType(file.toPath());
        } catch (IOException ioException) {
            _logger.config("ERROR: Unable to determine file type for " + fileName + " due to exception " +
                               ioException);
        }
        _logger.config("Exiting from DmsUtil ::: identifyFileTypeUsingFilesProbeContentType ::: @param final String fileName, @return String");
        return fileType;
    }
}
