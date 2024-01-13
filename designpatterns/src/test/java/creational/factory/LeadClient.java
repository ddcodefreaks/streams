package creational.factory;

import beans.LeadDTO;

public class LeadClient {

	public Lead createLead(LeadDTO dto) {
		Lead lead = LeadFactory.createLead(dto);
		return lead;
	}
	
}
