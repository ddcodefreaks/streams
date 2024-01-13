package creational.factory;

import beans.LeadDTO;

public class Residential implements Lead{

    @Override
    public void mapLeadDTOToLead(LeadDTO dto) {
        System.out.println("LeadDto transformed to Residential LEAD");
        
    }

    @Override
    public void SaveLead() {
        System.out.println("Residential Lead Saved");
        
    }

}
