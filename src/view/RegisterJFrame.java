package view;

import model.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RegisterJFrame extends JFrame {
    private ArrayList<User> allUsers;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField rePasswordField;
    private JButton registerButton;
    private JButton resetButton;


    public RegisterJFrame(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        rePasswordField = new JPasswordField();
        registerButton = new JButton();
        resetButton = new JButton();
        initFrame();
        initView();
        this.setVisible(true);
    }

    public void initFrame() {
        this.setSize(488, 430);
        this.setTitle("Jigsaw Puzzle Game v1.0 Register");
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }

    public void initView() {
        ImageIcon usernameIcon = new ImageIcon("library/images/register/registerUsername.png");
        JLabel registerUsername = new JLabel(usernameIcon);
        registerUsername.setBounds(85, 135, 80, 20);
        usernameField.setBounds(195, 134, 200, 30);
        this.getContentPane().add(registerUsername);
        this.getContentPane().add(usernameField);


        ImageIcon passwordIcon = new ImageIcon("library/images/register/registerPassword.png");
        JLabel registerPassword = new JLabel(passwordIcon);
        registerPassword.setBounds(97, 193, 70, 20);
        passwordField.setBounds(195, 195, 200, 30);
        this.getContentPane().add(registerPassword);
        this.getContentPane().add(passwordField);


        ImageIcon rePasswordIcon = new ImageIcon("library/images/register/reenterPassword.png");
        JLabel reenterPassword = new JLabel(rePasswordIcon);
        reenterPassword.setBounds(64, 255, 95, 20);
        rePasswordField.setBounds(195, 255, 200, 30);
        this.getContentPane().add(rePasswordField);
        this.getContentPane().add(reenterPassword);


        ImageIcon registerImage = new ImageIcon("library/images/register/register.png");
        registerButton.setIcon(registerImage);
        registerButton.setBounds(123, 310, 128, 47);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        this.getContentPane().add(registerButton);

        registerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String usernameText = usernameField.getText();
                String passwordText = new String(passwordField.getPassword());
                String rePasswordText = new String(rePasswordField.getPassword());
                System.out.println("register button is clicked.");
                System.out.println("username entered is " + usernameText);
                System.out.println("password entered is " + passwordText);
                System.out.println("reentered password is " + rePasswordText);
                if (usernameText.length() == 0) {
                    String warning = "username or password is blank.";
                    showDialog(warning);
                } else if (!usernameText.matches("[a-zA-Z0-9]{4,16}")) {
                    showDialog("username is invalid.");
                } else if (containsUsername(usernameText)) {
                    System.out.println("username exists");
                    showDialog("username exists, please enter a new one.");
                } else if (!passwordText.equals(rePasswordText)) {
                    System.out.println("Passwords don't match!");
                    showDialog("Passwords don't match!");
                } else if (passwordText.length() == 0) {
                    String warning = "passwordText is blank.";
                    showDialog(warning);
                } else if (rePasswordText.length() == 0) {
                    String warning = "rePasswordTextis blank.";
                    showDialog(warning);
                } else if (!passwordText.matches("\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[a-z])\\S*")) {
                    showDialog("password is invalid, it should at least contain one lowercase letter, ane digit, and the length should at least be 6.");
                } else {
                    System.out.println("ready to write to file.");
                    User newUser = new User(usernameText, passwordText);
                    allUsers.add(newUser);
                    File file = new File("userinfo.txt");
                    try(PrintWriter printWriter = new PrintWriter(file)) {
                        for (User u : allUsers) {
                            String s = u.toString();
                            System.out.println("string to file: " + s);
                            printWriter.write(s + System.lineSeparator());
                        }
                        showDialog("Registered successfully!");
                        setVisible(false);
                    } catch (IOException event) {
                        event.printStackTrace();
                    }
                    new LoginJFrame();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon newImage = new ImageIcon("library/images/register/pressRegister.png");
                registerButton.setIcon(newImage);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon newImage = new ImageIcon("library/images/register/register.png");
                registerButton.setIcon(newImage);

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        ImageIcon resetImage = new ImageIcon("library/images/register/reset.png");
        resetButton.setIcon(resetImage);
        resetButton.setBounds(256, 310, 128, 47);
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        this.getContentPane().add(resetButton);

        resetButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("reset button is clicked.");
                usernameField.setText("");
                passwordField.setText("");
                rePasswordField.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("pressed reset button.");
                ImageIcon newImage = new ImageIcon("library/images/register/pressReset.png");
                registerButton.setIcon(newImage);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("reset button released.");
                ImageIcon newImage = new ImageIcon("library/images/register/reset.png");
                registerButton.setIcon(newImage);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        ImageIcon bg = new ImageIcon("library/images/register/background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    public void showDialog(String contents) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(250, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setModal(true);
        jDialog.setLocationRelativeTo(null);

        JLabel warning = new JLabel("<html>" + contents + "</html>");
        warning.setBounds(0, 0, 250, 150);
        warning.setHorizontalAlignment(SwingConstants.CENTER);
        jDialog.getContentPane().add(warning);
        jDialog.setVisible(true);
    }

    public boolean containsUsername(String currentUsername) {
        for (User user : allUsers) {
            String userName = user.getUsername();
            if (userName.equals(currentUsername)) {
                return true;
            }
        }
        return false;
    }
}
