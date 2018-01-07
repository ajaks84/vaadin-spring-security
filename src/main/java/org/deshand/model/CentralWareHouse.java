package org.deshand.model;

import org.springframework.data.annotation.Id;

public class CentralWareHouse {
	
	@Id
	private String id;
	
	private String shelfName;
	
	private String valueMetal;
	
	private String partDescription;
	
	private String partNumber;
	
	private String wHNumber;
	
	private String quantity;
	
	private String bKQuantity;
	
	private String missingQuantity;
	
	private String placeOfInstallation;

	public CentralWareHouse() {
	}

	public CentralWareHouse(String shelfName, String hasValueMetal, String partDescription, String partNumber,
			String wHNumber, String quantity, String bKQuantity, String missingQuantity,
			String placeOfInstallation) {
		this.shelfName = shelfName;
		this.valueMetal = hasValueMetal;
		this.partDescription = partDescription;
		this.partNumber = partNumber;
		this.wHNumber = wHNumber;
		this.quantity = quantity;
		this.bKQuantity = bKQuantity;
		this.missingQuantity = missingQuantity;
		this.placeOfInstallation = placeOfInstallation;
	}

	

	@Override
	public String toString() {
		return "CentralWareHouse [shelfName=" + shelfName + ", valueMetal=" + valueMetal + ", partDescription="
				+ partDescription + ", partNumber=" + partNumber + ", wHNumber=" + wHNumber + ", quantity=" + quantity
				+ ", bKQuantity=" + bKQuantity + ", missingQuantity=" + missingQuantity + ", placeOfInstallation="
				+ placeOfInstallation + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}



	public String getHasValueMetal() {
		return valueMetal;
	}

	public void setHasValueMetal(String hasValueMetal) {
		this.valueMetal = hasValueMetal;
	}

	public String getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getwHNumber() {
		return wHNumber;
	}

	public void setwHNumber(String wHNumber) {
		this.wHNumber = wHNumber;
	}

	

	public String getValueMetal() {
		return valueMetal;
	}

	public void setValueMetal(String valueMetal) {
		this.valueMetal = valueMetal;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getbKQuantity() {
		return bKQuantity;
	}

	public void setbKQuantity(String bKQuantity) {
		this.bKQuantity = bKQuantity;
	}

	public String getMissingQuantity() {
		return missingQuantity;
	}

	public void setMissingQuantity(String missingQuantity) {
		this.missingQuantity = missingQuantity;
	}

	public String getPlaceOfInstallation() {
		return placeOfInstallation;
	}

	public void setPlaceOfInstallation(String placeOfInstallation) {
		this.placeOfInstallation = placeOfInstallation;
	}
	
	


	
	
	
	
	





	
	




}
