package grocery.base.pojo;

public class Item {
    int itemID;
    String itemName;
    int categoryID;
    int brandID;
    int availQuantity;
    int price;

    public Item(){
    };

    public Item(int itemID, String itemName){
        this.itemID = itemID;
        this.itemName = itemName;
    };

    public Item(int itemID, String itemName, int categoryID, int brandID, int availQuantity, int price){
        this.itemID = itemID;
        this.itemName = itemName;
        this.categoryID = categoryID;
        this.brandID = brandID;
        this.availQuantity = availQuantity;
        this.price = price;
    }

    public int getItemID(){
        return itemID;
    }

    public void setItemID(int itemID){
        this.itemID = itemID;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public int getCategoryID(){
        return categoryID;
    }

    public void setCategoryID(int categoryID){
        this.categoryID = categoryID;
    }

    public int getBrandID(){
        return brandID;
    }

    public void setBrandID(int brandID){
        this.brandID = brandID;
    }

    public int getAvailQuantity(){
        return availQuantity;
    }

    public void setAvailQuantity(int availQuantity){
        this.availQuantity = availQuantity;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }
}
