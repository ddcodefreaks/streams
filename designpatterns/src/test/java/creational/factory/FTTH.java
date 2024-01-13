package creational.factory;

import beans.LeadDTO;

public class FTTH implements Lead{

    @Override
    public void mapLeadDTOToLead(LeadDTO dto) {
        System.out.println("LeadDto transformed to FTTH LEAD");
        
    }

    @Override
    public void SaveLead() {
        System.out.println("FTTH Lead Saved");
        
    }

}
