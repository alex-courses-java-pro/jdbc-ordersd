import com.mysql.jdbc.NotImplemented;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

import dao.ClientsDao;
import dao.ConnectionManager;
import dao.OrdersDao;
import dao.ProductsDao;
import dao.impl.ClientsDaoImpl;
import dao.impl.OrdersDaoImpl;
import dao.impl.ProductsDaoImpl;
import model.Client;
import model.Order;
import model.Product;

/**
 * Created by arahis on 4/27/17.
 */
public class Main {
    private static OrdersDao ordersDao = new OrdersDaoImpl();
    private static ClientsDao clientsDao = new ClientsDaoImpl();
    private static ProductsDao productsDao = new ProductsDaoImpl();

    public static void main(String[] args) throws SQLException {
        ConnectionManager.dbInit();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("1: new client");
                System.out.println("2: new product");
                System.out.println("3: make order");
                System.out.print("-> ");
                int menuOption = Integer.parseInt(scanner.nextLine());
                handleInput(menuOption, scanner);
            }
        }
    }

    private static void handleInput(int menuOption, Scanner scanner) throws SQLException {
        switch (menuOption) {
            case 1:
                newClient(scanner);
                break;
            case 2:
                newProduct(scanner);
                break;
            case 3:
                makeOrder(scanner);
                break;
        }
    }

    private static void makeOrder(Scanner scanner) throws SQLException {
        System.out.print("product name: ");
        String productName = scanner.nextLine();
        Product product = productsDao.getByName(productName);
        System.out.print("clients phone number: ");
        String clientNum = scanner.nextLine();
        Client client = clientsDao.getByPhoneNum(clientNum);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        ordersDao.addOrder(new Order(timestamp, client.getId(), product.getId()));
    }

    private static void newProduct(Scanner scanner) throws SQLException {
        System.out.print("product name: ");
        String name = scanner.nextLine();
        System.out.print("product price: ");
        int price = Integer.parseInt(scanner.nextLine());
        productsDao.addProduct(new Product(name, price));
    }

    private static void newClient(Scanner scanner) throws SQLException {
        System.out.print("client name: ");
        String name = scanner.nextLine();
        System.out.print("client phone number: ");
        String phoneNum = scanner.nextLine();
        clientsDao.addClient(new Client(name, phoneNum));
    }
}
