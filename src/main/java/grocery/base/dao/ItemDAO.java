package grocery.base.dao;

import java.sql.*;
import java.util.ArrayList;

import grocery.base.pojo.Item;

public class ItemDAO{
    private Connection con;
    public ItemDAO(Connection con){
        this.con = con;
    }
    public void insert(Item item){
        try {
            String sql = "INSERT INTO items VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, item.getItemID());
            stmt.setString(2, item.getItemName());
            stmt.setInt(3, item.getCategoryID());
            stmt.setInt(4, item.getBrandID());
            stmt.setInt(5, item.getAvailQuantity());
            stmt.setInt(6, item.getPrice());
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Item " + item.getItemName() +" is inserted");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void update(Item item) {
        try {
            String sql = "UPDATE items " +
                    "SET " + "itemName = ?, subCatId = ?, brandId = ?, quantity = ?, price = ? WHERE itemId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, item.getItemName());
            stmt.setInt(2, item.getCategoryID());
            stmt.setInt(3, item.getBrandID());
            stmt.setInt(4, item.getAvailQuantity());
            stmt.setInt(5, item.getPrice());
            stmt.setInt(6, item.getItemID());
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Item of Id:"+ item.getItemID() + " is updated");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Item> listItems(){
        ArrayList<Item> results = new ArrayList<Item>();
        try{
            String sql = "SELECT itemId, itemName , subCatId, brandId, quantity, price FROM items";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet items = stmt.executeQuery();
            while(items.next()){
                Item item = new Item();
                item.setItemID(items.getInt("itemId"));
                item.setItemName(items.getString("itemName"));
                item.setCategoryID(items.getInt("subCatId"));
                item.setBrandID(items.getInt("brandId"));
                item.setAvailQuantity(items.getInt("quantity"));
                item.setPrice(items.getInt("price"));
                results.add(item);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList<Item> searchById(int itemID){
        ArrayList<Item> results = new ArrayList<Item>();
        try{
            String sql = "SELECT itemId, itemName , subCatId, brandId, quantity, price FROM items WHERE itemId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, itemID);
            ResultSet items = stmt.executeQuery();
            while(items.next()){
                Item item = new Item();
                item.setItemID(items.getInt("itemId"));
                item.setItemName(items.getString("itemName"));
                item.setCategoryID(items.getInt("subCatId"));
                item.setBrandID(items.getInt("brandId"));
                item.setAvailQuantity(items.getInt("quantity"));
                item.setPrice(items.getInt("price"));
                results.add(item);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList<Item> searchByItemName(String itemName){
        ArrayList<Item> results = new ArrayList<Item>();
        try{
            String sql = "SELECT itemId, itemName , subCatId, brandId, quantity, price FROM items WHERE itemName = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, itemName);
            ResultSet items = stmt.executeQuery();
            while(items.next()){
                Item item = new Item();
                item.setItemID(items.getInt("itemId"));
                item.setItemName(items.getString("itemName"));
                item.setCategoryID(items.getInt("subCatId"));
                item.setBrandID(items.getInt("brandId"));
                item.setAvailQuantity(items.getInt("quantity"));
                item.setPrice(items.getInt("price"));
                results.add(item);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    public void deleteById(int itemID){
        try {
            String sql = "DELETE FROM ITEMS WHERE itemId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, itemID);
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Item deleted");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
