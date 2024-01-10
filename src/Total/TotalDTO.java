package Total;

import java.sql.Date;

public class TotalDTO {
	private int num;
	private String tname;
	private int price;
	private Date tday;
	@Override
	public String toString() {
		return "TotalDTO [num=" + num + ", tname=" + tname + ", price=" + price + ", tday=" + tday + "]";
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date gettday() {
		return tday;
	}
	public void settday(Date tday) {
		this.tday = tday;
	}
	public TotalDTO(int num, String tname, int price, Date tday) {
		super();
		this.num = num;
		this.tname = tname;
		this.price = price;
		this.tday = tday;
	}
	public TotalDTO() {
		// TODO Auto-generated constructor stub
	}
}
