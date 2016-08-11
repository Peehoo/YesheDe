package yeshede.domain;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Book {
	private int id;
	private int predecessorId;
	private int successorId;
	private int numCopiesPrinted;
	private int pressPlateLocationId;
	private Date productionYear;
	private String title;
	private String notes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPredecessorId() {
		return predecessorId;
	}
	public void setPredecessorId(int predecessorId) {
		this.predecessorId = predecessorId;
	}
	public int getSuccessorId() {
		return successorId;
	}
	public void setSuccessorId(int successorId) {
		this.successorId = successorId;
	}
	public int getNumCopiesPrinted() {
		return numCopiesPrinted;
	}
	public void setNumCopiesPrinted(int numCopiesPrinted) {
		this.numCopiesPrinted = numCopiesPrinted;
	}
	public int getPressPlateLocationId() {
		return pressPlateLocationId;
	}
	public void setPressPlateLocationId(int pressPlateLocationId) {
		this.pressPlateLocationId = pressPlateLocationId;
	}
	public Date getProductionYear() {
		return productionYear;
	}
	public void setProductionYear(Date productionYear) {
		this.productionYear = productionYear;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getProductionYearAsString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(this.productionYear);
	}
}

