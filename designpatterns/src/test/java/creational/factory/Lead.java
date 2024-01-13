package creational.factory;

import beans.LeadDTO;

public interface Lead {
	
	
	void mapLeadDTOToLead(LeadDTO dto);
	
	void SaveLead();

}
