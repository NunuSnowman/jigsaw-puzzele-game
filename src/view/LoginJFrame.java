package view;

import model.User;
import util.VerificationCodeUtil;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginJFrame extends JFrame {
    private ArrayList<User> allUers;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField username;
    private JPasswordField password;
    private JTextField verificationCode;
    private JLabel displayCode;
    private int chance = 0;

    public LoginJFrame() {
        allUers = new ArrayList<>();
        loginButton = new JButton();
        registerButton = new JButton();
        username = new JTextField();
        password = new JPasswordField();
        verificationCode = new JTextField();
        displayCode = new JLabel();

        readUserInfo();
        initJFrame();
        initView();
        this.setVisible(true);
    }

    public void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("Jigsaw Puzzle Game v1.0 Login");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
    }

    public void initView() {
        // setup username text
        ImageIcon usernameImage = new ImageIcon("library/images/login/username.png");
        JLabel usernameText = new JLabel(usernameImage);
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);
        // setup username text filed
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        // setup password text
        ImageIcon passwordImage = new ImageIcon("library/images/login/password.png");
        JLabel passwordText = new JLabel(passwordImage);
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);
        //setup password text field
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //setup verification code text
        ImageIcon codeImage = new ImageIcon("library/images/login/verificationCode.png");
        JLabel codeText = new JLabel(codeImage);
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);
        // setup verification code text field
        verificationCode.setBounds(195, 256, 100, 30);
        this.getContentPane().add(verificationCode);

        // display the verification code
        String code = VerificationCodeUtil.getcode();
        displayCode.setText(code);
        displayCode.setBounds(300, 256, 50, 30);
        this.getContentPane().add(displayCode);

        displayCode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Generate a new verification code");
                String newCode = VerificationCodeUtil.getcode();
                displayCode.setText(newCode);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // setup the Login button
        ImageIcon loginImage = new ImageIcon("library/images/login/login.png");
        loginButton.setIcon(loginImage);
        loginButton.setBounds(123, 310, 128, 47);
        // remove the outlier
        loginButton.setBorderPainted(false);
        // remove the background of the button
        loginButton.setContentAreaFilled(false);
        this.getContentPane().add(loginButton);

        loginButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Login Button is clicked.");
                // Check if the maximum number of attempts has been reached
                if (chance >= 3) {
                    showDialog("Maximum attempts reached. Please try again later.");
                    return; // Exit the method to prevent further processing
                }

                String usernameInput = username.getText();
                String passwordInput = new String(password.getPassword());
                String codeInput = verificationCode.getText();

                // create a User obejct
                User currentUser = new User(usernameInput, passwordInput);
                System.out.println("username entered: " + usernameInput);
                System.out.println("password entered: " + passwordInput);
                if (codeInput.length() == 0) {
                    showDialog("The verification code can not be null.");
                } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                    showDialog("username or password is blank.");
                } else if (!codeInput.equalsIgnoreCase(displayCode.getText())) {
                    showDialog("Wrong verification code!");
                } else if (contains(currentUser)) {
                    System.out.println("username and password are correct, enter game.");
                    setVisible(false);
                    new GameJFrame();
                } else {
                    chance++; // Increment the chance counter
                    System.out.println("Wrong password. Attempts left: " + (3 - chance));
                    showDialog("Wrong password! You have " + (3 - chance) + " attempts left.");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

                ImageIcon newImage = new ImageIcon("library/images/login/pressLogin.png");
                loginButton.setIcon(newImage);

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                ImageIcon newImage = new ImageIcon("library/images/login/login.png");
                loginButton.setIcon(newImage);

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // setup the register button
        ImageIcon registerImage = new ImageIcon("library/images/login/register.png");
        registerButton.setIcon(registerImage);
        registerButton.setBounds(256, 310, 128, 47);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        this.getContentPane().add(registerButton);

        registerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Register Button is clicked.");
                setVisible(false);
                new RegisterJFrame(allUers);
            }

            @Override
            public void mousePressed(MouseEvent e) {

                ImageIcon newImage = new ImageIcon("library/images/login/pressRegister.png");
                registerButton.setIcon(newImage);

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                ImageIcon newImage = new ImageIcon("library/images/login/register.png");
                registerButton.setIcon(newImage);

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        ImageIcon bg = new ImageIcon("library/images/login/background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    public void showDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(250, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        // if not close the dialog, can't go on
        jDialog.setModal(true);
        // HTML Formatting allows for automatic line wrapping.
        JLabel warning = new JLabel("<html>" + content + "</html>");
        warning.setBounds(0, 0, 250, 150);
        // set the JLabel stay center
        warning.setHorizontalAlignment(SwingConstants.CENTER);
        warning.setVerticalAlignment(SwingConstants.CENTER);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);
    }

    /**
     * Read user data from local file
     */
    public void readUserInfo() {
        File file = new File("userinfo.txt");
        try {
            System.out.println("Read users from file");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] strArr = line.split("&");
                String[] usernameArr = strArr[0].split("=");
                String[] passwordArr = strArr[1].split("=");
                String username = usernameArr[1];
                String password = passwordArr[1];
                allUers.add(new User(username, password));
            }
            // release any resources associated with the Scanner object.
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(User userInput) {
        System.out.println("userInput " + userInput.toString());
        for (int i = 0; i < allUers.size(); i++) {
            User currentUser = allUers.get(i);
            System.out.println("currentUser " + currentUser.toString());
            if (currentUser.getUsername().equals(userInput.getUsername()) && currentUser.getPassword().equals(userInput.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
