package creational.factory;

import beans.LeadDTO;

public class Commercial implements Lead{

	@Override
	public void mapLeadDTOToLead(LeadDTO dto ) {
		System.out.println("LeadDto transformed to Commercial LEAD");
		
	}

	@Override
	public void SaveLead() {
		System.out.println("Commercial Lead Saved");
		
	}

}
