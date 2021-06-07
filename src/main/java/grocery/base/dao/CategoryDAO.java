package grocery.base.dao;

import java.sql.*;
import java.util.ArrayList;

import grocery.base.pojo.Category;
import grocery.base.pojo.Item;

public class CategoryDAO {
    private final Connection con;

    public CategoryDAO(Connection con) {
        this.con = con;
    }
    public void insert(Category category) {
        try {
            String sql = "INSERT INTO categories(categoryName) VALUES(?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, category.getCategoryName());
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Category inserted");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(Category category) {
        try {
            String sql = "UPDATE categories SET categoryName = ? WHERE categoryId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, category.getCategoryName());
            stmt.setInt(2, category.getCategoryID());
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Category updated");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList <Category> listCategories() {
        ArrayList <Category> results = new ArrayList <Category> ();
        try {
            String sql = "SELECT categoryId, categoryName FROM categories";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet categories = stmt.executeQuery();
            while (categories.next()) {
                Category category = new Category();
                category.setCategoryID(categories.getInt("categoryId"));
                category.setCategoryName(categories.getString("categoryName"));
                results.add(category);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
    public Category getCategoryOfId(int CategoryId) {
        Category category = new Category();
        try {
            String sql = "SELECT categoryId, categoryName FROM categories WHERE categoryId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, CategoryId);
            ResultSet categories = stmt.executeQuery();
            while (categories.next()) {
                category.setCategoryID(categories.getInt("categoryId"));
                category.setCategoryName(categories.getString("categoryName"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
    public ArrayList <Item> searchByCategoryId(int CategoryId) {
        ArrayList <Item> results = new ArrayList <Item> ();
        try {
            String sql = "SELECT items.itemId, items.itemName , items.subCatId, items.brandId, items.quantity, items.price FROM items, brands, parent_brands WHERE items.brandId = brands.brandId AND brands.parentId = parent_brands.parentId AND parent_brands.parentId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, CategoryId);
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
    public ArrayList <Item> searchByCategoryName(String CategoryName) {
        ArrayList <Item> results = new ArrayList <Item> ();
        try {
            String sql = "SELECT items.itemId, items.itemName , items.subCatId, items.brandId, items.quantity, items.price FROM items, sub_categories, categories WHERE items.subCatId = sub_categories.subCatId AND sub_categories.categoryId = categories.categoryId AND categories.categoryName = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, CategoryName);
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
    public void deleteByCategoryId(int CategoryID) {
        try {
            String sql = "DELETE FROM categories WHERE categoryId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, CategoryID);
            int s = stmt.executeUpdate();
            if (s == 1) {
                System.out.println("Category deleted");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}