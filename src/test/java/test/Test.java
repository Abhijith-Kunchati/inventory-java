package test;

import grocery.base.connection.DbConnect;
import grocery.base.dao.BrandDAO;
import grocery.base.dao.ItemDAO;
import grocery.base.dao.SubCategoryDAO;
import grocery.base.pojo.Brand;
import grocery.base.pojo.Item;
import grocery.base.pojo.SubCategory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void printSubCategories(ArrayList <SubCategory> subCategories) {
        if (subCategories.isEmpty()) {
            System.out.println("No categories found! Please insert one");
        } else {
            System.out.println("--------------------------");
            System.out.println("Categories:");
            System.out.println("Category Id\tCategory");
            for (SubCategory subCategory: subCategories) {
                System.out.println(subCategory.getSubCatId() + "\t" + subCategory.getSubCatName());
            }
            System.out.println("--------------------------");
        }
    }

    public static boolean containsSubCat(ArrayList <SubCategory> subCategories, int subCatID) {
        for (SubCategory subCategory: subCategories) {
            if (subCategory.getSubCatId() == subCatID) return true;
        }
        return false;
    }

    public static void printBrands(ArrayList <Brand> brands) {
        if (brands.isEmpty()) {
            System.out.println("No brands found! Please insert one");
        } else {
            System.out.println("--------------------------");
            System.out.println("Brands:");
            System.out.println("Brand Id\tBrand");
            for (Brand brand: brands) {
                System.out.println(brand.getBrandId() + "\t" + brand.getBrandName());
            }
            System.out.println("--------------------------");
        }
    }

    private static boolean containsBrand(ArrayList <Brand> brands, int brandID) {
        for (Brand brand: brands) {
            if (brand.getBrandId() == brandID) return true;
        }
        return false;
    }

    private static void printItems(ArrayList <Item> items) {
        if (items.isEmpty()) {
            System.out.println("No items found! Please insert one");
        } else {
            System.out.println("--------------------------");
            System.out.println("Items:");
            System.out.println("Item Id\tItem");
            for (Item item: items) {
                System.out.println(item.getItemID() + "\t" + item.getItemName());
            }
            System.out.println("--------------------------");
        }
    }

    private static boolean containsItem(ArrayList <Item> items, int itemID) {
        for (Item item: items) {
            if (item.getItemID() == itemID) return true;
        }
        return false;
    }

    public static int displayOptions(int option, Scanner in ) {
        System.out.println("---------------------------------");
        System.out.println("1. Insert item \n2. Update item \n3. List items \n4. Delete item \n5. Exit");
        System.out.println("---------------------------------");
        System.out.print("Choose an Operation: ");
        option = in.nextInt();
        return option;
    }

    public static int displayItemOptions(int option, Scanner in ) {
        System.out.println("---------------------------------");
        System.out.println("Possible Operations:");
        System.out.println("1. Update item name \n2. Update category \n3. Update brand \n4. Update quantity \n5. Update price \n6. Finish");
        System.out.println("---------------------------------");
        System.out.print("Choose an Option: ");
        option = in.nextInt();
        return option;
    }

    public static void insertItem(Scanner in , Connection con) {
        String itemName;
        int subCatID = 0;
        int brandID = 0;
        int availQuantity;
        int price;
        boolean validSubCat = false;
        boolean validBrand = false;
        SubCategoryDAO subCategoryDAO = new SubCategoryDAO(con);
        BrandDAO brandDAO = new BrandDAO(con);
        System.out.print("Enter name of the item: ");
        itemName = in.next();
        ArrayList <SubCategory> subCategories = subCategoryDAO.listSubCategories();
        printSubCategories(subCategories);
        while (!validSubCat) {
            System.out.print("Choose a category: ");
            subCatID = in.nextInt();
            validSubCat = containsSubCat(subCategories, subCatID);
            int c = 0;
            if(!validSubCat){
                System.out.println("Invalid choice");
                System.out.println("1. Try again \n2. Abort");
                System.out.print("Your choice: ");
                c = in.nextInt();
            }
            if(c == 2){
                return;
            }
        }
        ArrayList <Brand> brands = brandDAO.listBrands();
        printBrands(brands);
        while (!validBrand) {
            System.out.print("Choose a brand: ");
            brandID = in.nextInt();
            validBrand = containsBrand(brands, brandID);
            int c = 0;
            if(!validBrand){
                System.out.println("Invalid choice");
                System.out.println("1. Try again \n2. Abort");
                System.out.print("Your choice: ");
                c = in.nextInt();
            }
            if(c == 2){
                return;
            }
        }
        System.out.print("Enter quantity of items: ");
        availQuantity = in.nextInt();
        System.out.print("Enter item's price: ");
        price = in.nextInt();
        Item item = new Item(itemName, subCatID, brandID, availQuantity, price);
        ItemDAO itemDAO = new ItemDAO(con);
        itemDAO.insert(item);
    }

    private static void updateItem(Scanner in , Connection con) {
        int itemID = 0;
        String itemName;
        int subCatID = 0;
        int brandID = 0;
        int availQuantity;
        int price;
        boolean validItemId = false;
        ItemDAO itemDAO = new ItemDAO(con);
        SubCategoryDAO subCategoryDAO = new SubCategoryDAO(con);
        BrandDAO brandDAO = new BrandDAO(con);
        ArrayList <Item> items = itemDAO.listItems();
        printItems(items);
        while (!validItemId) {
            System.out.print("Enter an item id: ");
            itemID = in.nextInt();
            validItemId = containsItem(items, itemID);
        }
        Item item = itemDAO.getItemOfId(itemID);
        int option = 0;
        do {
            boolean validSubCat = false;
            boolean validBrand = false;
            option = displayItemOptions(option, in );
            switch (option) {
                case 1:
                    System.out.print("Enter new name: ");
                    itemName = in.next();
                    item.setItemName(itemName);
                    break;
                case 2:
                    ArrayList <SubCategory> subCategories = subCategoryDAO.listSubCategories();
                    printSubCategories(subCategories);
                    while (!validSubCat) {
                        System.out.print("Choose a new category: ");
                        subCatID = in.nextInt();
                        validSubCat = containsSubCat(subCategories, subCatID);
                        int c = 0;
                        if(!validSubCat){
                            System.out.println("Invalid choice");
                            System.out.println("1. Try again \n2. Abort");
                            System.out.print("Your choice: ");
                            c = in.nextInt();
                        }
                        if(c == 2){
                            return;
                        }
                    }
                    item.setCategoryID(subCatID);
                    break;
                case 3:
                    ArrayList <Brand> brands = brandDAO.listBrands();
                    printBrands(brands);
                    while (!validBrand) {
                        System.out.print("Choose a new brand: ");
                        brandID = in.nextInt();
                        validBrand = containsBrand(brands, brandID);
                        int c = 0;
                        if(!validBrand){
                            System.out.println("Invalid choice");
                            System.out.println("1. Try again \n2. Abort");
                            System.out.print("Your choice: ");
                            c = in.nextInt();
                        }
                        if(c == 2){
                            return;
                        }
                    }
                    item.setBrandID(brandID);
                    break;
                case 4:
                    System.out.print("Enter the new available quantity: ");
                    availQuantity = in.nextInt();
                    item.setAvailQuantity(availQuantity);
                    break;
                case 5:
                    System.out.print("Enter the new price: ");
                    price = in.nextInt();
                    item.setPrice(price);
                    break;
                case 6:
                    break;
                default:
                    System.out.println(option + " is not a valid option");
            }
        }
        while (option != 6);
        itemDAO.update(item);
    }

    private static void displayItems(Connection con) {
        ItemDAO itemDAO = new ItemDAO(con);
        ArrayList <Item> items = itemDAO.listItems();
        if (items.isEmpty()) {
            System.out.println("Item list is empty!");
        } else {
            System.out.println("--------------------------");
            System.out.println("Items detailed view:");
            System.out.println("Item Id\tItem Name\tCategory\tBrand\tQuantity\tPrice");
            for (Item item: items) {
                System.out.println(item.getItemID() + "\t" + item.getItemName() + "\t" + item.getCategoryID() + "\t" + item.getBrandID() + "\t" + item.getAvailQuantity() + "\t" + item.getPrice());
            }
            System.out.println("--------------------------");
        }
    }

    private static void deleteItem(Scanner in , Connection con) {
        int itemID = 0;
        boolean validItemId = false;
        ItemDAO itemDAO = new ItemDAO(con);
        ArrayList <Item> items = itemDAO.listItems();
        printItems(items);
        while (!validItemId) {
            System.out.print("Enter an item id: ");
            itemID = in.nextInt();
            validItemId = containsItem(items, itemID);
            int c = 0;
            if(!validItemId){
                System.out.println("Invalid choice");
                System.out.println("1. Try again \n2. Abort");
                System.out.print("Your choice: ");
                c = in.nextInt();
            }
            if(c == 2){
                return;
            }
        }
        itemDAO.deleteById(itemID);
    }

    public static void main(String[] args) {
        DbConnect connect = new DbConnect();
        Connection con = null;
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        try {
            con = connect.getConnection();
            int option = 0;
            do {
                option = displayOptions(option, in);
                switch (option) {
                    case 1:
                        insertItem(in , con);
                        break;
                    case 2:
                        updateItem(in , con);
                        break;
                    case 3:
                        displayItems(con);
                        break;
                    case 4:
                        deleteItem(in , con);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println(option + " is not a valid option");
                }
            } while (option != 5);

        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}