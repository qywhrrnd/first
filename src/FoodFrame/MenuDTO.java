package FoodFrame;

public class MenuDTO {
	private int foodnum;
	private String menu;
	
	
	public int getFoodnum() {
		return foodnum;
	}
	public void setFoodnum(int foodnum) {
		this.foodnum = foodnum;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	@Override
	public String toString() {
		return "MenuDTO [foodnum=" + foodnum + ", menu=" + menu + "]";
	}
	public MenuDTO(int foodnum, String menu) {

		this.foodnum = foodnum;
		this.menu = menu;
	}
	
	public MenuDTO() {
		// TODO Auto-generated constructor stub
	}
}
