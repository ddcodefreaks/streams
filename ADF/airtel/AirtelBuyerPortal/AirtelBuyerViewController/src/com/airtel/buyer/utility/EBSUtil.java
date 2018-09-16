package com.airtel.buyer.utility;

import com.airtel.buyer.pojo.CreateSupplierRR;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import oracle.adf.share.logging.ADFLogger;

/**
 * The Class EBSUtil.
 */
public class EBSUtil {

    /** The Constant _logger. */
    private static final ADFLogger _logger = ADFLogger.createADFLogger(EBSUtil.class);


    /**
     * Call proc create supplier in EBS.
     *
     * @param createSupplierRR the create supplier RR
     * @return the creates the supplier RR
     * @throws SQLException the SQL exception
     */
    public static CreateSupplierRR callProcCreateSupplierInEBS(CreateSupplierRR createSupplierRR) throws SQLException {
        _logger.config("Inside EBSUtil ::: callProcCreateSupplierInEBS  ::: @param CreateSupplierRR, @return CreateSupplierRR");
        Connection dbConnection = null;
        CallableStatement callableStatement = null;

        String getDBUSERByUserIdSql = "{call BTVL_CALL_EBS_VENDER_PRC(?,?,?,?,?)}";

        try {
            dbConnection = getJNDIConnection();
            callableStatement = dbConnection.prepareCall(getDBUSERByUserIdSql);

            callableStatement.setLong(1, createSupplierRR.getPartnerVendorId());
            callableStatement.setString(2, createSupplierRR.getPartnerPassword());
            callableStatement.setString(3, createSupplierRR.getBuyerEmail());
            callableStatement.registerOutParameter(4, java.sql
                                                          .Types
                                                          .VARCHAR);

            callableStatement.registerOutParameter(4, java.sql
                                                          .Types
                                                          .VARCHAR);
            callableStatement.registerOutParameter(5, java.sql
                                                          .Types
                                                          .VARCHAR);

            // execute getDBUSERByUserId store procedure
            callableStatement.executeUpdate();

            String status = callableStatement.getString(4);
            String message = callableStatement.getString(5);
            //            Date createdDate = callableStatement.getDate(4);

            _logger.config("status : " + status);
            _logger.config("message : " + message);
            createSupplierRR.setStatus(status != null ? status : "");
            createSupplierRR.setMessage(message != null ? message : "");
            //            _logger.config("CreatedDate : " + createdDate);

        } catch (SQLException e) {
            _logger.severe("Exception raised ::: "+e.getMessage(),e);
            _logger.config(e.getMessage());

        } catch (Exception e) {
            
            _logger.severe("Exception raised ::: "+e.getMessage(),e);
            _logger.config(e.getMessage());

        }finally {

            if (callableStatement != null) {
                callableStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        _logger.config("Exiting from EBSUtil ::: callProcCreateSupplierInEBS  ::: @param CreateSupplierRR, @return CreateSupplierRR");
        return createSupplierRR;
    }

    /**
     * Uses JNDI and Datasource (preferred style).
     *
     * @return the JNDI connection
     */
    private static Connection getJNDIConnection() {
        _logger.config("Inside EBSUtil ::: getJNDIConnection");
        String DATASOURCE_CONTEXT = CommonConstants.EBS_DATA_SOURCE_JNDI;
        Connection result = null;
        try {
            Context initialContext = new InitialContext();
            if (initialContext == null) {
                _logger.config("JNDI problem. Cannot get InitialContext.");
            }
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                result = datasource.getConnection();
            } else {
                _logger.config("Failed to lookup datasource.");
            }
        } catch (NamingException ne) {
            _logger.severe("Exception raised ::: "+ ne.getMessage(),ne);
        } catch (SQLException sqle) {
            _logger.severe("Exception raised ::: "+ sqle.getMessage(),sqle);
        } catch (Exception e) {
            _logger.severe("Exception raised ::: "+ e.getMessage(),e);
        }
        _logger.config("Exiting from EBSUtil ::: getJNDIConnection");
        return result;
    }
}
