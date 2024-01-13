package creational.factory;

import beans.LeadDTO;

public class Test {

	public static void main(String[] args) {

		LeadClient leadClient = new LeadClient();
		LeadDTO dto = new LeadDTO();
		dto.setId(1);
		dto.setLeadType("COMMERCIAL");
		dto.setName("XYZ");
		dto.setAge(29);
		
		leadClient.createLead(dto);
	}

}
