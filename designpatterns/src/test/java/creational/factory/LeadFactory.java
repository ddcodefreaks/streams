package creational.factory;

import beans.LeadDTO;

public class LeadFactory {
	
	public static Lead createLead(LeadDTO dto) {
		
		Lead lead = null;
		
		if (dto.getLeadType().equals("COMMERCIAL")) {
			lead = new Commercial();
		} else if (dto.getLeadType().equals("RESIDENTIAL")) {
			lead = new Residential();
		} else if (dto.getLeadType().equals("FTTH")) {
			lead = new FTTH();
		}

		lead.mapLeadDTOToLead(dto);
		lead.SaveLead();
		return lead;
	}

}
