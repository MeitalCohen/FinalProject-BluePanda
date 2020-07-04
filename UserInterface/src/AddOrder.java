import entities.BooksInOrders;
import entities.Order;
import entities.User;
import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.CreateOrderRequest;
import services.requests.RegisterUserRequest;
import services.responses.CreateOrderResponse;
import services.responses.RegisterUserResponse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddOrder {
    public static void AddOrder(String userId) {

        int Y = -15;
        int dY = 35;

        JFrame f = new JFrame("AddOrder");//creating instance of JFrame

        JLabel bookNameLabel;
        JLabel authorName;
        JLabel categorylabl;
        JLabel quantity;
        JLabel price;
        JLabel passwordL;


        bookNameLabel = new JLabel("Book Name");  //Create label Username
        bookNameLabel.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        authorName = new JLabel("Author Name");  //Create label Username
        authorName.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        categorylabl = new JLabel("Category");  //Create label Username
        categorylabl.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height

        quantity = new JLabel("Quantity");  //Create label Password
        quantity.setBounds(30, Y+=dY, 100, 30);

        price = new JLabel("Price");
        price.setBounds(30, Y+=dY, 100, 30); //x axis, y axis, width, height


        Y = -15;
        // add input fields

        JTextField bookNameField = new JTextField(); //Create text field for username
        bookNameField.setBounds(110, Y+=dY, 200, 30);

        JTextField authorNameField = new JTextField(); //Create text field for username
        authorNameField.setBounds(110, Y+=dY, 200, 30);

        JTextField categoryField = new JTextField(); //Create text field for username
        categoryField.setBounds(110, Y+=dY, 200, 30);


        JTextField quantityField = new JTextField();
        quantityField.setBounds(110, Y+=dY, 200, 30);

        JTextField priceField = new JTextField();
        priceField.setBounds(110, Y+=dY, 200, 30); //x axis, y axis, width, height

        JPasswordField passwordF = new JPasswordField(); //Create text field for password
        passwordF.setBounds(110, Y+=dY, 200, 30);



        JButton createOrder_but = new JButton("Order!");
        createOrder_but.setBounds(142, Y+=1.5*dY, 100, 25);
        createOrder_but.addActionListener(new ActionListener() {  //Perform action

            public void actionPerformed(ActionEvent e) {

                String bookNAme = bookNameField.getText();
                String authorName = authorNameField.getText(); //Store username entered by the user in the variable "username"
                String category = categoryField.getText();
                int quantity = 0;
                float price = 0;
                try {
                    quantity = Integer.parseInt(quantityField.getText());
                    price = Float.parseFloat(priceField.getText());
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid value for quantity/price"); //Display dialog box with the message

                }

                if (bookNAme.equals("")) //If username is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter bookName"); //Display dialog box with the message
                } else if (authorName.equals("")) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Please enter Author Name"); //Display dialog box with the message
                } else if (quantity <= 0) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Invalid Quantity"); //Display dialog box with the message
                } else if (price < 0) //If password is null
                {
                    JOptionPane.showMessageDialog(null, "Invalid price"); //Display dialog box with the message
                }
                else if (category.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter book category"); //Display dialog box with the message
                }
                else {

                    Order newOrder = new Order(price, new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()), false, userId, bookNAme, authorName,quantity);
                    BooksInOrders bookInOrder = new BooksInOrders(newOrder.getOrderID(), bookNAme, category);

                    CreateOrderRequest request = new CreateOrderRequest(userId, newOrder, bookInOrder);

                    ServiceCommand sc = ServiceCommand.getInstance();
                    CreateOrderResponse response = sc.execute(request);
                    if (response.getStatus() != ResponseStatus.OK.errorCode()) {
                        JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
                    } else {
                        JOptionPane.showMessageDialog(null, "Order Approved"); //Display Message

                        f.dispose();
                    }
                }
            }
        });

        f.add(bookNameLabel);
        f.add(quantity);
        f.add(price);
        f.add(authorName);


        f.add(bookNameField);
        f.add(priceField);
        f.add(quantityField);
        f.add(authorNameField);


        f.add(createOrder_but);//adding button in JFrame

        f.setSize(400,300);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);


    }

}


