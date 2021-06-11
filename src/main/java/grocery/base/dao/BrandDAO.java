package grocery.base.dao;

import java.sql.*;
import java.util.ArrayList;

import grocery.base.pojo.Brand;
import grocery.base.pojo.Item;

public class BrandDAO {
    private final Connection con;
    public BrandDAO(Connection con) {
        this.con = con;
    }
    public void insert(Brand brand) {
        try {
            String sql = "INSERT INTO brands(brandName, parentId) VALUES(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, brand.getBrandName());
            stmt.setInt(2, brand.getParentId());
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Brand inserted");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(Brand brand) {
        try {
            String sql = "UPDATE brands SET brandName = ?, parentId = ? WHERE brandId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, brand.getBrandName());
            stmt.setInt(2, brand.getParentId());
            stmt.setInt(3, brand.getBrandId());
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

    public ArrayList <Brand> listBrands() {
        ArrayList <Brand> results = new ArrayList <Brand> ();
        try {
            String sql = "SELECT brandId, brandName, parentId FROM brands";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet brands = stmt.executeQuery();
            while (brands.next()) {
                Brand brand = new Brand();
                brand.setBrandId(brands.getInt("brandId"));
                brand.setBrandName(brands.getString("brandName"));
                brand.setParentId(brands.getInt("parentId"));
                results.add(brand);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public Brand getBrandOfId(int BrandId) {
        Brand brand = new Brand();
        try {
            String sql = "SELECT brandId, brandName, parentId FROM brands WHERE brandId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, BrandId);
            ResultSet brands = stmt.executeQuery();
            while (brands.next()) {
                brand.setBrandId(brands.getInt("brandId"));
                brand.setBrandName(brands.getString("brandName"));
                brand.setParentId(brands.getInt("parentId"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brand;
    }

    public ArrayList <Item> searchByBrandId(int BrandId) {
        ArrayList <Item> results = new ArrayList <Item> ();
        try {
            String sql = "SELECT items.itemId, items.itemName , items.subCatId, items.brandId, items.quantity, items.price FROM items, brands WHERE items.brandId = brands.brandId AND brands.brandId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, BrandId);
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

    public ArrayList <Item> searchByBrandName(String BrandName) {
        ArrayList <Item> results = new ArrayList <Item> ();
        try {
            String sql = "SELECT items.itemId, items.itemName , items.subCatId, items.brandId, items.quantity, items.price FROM items, brands WHERE items.brandId = brands.brandId AND brands.brandName = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, BrandName);
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

    public void deleteByBrandId(int brandID) {
        try {
            String sql = "DELETE FROM brands WHERE brandId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, brandID);
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Brand deleted");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}