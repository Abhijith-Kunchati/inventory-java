package grocery.app.Inventory_Web;

import grocery.base.connection.DbConnect;
import grocery.base.dao.ItemDAO;
import grocery.base.pojo.Item;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "showItems-v1", value = "/show-items-v.01")
public class ShowItemsV1 extends HttpServlet {
    private DbConnect connect;
    private Connection con;
    private ItemDAO itemDAO;
    private ArrayList<Item> items;

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

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");

        PrintWriter out = res.getWriter();
        out.println("<html><head>"+"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">"+"<title> Inventory Items </title>"+"</head><body>");
        out.println("<h1>List of items in the inventory<h1>");
        out.println("<table class=\"table\"><thead>");
        out.println("<tr>\n" +
                "      <th scope=\"col\">#Item Id</th>\n" +
                "      <th scope=\"col\">Item Name</th>\n" +
                "      <th scope=\"col\">Sub-category</th>\n" +
                "      <th scope=\"col\">Brand</th>\n" +
                "      <th scope=\"col\">Quantity</th>\n" +
                "      <th scope=\"col\">Price</th>\n" +
                "    </tr></thead><tbody>");
        for (Item item: items) {
            out.println("<tr>\n" +
                    "      <th scope=\"row\">"+ item.getItemID() +"</th>\n" +
                    "      <td>"+ item.getItemName() +"</td>\n" +
                    "      <td>"+ item.getCategoryID() +"</td>\n" +
                    "      <td>"+ item.getBrandID() +"</td>\n" +
                    "      <td>"+ item.getAvailQuantity() +"</td>\n" +
                    "      <td>"+ item.getPrice() +"</td>\n" +
                    "    </tr>");
        }
        out.println("</tbody></table>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
