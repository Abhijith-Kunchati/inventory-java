package grocery.base.dao;

import java.sql.*;
import java.util.ArrayList;

import grocery.base.pojo.ParentBrand;
import grocery.base.pojo.Item;

public class ParentBrandDAO {
    private final Connection con;
    public ParentBrandDAO(Connection con) {
        this.con = con;
    }
    public void insert(ParentBrand parentBrand) {
        try {
            String sql = "INSERT INTO parent_brands(parentName) VALUES(?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, parentBrand.getParentName());
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Parent Brand inserted");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(ParentBrand parentBrand) {
        try {
            String sql = "UPDATE parent_brands SET parentName = ? WHERE parentId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, parentBrand.getParentName());
            stmt.setInt(2, parentBrand.getParentId());
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Brand updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList < ParentBrand > listParentBrands() {
        ArrayList < ParentBrand > results = new ArrayList < ParentBrand > ();
        try {
            String sql = "SELECT parentId, parentName FROM parent_brands";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet parentBrands = stmt.executeQuery();
            while (parentBrands.next()) {
                ParentBrand parentBrand = new ParentBrand();
                parentBrand.setParentId(parentBrands.getInt("parentId"));
                parentBrand.setParentName(parentBrands.getString("parentName"));
                results.add(parentBrand);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public ParentBrand getParentOfId(int ParentId) {
        ParentBrand parentBrand = new ParentBrand();
        try {
            String sql = "SELECT parentId, parentName FROM parent_brands WHERE parentId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ParentId);
            ResultSet parentBrands = stmt.executeQuery();
            while (parentBrands.next()) {
                parentBrand.setParentId(parentBrands.getInt("parentId"));
                parentBrand.setParentName(parentBrands.getString("parentName"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parentBrand;
    }
    public ArrayList < Item > searchByParentId(int ParentId) {
        ArrayList < Item > results = new ArrayList < Item > ();
        try {
            String sql = "SELECT items.itemId, items.itemName , items.subCatId, items.brandId, items.quantity, items.price FROM items, brands, parent_brands WHERE items.brandId = brands.brandId AND brands.parentId = parent_brands.parentId AND parent_brands.parentId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ParentId);
            ResultSet items = stmt.executeQuery();
            while (items.next()) {
                Item item = new Item();
                item.setItemID(items.getInt("itemId"));
                item.setItemName(items.getString("itemName"));
                item.setCategoryID(items.getInt("subCatId"));
                item.setBrandID(items.getInt("brandId"));
                item.setAvailQuantity(items.getInt("quantity"));
                item.setPrice(items.getInt("price"));
                results.add(item);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public ArrayList < Item > searchByParentName(String ParentName) {
        ArrayList < Item > results = new ArrayList < Item > ();
        try {
            String sql = "SELECT items.itemId, items.itemName , items.subCatId, items.brandId, items.quantity, items.price FROM items, brands, parent_brands WHERE items.brandId = brands.brandId AND brands.parentId = parent_brands.parentId AND parent_brands.parentName = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, ParentName);
            ResultSet items = stmt.executeQuery();
            while (items.next()) {
                Item item = new Item();
                item.setItemID(items.getInt("itemId"));
                item.setItemName(items.getString("itemName"));
                item.setCategoryID(items.getInt("subCatId"));
                item.setBrandID(items.getInt("brandId"));
                item.setAvailQuantity(items.getInt("quantity"));
                item.setPrice(items.getInt("price"));
                results.add(item);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public void deleteByParentId(int parentID) {
        try {
            String sql = "DELETE FROM parent_brands WHERE parentId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, parentID);
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Parent brand deleted");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}