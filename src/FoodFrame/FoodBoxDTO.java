package FoodFrame;

public class FoodBoxDTO {
   private int snum;
private String foodname;
   private int price;
   private int seatno;
   
   /**
    * @return the snum
    */
   public int getSnum() {
	   return snum;
   }
   /**
    * @param snum the snum to set
    */
   public void setSnum(int snum) {
	   this.snum = snum;
   }
   
   public int getSeatno() {
      return seatno;
   }
   public void setSeatno(int seatno) {
      this.seatno = seatno;
   }
   public String getFoodname() {
      return foodname;
   }
   public void setFoodname(String foodname) {
      this.foodname = foodname;
   }
   public int getPrice() {
      return price;
   }
   public void setPrice(int price) {
      this.price = price;
   }
   
   @Override
   public String toString() {
      return "FoodBoxDTO [foodname=" + foodname + ", price=" + price + ", seatno=" + seatno + "]";
   }
   
   
public FoodBoxDTO(String foodname, int price, int seatno) {
      super();
      this.foodname = foodname;
      this.price = price;
      this.seatno = seatno;
   }
public FoodBoxDTO() {
   // TODO Auto-generated constructor stub
}   

}