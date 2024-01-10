package LoginFrame;

import java.sql.Date;
import java.sql.Time;

import javax.swing.Spring;

public class MemberDTO {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String birth;
    private int time;
    private int seatno;
    private int price;
    private int stackprice;
	/**
	 * @return the userId
	 */
	
	
	public String toString() {
		return "MemberDTO [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", birth=" + birth + ", time=" + time + ", seatno=" + seatno + ", price=" + price
				+ ", stackprice=" + stackprice + "]";
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getSeatno() {
		return seatno;
	}

	public void setSeatno(int seatno) {
		this.seatno = seatno;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStackprice() {
		return stackprice;
	}

	public void setStackprice(int stackprice) {
		this.stackprice = stackprice;
	}

	public MemberDTO(String userId, String password, String name, String email, String phone, String birth) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.birth = birth;
	}
    
    
    public MemberDTO() {
		// TODO Auto-generated constructor stub
	}
	public MemberDTO(int i, String j) {
	this.seatno = i;
	this.userId = j;
	}
    
	public MemberDTO(String j) {
		this.name = j;
		}
	
	public MemberDTO(int i, String j, int l) {
	this.seatno = i;
	this.userId = j;
	this.time = l;
	}
	
}
    
    