import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TravelTourismSystem extends JFrame implements ActionListener {

    JLabel titleLabel, destinationLabel, packageLabel, hotelLabel,
           transportLabel, seasonLabel, basePriceLabel, finalPriceLabel, imageLabel;

    JComboBox<String> destinationBox, packageBox, hotelBox, transportBox, seasonBox;

    JButton bookButton, paymentButton;
    JTextArea outputArea;

    String[] destinations = {"Delhi", "Goa", "Kerala", "Ooty", "Rajasthan"};
    String[] packages     = {"Silver", "Gold", "Platinum"};
    String[] hotels       = {"3 Star", "4 Star", "5 Star"};
    String[] transports   = {"Bus", "Train", "Flight"};
    String[] seasons      = {"Off", "Normal", "Peak"};

    String[] images = {
        "delhi.jpg", "goa.jpg", "kerala.jpg", "ooty.jpg", "rajasthan.jpg"
    };

    boolean isBooked = false;
    double finalPrice = 0;
    String bookingID = "";

    public TravelTourismSystem() {
        setTitle("Travel & Tourism Management System");
        setSize(1080, 680);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel("Travel Booking System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(200, 10, 650, 40);
        add(titleLabel);

        destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(30, 80, 100, 25);
        add(destinationLabel);

        destinationBox = new JComboBox<>(destinations);
        destinationBox.setBounds(140, 80, 160, 25);
        destinationBox.addActionListener(this);
        add(destinationBox);

        packageLabel = new JLabel("Package:");
        packageLabel.setBounds(30, 120, 100, 25);
        add(packageLabel);

        packageBox = new JComboBox<>(packages);
        packageBox.setBounds(140, 120, 160, 25);
        packageBox.addActionListener(this);
        add(packageBox);

        hotelLabel = new JLabel("Hotel Type:");
        hotelLabel.setBounds(30, 160, 100, 25);
        add(hotelLabel);

        hotelBox = new JComboBox<>(hotels);
        hotelBox.setBounds(140, 160, 160, 25);
        hotelBox.addActionListener(this);
        add(hotelBox);

        transportLabel = new JLabel("Transport:");
        transportLabel.setBounds(30, 200, 100, 25);
        add(transportLabel);

        transportBox = new JComboBox<>(transports);
        transportBox.setBounds(140, 200, 160, 25);
        transportBox.addActionListener(this);
        add(transportBox);

        seasonLabel = new JLabel("Season:");
        seasonLabel.setBounds(30, 240, 100, 25);
        add(seasonLabel);

        seasonBox = new JComboBox<>(seasons);
        seasonBox.setBounds(140, 240, 160, 25);
        seasonBox.addActionListener(this);
        add(seasonBox);

        basePriceLabel = new JLabel("Base Price: \u20B90");
        basePriceLabel.setBounds(30, 280, 200, 25);
        add(basePriceLabel);

        finalPriceLabel = new JLabel("Final Price: \u20B90");
        finalPriceLabel.setBounds(30, 305, 200, 25);
        add(finalPriceLabel);

        bookButton = new JButton("Book Package");
        bookButton.setBounds(30, 335, 270, 30);
        bookButton.addActionListener(this);
        add(bookButton);

        paymentButton = new JButton("Make Payment");
        paymentButton.setBounds(30, 375, 270, 30);
        paymentButton.addActionListener(this);
        add(paymentButton);

        imageLabel = new JLabel();
        imageLabel.setBounds(355, 75, 530, 295);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(imageLabel);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(30, 420, 1000, 180);
        add(scrollPane);

        updatePrice();
        updateImage();

        outputArea.append("Login Successful\n");
        outputArea.append("Available Packages Displayed\n");

        setVisible(true);
    }

    double getBasePrice() {
        String dest = (String) destinationBox.getSelectedItem();
        switch (dest) {
            case "Delhi":     return 10000;
            case "Goa":       return 12000;
            case "Kerala":    return 11000;
            case "Ooty":      return 8000;
            case "Rajasthan": return 13000;
            default:          return 10000;
        }
    }

    double getPackageCharge() {
        String pkg = (String) packageBox.getSelectedItem();
        switch (pkg) {
            case "Silver":   return 2000;
            case "Gold":     return 5000;
            case "Platinum": return 10000;
            default:         return 0;
        }
    }

    double getHotelCharge() {
        String hotel = (String) hotelBox.getSelectedItem();
        switch (hotel) {
            case "3 Star": return 3000;
            case "4 Star": return 5000;
            case "5 Star": return 8000;
            default:       return 0;
        }
    }

    double getTransportCharge() {
        String transport = (String) transportBox.getSelectedItem();
        switch (transport) {
            case "Bus":    return 500;
            case "Train":  return 1500;
            case "Flight": return 5000;
            default:       return 0;
        }
    }

    double getSeasonMultiplier() {
        String season = (String) seasonBox.getSelectedItem();
        switch (season) {
            case "Off":    return 0.90;
            case "Normal": return 1.00;
            case "Peak":   return 1.20;
            default:       return 1.00;
        }
    }

    void updatePrice() {
        double base = getBasePrice() + getPackageCharge() +
                      getHotelCharge() + getTransportCharge();
        finalPrice = base * getSeasonMultiplier();
        basePriceLabel.setText("Base Price: \u20B9" + (int) base);
        finalPriceLabel.setText("Final Price: \u20B9" + (int) finalPrice);
    }

    void updateImage() {
        int index = destinationBox.getSelectedIndex();
        ImageIcon icon = new ImageIcon(images[index]);
        Image img = icon.getImage().getScaledInstance(530, 295, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            updatePrice();
            Random rand = new Random();
            bookingID = "TTM" + (1000 + rand.nextInt(9000));
            isBooked = true;
            outputArea.append("\nPackage Booked Successfully\n");
            outputArea.append("Booking ID: " + bookingID + "\n");
            outputArea.append("Total Amount: \u20B9" + (int) finalPrice + "\n");
        } else if (e.getSource() == paymentButton) {
            if (isBooked) {
                outputArea.append("\nPayment: SUCCESS\n");
                outputArea.append("Thank You for Choosing Us\n");
            } else {
                outputArea.append("\nPlease Book a Package First.\n");
            }
        } else {
            updatePrice();
            updateImage();
        }
    }

    public static void main(String[] args) {
        new TravelTourismSystem();
    }
}
