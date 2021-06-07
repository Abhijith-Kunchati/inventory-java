package grocery.base.dao;

import java.sql.*;
import java.util.ArrayList;

import grocery.base.pojo.Item;

public class ItemDAO{
    private final Connection con;
    public ItemDAO(Connection con){
        this.con = con;
    }
    public void insert(Item item){
        try {
            String sql = "INSERT INTO items(itemName , subCatId, brandId, quantity, price) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, item.getItemName());
            stmt.setInt(2, item.getCategoryID());
            stmt.setInt(3, item.getBrandID());
            stmt.setInt(4, item.getAvailQuantity());
            stmt.setInt(5, item.getPrice());
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Item " + item.getItemName() +" is inserted");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e){
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
        catch (SQLException e){
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e){
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
        catch (SQLException e){
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    public Item getItemOfId(int itemID){
        Item item = new Item();
        try{
            String sql = "SELECT itemId, itemName , subCatId, brandId, quantity, price FROM items WHERE itemId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, itemID);
            ResultSet items = stmt.executeQuery();
            while(items.next()){
                item.setItemID(items.getInt("itemId"));
                item.setItemName(items.getString("itemName"));
                item.setCategoryID(items.getInt("subCatId"));
                item.setBrandID(items.getInt("brandId"));
                item.setAvailQuantity(items.getInt("quantity"));
                item.setPrice(items.getInt("price"));
            }
        }
        catch (SQLException e){
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return item;
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
        catch (SQLException e){
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e){
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
        catch (SQLException e){
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
