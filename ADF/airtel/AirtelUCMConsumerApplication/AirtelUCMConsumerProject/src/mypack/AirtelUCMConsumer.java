package mypack;

import com.airtel.ucm.UCMAdapter;

import java.io.File;

import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.protocol.ServiceResponse;

public class AirtelUCMConsumer {
    public AirtelUCMConsumer() {
        super();
    }

    public static void main(String[] args) {
        
        AirtelUCMConsumer airtelUCMConsumer = new AirtelUCMConsumer();
        UCMAdapter ucmAdapter = new UCMAdapter();
        
        ServiceResponse response = null;
        DataBinder responseData = null;
        UCMAdapter uCMAdapter = new UCMAdapter();
        
        String GET_FILE_URL = "";
        String dDocName = "";
        String dID = "";
        File primaryFile = new File("C:\\Users\\a1fyu1t5\\Desktop\\download.jpg");
        
        response = uCMAdapter.checkInFile_StandardProfile(primaryFile, dDocName, dID);
        try {
            responseData = response.getResponseAsBinder();
        } catch (IdcClientException e) {
            e.printStackTrace();
        }
        System.out.println("ContentID is " + responseData.getLocal("dDocName"));
        System.out.println("dID is " + responseData.getLocal("dID"));
    }
}
