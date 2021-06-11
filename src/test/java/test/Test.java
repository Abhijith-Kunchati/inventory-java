package test;

import grocery.base.connection.DbConnect;
import grocery.base.dao.BrandDAO;
import grocery.base.dao.CategoryDAO;
import grocery.base.dao.ItemDAO;
import grocery.base.dao.SubCategoryDAO;
import grocery.base.pojo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    private static void printSubCategories(ArrayList <SubCategory> subCategories) {
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

    private static boolean containsSubCat(ArrayList <SubCategory> subCategories, int subCatID) {
        for (SubCategory subCategory: subCategories) {
            if (subCategory.getSubCatId() == subCatID) return true;
        }
        return false;
    }

    private static void printCategories(ArrayList<Category> categories){
        if (categories.isEmpty()) {
            System.out.println("No categories found! Please insert one");
        } else {
            System.out.println("--------------------------");
            System.out.println("Categories:");
            System.out.println("Category Id\tCategory");
            for (Category category: categories) {
                System.out.println(category.getCategoryID() + "\t" + category.getCategoryName());
            }
            System.out.println("--------------------------");
        }
    }

    private static boolean containsCategory(ArrayList <Category> categories, int categoryID){
        for (Category category: categories) {
            if (category.getCategoryID() == categoryID) return true;
        }
        return false;
    }

    private static void printBrands(ArrayList <Brand> brands) {
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

    private static void printParentBrands(ArrayList <ParentBrand> parentBrands) {
        if (parentBrands.isEmpty()) {
            System.out.println("No parents brands found! Please insert one");
        } else {
            System.out.println("--------------------------");
            System.out.println("Parent brands:");
            System.out.println("Parent brand Id\tParent brand");
            for (ParentBrand parentBrand: parentBrands) {
                System.out.println(parentBrand.getParentId() + "\t" + parentBrand.getParentName());
            }
            System.out.println("--------------------------");
        }
    }

    private static boolean containsParentBrand(ArrayList <ParentBrand> parentBrands, int parentBrandID) {
        for (ParentBrand parentBrand: parentBrands) {
            if (parentBrand.getParentId() == parentBrandID) return true;
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

    public static int displayItemOperations(int option, Scanner in ) {
        System.out.println("---------------------------------");
        System.out.println("1. Insert item \n2. Update item \n3. List items \n4. Delete item \n5. Exit");
        System.out.println("---------------------------------");
        System.out.print("Choose an Operation: ");
        option = in.nextInt();
        return option;
    }

    public static int displayCategoryOperations(int option, Scanner in) {
        System.out.println("---------------------------------");
        System.out.println("1. Insert category \n2. Update category \n3. List categories \n4. Search item by category \n5. Delete category \n5. Exit");
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

    public static int displaySubCatOptions(int option, Scanner in ) {
        System.out.println("---------------------------------");
        System.out.println("Possible Operations:");
        System.out.println("1. Update sub-category name \n2. Update category \n3. Update units \n4. Finish");
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
            System.out.print("Choose a sub-category: ");
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

    public static void insertCategory(Scanner in , Connection con){
        String categoryName;
        Category category = new Category();
        System.out.print("Enter name of the category: ");
        categoryName = in.next();
        category.setCategoryName(categoryName);
        CategoryDAO categoryDAO = new CategoryDAO(con);
        categoryDAO.insert(category);
    }

    public static void updateCategory(Scanner in , Connection con){
        int categoryID = 0;
        String categoryName;
        boolean validCategoryId = false;
        CategoryDAO categoryDAO = new CategoryDAO(con);
        ArrayList<Category> categories = categoryDAO.listCategories();
        printCategories(categories);
        while (!validCategoryId) {
            System.out.print("Enter a category id: ");
            categoryID = in.nextInt();
            validCategoryId = containsCategory(categories, categoryID);
            int c = 0;
            if(!validCategoryId){
                System.out.println("Invalid choice");
                System.out.println("1. Try again \n2. Abort");
                System.out.print("Your choice: ");
                c = in.nextInt();
            }
            if(c == 2){
                return;
            }
        }
        Category category = categoryDAO.getCategoryOfId(categoryID);
        System.out.print("Enter the category name: ");
        categoryName = in.next();
        category.setCategoryName(categoryName);
        categoryDAO.update(category);
    }

    public static void displayCategories(Connection con){
        CategoryDAO categoryDAO = new CategoryDAO(con);
        ArrayList<Category> categories = categoryDAO.listCategories();
        printCategories(categories);
    }

    public static void searchByCategory(Scanner in, Connection con){
        int categoryID = 0;
        boolean validCategoryId = false;
        CategoryDAO categoryDAO = new CategoryDAO(con);
        ArrayList<Category> categories = categoryDAO.listCategories();
        printCategories(categories);
        while (!validCategoryId){
            System.out.print("Choose a category:");
            categoryID = in.nextInt();
            validCategoryId = containsCategory(categories, categoryID);
            int c = 0;
            if(!validCategoryId){
                System.out.println("Invalid choice");
                System.out.println("1. Try again \n2. Abort");
                System.out.print("Your choice: ");
                c = in.nextInt();
            }
            if(c == 2){
                return;
            }
        }
        ArrayList<Item> items = categoryDAO.searchByCategoryId(categoryID);
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

    private static void deleteCategory(Scanner in , Connection con) {
        int categoryID = 0;
        boolean validCategoryId = false;
        CategoryDAO categoryDAO = new CategoryDAO(con);
        ArrayList<Category> categories = categoryDAO.listCategories();
        printCategories(categories);
        while (!validCategoryId) {
            System.out.print("Enter a category id: ");
            categoryID = in.nextInt();
            validCategoryId = containsCategory(categories, categoryID);
            int c = 0;
            if(!validCategoryId){
                System.out.println("Invalid choice");
                System.out.println("1. Try again \n2. Abort");
                System.out.print("Your choice: ");
                c = in.nextInt();
            }
            if(c == 2){
                return;
            }
        }
        categoryDAO.deleteByCategoryId(categoryID);
    }

    private static void insertSubCategory(Scanner in , Connection con){
        String subCatName;
        int categoryId = 0;
        String unit;
        CategoryDAO categoryDAO = new CategoryDAO(con);
        ArrayList<Category> categories = categoryDAO.listCategories();
        boolean validCategoryId = false;
        validCategoryId = containsCategory(categories, categoryId);
        System.out.print("Enter name of the sub-category: ");
        subCatName = in.next();
        printCategories(categories);
        while (!validCategoryId){
            System.out.print("Choose a category:");
            categoryId = in.nextInt();
            validCategoryId = containsCategory(categories, categoryId);
            int c = 0;
            if(!validCategoryId){
                System.out.println("Invalid choice");
                System.out.println("1. Try again \n2. Abort");
                System.out.print("Your choice: ");
                c = in.nextInt();
            }
            if(c == 2){
                return;
            }
        }
        System.out.print("Enter the units for this sub-category: ");
        unit = in.next();
        SubCategory subCategory = new SubCategory(subCatName, categoryId, unit);
        SubCategoryDAO subCategoryDAO = new SubCategoryDAO(con);
        subCategoryDAO.insert(subCategory);
    }

    private static void updateSubCategory(Scanner in , Connection con){
        int subCatId = 0;
        String subCatName;
        int categoryId = 0;
        String unit;
        boolean validSubCatId = false;
        SubCategoryDAO subCategoryDAO = new SubCategoryDAO(con);
        CategoryDAO categoryDAO = new CategoryDAO(con);
        ArrayList<SubCategory> subCategories = subCategoryDAO.listSubCategories();
        printSubCategories(subCategories);
        while (!validSubCatId) {
            System.out.print("Enter a sub-category id: ");
            subCatId = in.nextInt();
            validSubCatId = containsSubCat(subCategories, subCatId);
            int c = 0;
            if(!validSubCatId){
                System.out.println("Invalid choice");
                System.out.println("1. Try again \n2. Abort");
                System.out.print("Your choice: ");
                c = in.nextInt();
            }
            if(c == 2){
                return;
            }
        }
        SubCategory subCategory = subCategoryDAO.getSubCatOfId(subCatId);
        int option = 0;
        do {
            boolean validCategoryId = false;
            option = displaySubCatOptions(option, in );
            switch (option) {
                case 1:
                    System.out.print("Enter new name: ");
                    subCatName = in.next();
                    subCategory.setSubCatName(subCatName);
                    break;
                case 2:
                    ArrayList <Category> categories = categoryDAO.listCategories();
                    printCategories(categories);
                    while (!validCategoryId) {
                        System.out.print("Choose a new category: ");
                        categoryId = in.nextInt();
                        validCategoryId = containsCategory(categories, categoryId);
                        int c = 0;
                        if(!validCategoryId){
                            System.out.println("Invalid choice");
                            System.out.println("1. Try again \n2. Abort");
                            System.out.print("Your choice: ");
                            c = in.nextInt();
                        }
                        if(c == 2){
                            return;
                        }
                    }
                    subCategory.setCategoryId(categoryId);
                    break;
                case 3:
                    System.out.print("Enter new units: ");
                    unit = in.next();
                    subCategory.setUnit(unit);
                    break;
                case 4:
                    break;
                default:
                    System.out.println(option + " is not a valid option");
            }
        }
        while (option != 4);
        subCategoryDAO.update(subCategory);
    }

    private static void displaySubCategories(Connection con){

    }

    private static void searchBySubCat(Scanner in,Connection con){

    }

    private static void deleteSubCategory(Scanner in,Connection con){

    }

    public static void displayItemMenu(Scanner in, Connection con){
        int option = 0;
        do {
            option = displayItemOperations(option, in);
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
    }

    public static void displayCategoryMenu(Scanner in, Connection con){
        int option = 0;
        do {
            option = displayCategoryOperations(option, in);
            switch (option) {
                case 1:
                    insertCategory(in , con);
                    break;
                case 2:
                    updateCategory(in , con);
                    break;
                case 3:
                    displayCategories(con);
                    break;
                case 4:
                    searchByCategory(in, con);
                    break;
                case 5:
                    deleteCategory(in , con);
                    break;
                case 6:
                    break;
                default:
                    System.out.println(option + " is not a valid option");
            }
        } while (option != 6);
    }

    public static void displaySubCatMenu(Scanner in, Connection con){
        int option = 0;
        do {
            option = displayCategoryOperations(option, in);
            switch (option) {
                case 1:
                    insertSubCategory(in , con);
                    break;
                case 2:
                    updateSubCategory(in , con);
                    break;
                case 3:
                    displaySubCategories(con);
                    break;
                case 4:
                    searchBySubCat(in, con);
                    break;
                case 5:
                    deleteSubCategory(in , con);
                    break;
                case 6:
                    break;
                default:
                    System.out.println(option + " is not a valid option");
            }
        } while (option != 6);
    }

    public static int displayOptions(int option, Scanner in){
        System.out.println("---------------------------------");
        System.out.println("Sections in inventory:");
        System.out.println("1. Items \n2. Categories \n3. Sub-categories \n4. Brands \n5. Parent Brands");
        System.out.println("---------------------------------");
        System.out.println("Press '6' to Abort");
        System.out.print("Choose a section to operate with: ");
        option = in.nextInt();
        return option;
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
                        displayItemMenu(in, con);
                        break;
                    case 2:
                        displayCategoryMenu(in , con);
                        break;
                    case 3:
                        displayItems(con);
                        break;
                    case 4:
                        deleteItem(in , con);
                        break;
                    case 5:
                        insertItem(in , con);
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println(option + " is not a valid option");
                }
            }
            while (option != 6);
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