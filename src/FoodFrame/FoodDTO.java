package FoodFrame;

public class FoodDTO {
  private int foodnum;
  private String foodname;
  private int amount;
  private int price;
  private String menu;
public int getFoodnum() {
	return foodnum;
}
public void setFoodnum(int foodnum) {
	this.foodnum = foodnum;
}
public String getFoodname() {
	return foodname;
}
public void setFoodname(String foodname) {
	this.foodname = foodname;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getMenu() {
	return menu;
}
public void setMenu(String menu) {
	this.menu = menu;
}
public FoodDTO(int foodnum, String foodname, int amount, int price) {
	super();
	this.foodnum = foodnum;
	this.foodname = foodname;
	this.amount = amount;
	this.price = price;
}
@Override
public String toString() {
	return "FoodDTO [foodnum=" + foodnum + ", foodname=" + foodname + ", amount=" + amount + ", price=" + price
			+ ", menu=" + menu + "]";
}
  
  
  public FoodDTO() {
	// TODO Auto-generated constructor stub
}
  
  
}
