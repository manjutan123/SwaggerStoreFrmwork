package storeTestcase;

public class DataLoad {

	private int id;
	private int petId;
	private int quantity;
	private String shipDate;
	private String status;
	private boolean complete;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getShipDate() {
		return shipDate;
	}
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public static DataLoad setdata(int id,int petid,int quantity,String shidte,String status,boolean complete)
	{
		DataLoad l1= new DataLoad();
		l1.setId(id);
		l1.setPetId(petid);
		l1.setQuantity(quantity);
		l1.setShipDate(shidte);
		l1.setStatus(status);
		l1.setComplete(complete);
		return l1;
	
	}
	
	
}
