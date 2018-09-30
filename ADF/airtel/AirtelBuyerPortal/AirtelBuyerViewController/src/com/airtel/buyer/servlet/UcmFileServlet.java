package com.airtel.buyer.servlet;

import com.airtel.buyer.utility.DmsUtil;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.adf.share.logging.ADFLogger;

public class UcmFileServlet extends HttpServlet {
    @SuppressWarnings("compatibility:-2592925397733655166")
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
    private static final ADFLogger _logger = ADFLogger.createADFLogger(UcmFileServlet.class);


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        _logger.config("Entering Servlet UcmFileServlter ::: method ::: doGet");
        String attributeId = request.getParameter("attributeId");
        _logger.config(attributeId);

        if (attributeId != null && !attributeId.equalsIgnoreCase("")) {
            Map<String, String> panMetaData = DmsUtil.searchDocInUCM(attributeId);
            String webUrl = "";
            String contentType = "";
            String fileName = "DownloadedFile";
            String dId = "";
            for (Map.Entry<String, String> entry : panMetaData.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
                if (entry.getKey() != null && entry.getKey().equals("dID")) {
                    dId = entry.getValue();
                }
                if (entry.getKey() != null && entry.getKey().equals("webURL")) {
                    webUrl = entry.getValue();
                }
                if (entry.getKey() != null && entry.getKey().equals("dFormat")) {
                    contentType = entry.getValue();
                }
                if (entry.getKey() != null && entry.getKey().equals("dOriginalName")) {
                    fileName = entry.getValue();
                }
            }
            System.out.println(webUrl);
            System.out.println(contentType);
            
            OutputStream output = null;
            
            if (!dId.isEmpty() && !webUrl.isEmpty() && !contentType.isEmpty()) {
                response.setContentType(contentType);
//                response.setHeader("Content-disposition", "attachment; filename="+ fileName);
                //fetch document from ucm using http connection and feed it into response output stream
//                URL url = new URL(webUrl);
//                InputStream inputStream = null;
//                OutputStream output = null;
//                inputStream = url.openStream();
//                inputStream = new BufferedInputStream(url.openStream(), DEFAULT_BUFFER_SIZE);
//                output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
//                // Write file contents to response.
//                byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
//                int length;
//                while ((length = inputStream.read(buffer)) > 0) {
//                    output.write(buffer, 0, length);
//                }
//                output.flush();
//                inputStream.close();
                output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
                //get file bytes from ucm and put it in outputstream
                try {
                    byte[] fileBytesObj = DmsUtil.getFileFromUCM(dId, attributeId);
                    output.write(fileBytesObj);
                } catch (IOException ioe) {
                    _logger.severe("Exception raised :::"+ioe.getMessage());
                    ioe.printStackTrace();
                }
                output.flush();

            }
        }
        _logger.info("Exit Servlet UcmFileServlter ::: method ::: doGet");
    }
}
