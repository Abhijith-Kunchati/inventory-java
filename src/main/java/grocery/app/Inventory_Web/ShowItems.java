package grocery.app.Inventory_Web;

import com.google.gson.Gson;
import grocery.base.connection.DbConnect;
import grocery.base.dao.ItemDAO;
import grocery.base.pojo.Item;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "showItems", value = "/show-items")
public class ShowItems extends HttpServlet {
    private DbConnect connect;
    private Connection con;
    private ItemDAO itemDAO;
    private ArrayList<Item> items;
    private Gson gson = new Gson();

    public void init() {
        this.connect = new DbConnect();
        try {
            con = connect.getConnection();
        }
        catch (SQLException e) {
            System.out.println("SQL Exception:" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        itemDAO = new ItemDAO(con);
        items = itemDAO.listItems();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE, HEAD");
        res.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        String itemsJson = this.gson.toJson(items);
        PrintWriter out = res.getWriter();
        out.print(itemsJson);
        out.flush();
    }

    public void destroy() {
    }
}
