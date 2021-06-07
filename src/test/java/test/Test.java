package test;

import grocery.base.connection.DbConnect;
import grocery.base.dao.BrandDAO;
import grocery.base.dao.ItemDAO;
import grocery.base.pojo.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        DbConnect connect = new DbConnect();
        Connection con = null;
        try{
            con = connect.getConnection();
//            Item soap = new Item(3, "Dove Soap", 1, 2, 10, 80);
            ItemDAO itemDAO = new ItemDAO(con);
//            itemDAO.insert(soap);
            BrandDAO brandDAO = new BrandDAO(con);
//            ArrayList<Item> items = itemDAO.listItems();
            ArrayList<Item> items = brandDAO.searchByBrandId(1);
            for(Item item : items){
                System.out.println("Item Id: " + item.getItemID() + " Item Name: " + item.getItemName() + " Category: " + item.getCategoryID() + " Brand: " + item.getBrandID() + " Quantity: " + item.getAvailQuantity() + " Price: " + item.getPrice());
            }
        } catch (SQLException e){
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
