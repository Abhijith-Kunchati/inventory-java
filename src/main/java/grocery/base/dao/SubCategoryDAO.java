package grocery.base.dao;

import java.sql.*;
import java.util.ArrayList;

import grocery.base.pojo.SubCategory;
import grocery.base.pojo.Item;

public class SubCategoryDAO {
    private final Connection con;
    public SubCategoryDAO(Connection con){
        this.con = con;
    }
    public void insert(SubCategory subCat){
        try {
            String sql = "INSERT INTO sub_categories(subCatName, categoryId, unit) VALUES(?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,subCat.getSubCatName());
            stmt.setInt(2,subCat.getCategoryId());
            stmt.setString(3, subCat.getUnit());
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("SubCategory inserted");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void update(SubCategory subCat){
        try {
            String sql = "UPDATE sub_categories SET subCatName = ?, categoryId = ?, unit = ? WHERE subCatId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,subCat.getSubCatName());
            stmt.setInt(2,subCat.getCategoryId());
            stmt.setString(3, subCat.getUnit());
            stmt.setInt(4,subCat.getSubCatId());
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("SubCategory updated");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<SubCategory> listSubCategories(){
        ArrayList<SubCategory> results = new ArrayList<SubCategory>();
        try {
            String sql = "SELECT subCatId, subCatName, categoryId, unit FROM sub_categories";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet subCategories = stmt.executeQuery();
            while (subCategories.next()){
                SubCategory subCategory = new SubCategory();
                subCategory.setSubCatId(subCategories.getInt("subCatId"));
                subCategory.setSubCatName(subCategories.getString("subCatName"));
                subCategory.setCategoryId(subCategories.getInt("categoryId"));
                subCategory.setUnit(subCategories.getString("unit"));
                results.add(subCategory);
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
    public SubCategory getSubCatOfId(int SubCatId){
        SubCategory subCategory = new SubCategory();
        try {
            String sql = "SELECT subCatId, subCatName, categoryId, unit FROM sub_categories WHERE subCatId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, SubCatId);
            ResultSet subCategories = stmt.executeQuery();
            while (subCategories.next()){
                subCategory.setSubCatId(subCategories.getInt("subCatId"));
                subCategory.setSubCatName(subCategories.getString("subCatName"));
                subCategory.setCategoryId(subCategories.getInt("categoryId"));
                subCategory.setUnit(subCategories.getString("unit"));
            }
        }
        catch (SQLException e){
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return subCategory;
    }
    public ArrayList<Item> searchBySubCatId(int SubCatId){
        ArrayList<Item> results = new ArrayList<Item>();
        try {
            String sql = "SELECT items.itemId, items.itemName , items.subCatId, items.brandId, items.quantity, items.price FROM items, sub_categories WHERE items.subCatId = sub_categories.subCatId AND sub_categories.subCatId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, SubCatId);
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
    public ArrayList<Item> searchBySubCatName(String SubCatName){
        ArrayList<Item> results = new ArrayList<Item>();
        try {
            String sql = "SELECT items.itemId, items.itemName , items.subCatId, items.brandId, items.quantity, items.price FROM items, sub_categories WHERE items.subCatId = sub_categories.subCatId AND sub_categories.subCatName = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, SubCatName);
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
    public void deleteBySubCatId(int SubCatId){
        try {
            String sql = "DELETE FROM sub_categories WHERE subCatId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, SubCatId);
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Sub Category deleted");
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
