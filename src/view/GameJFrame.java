package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame {
    private int[][] myData;
    private int[][] myVictory;
    private int myX;
    private int myY;
    private Random myRandom;
    private String myPath;
    private String myAnimalPath;
    private String myBeautyPath;
    private String mySportPath;
    private int myStep;
    private JMenuBar myMenuBar;
    private JMenu myOptionMenu;
    private JMenu myCategoryMenu;
    private JMenu myAboutMenu;

    public GameJFrame() {
        myData = new int[4][4];
        myX = 0;
        myY = 0;
        myRandom = new Random();
        myPath = "library/images/animal/animal3/";
        myAnimalPath = "";
        myBeautyPath = "";
        mySportPath = "";
        myVictory = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };
        myStep = 0;

        initJFrame();
        initJMenuBar();
        initData();
        initImage();
        this.setVisible(true);
    }

    public void initJFrame() {
        myMenuBar = new JMenuBar();
        myOptionMenu = new JMenu("Options");
        myCategoryMenu = new JMenu("Category");
        myAboutMenu = new JMenu("About");
        this.setSize(603, 680);
        this.setTitle("Jigsaw Puzzle Game v1.0");
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(myMenuBar);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                // if pressed 'a' or 'A', show the intact image
                if (code == 65) {
                    //load the full image
                    getContentPane().removeAll();
                    ImageIcon fullImage = new ImageIcon(myPath + "all.jpg");
                    JLabel jLabel = new JLabel(fullImage);
                    jLabel.setBounds(83, 134, 420, 420);
                    getContentPane().add(jLabel);
                    //load the background image
                    ImageIcon bg = new ImageIcon("library/images/background.png");
                    JLabel backgroundLabel = new JLabel(bg);
                    backgroundLabel.setBounds(40, 40, 508, 560);
                    getContentPane().add(backgroundLabel);

                    getContentPane().repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                if (victory()) {
                    return;
                }
                System.out.println("The code is " + code);
                // 37-left, 38-up, 39-right, 40-down
                // 37 move the block on the right side to left.
                if (code == 37) {
                    // the blank block is already the right most one
                    System.out.println("Move the right side block to the left.");
                    if (myY == 3) {
                        return;
                    }
                    myData[myX][myY] = myData[myX][myY + 1];
                    myData[myX][myY + 1] = 0;
                    myY++;
                    myStep++;
                    initImage();
                } else if (code == 38) {
                    System.out.println("Move the block below to the up");
                    // move the block below the current block up
                    if (myX == 3) {
                        // the current block is already the lowest one
                        return;
                    }
                    myData[myX][myY] = myData[myX + 1][myY];
                    myData[myX + 1][myY] = 0;
                    myX++;
                    myStep++;
                    initImage();
                } else if (code == 39) {
                    //move the block on the left side of the blank block to the right
                    System.out.println("Move the block on the left side to the right");
                    if (myY == 0) {
                        return;
                    }
                    myData[myX][myY] = myData[myX][myY - 1];
                    myData[myX][myY - 1] = 0;
                    myY--;
                    myStep++;
                    initImage();

                } else if (code == 40) {
                    //move the block at the upside of the blank block down
                    System.out.println("Move the block on the up side to the down side.");
                    if (myX == 0) {
                        return;
                    }
                    myData[myX][myY] = myData[myX - 1][myY];
                    myData[myX - 1][myY] = 0;
                    myX--;
                    myStep++;
                    initImage();
                } else if (code == 65) {
                    initImage();
                } else if (code == 86) {
                    myData = myVictory.clone();
                    initImage();
                }
            }
        });
    }

    public void initJMenuBar() {
        JMenuItem beautyItem = new JMenuItem("Beauties");
        JMenuItem animalItem = new JMenuItem("Animals");
        JMenuItem sportItem = new JMenuItem("Sports");
        JMenuItem aboutItem = new JMenuItem("Author");
        JMenuItem restartItem = new JMenuItem("Restart");
        JMenuItem reLoginItem = new JMenuItem("ReLogin");
        JMenuItem closeItem = new JMenuItem("Close");

        myMenuBar.add(myOptionMenu);
        myMenuBar.add(myCategoryMenu);
        myMenuBar.add(myAboutMenu);

        myOptionMenu.add(restartItem);
        myOptionMenu.add(reLoginItem);
        myOptionMenu.add(closeItem);

        myCategoryMenu.add(beautyItem);
        myCategoryMenu.add(animalItem);
        myCategoryMenu.add(sportItem);

        myAboutMenu.add(aboutItem);


        restartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj == restartItem) {
                    System.out.println("Restart the game.");
                    myStep = 0;
                    initData();
                    initImage();
                }
            }
        });

        reLoginItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj == reLoginItem) ;
                System.out.println("ReLogin the game.");
                new LoginJFrame();
            }
        });

        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj == closeItem) {
                    System.out.println("Close the game.");
                    //Close JVM
                    System.exit(0);
                }
            }
        });

        beautyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj == beautyItem) {
                    myStep = 0;
                    initData();
                    int imageNumber = myRandom.nextInt(7);
                    myBeautyPath = "library/images/girl/girl" + imageNumber + "/";
                    myPath = myBeautyPath;
                    initImage();
                }
            }
        });

        animalItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj == animalItem) {
                    myStep = 0;
                    initData();
                    int imageNumber = myRandom.nextInt(9);
                    myAnimalPath = "library/images/animal/animal" + imageNumber + "/";
                    myPath = myAnimalPath;
                    initImage();
                }
            }
        });

        sportItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj == sportItem) {
                    myStep = 0;
                    initData();
                    int imageNumber = myRandom.nextInt(11);
                    mySportPath = "library/images/sport/sport" + imageNumber + "/";
                    myPath = mySportPath;
                    initImage();
                }
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj == aboutItem) {
                    System.out.println("About author.");
                    JDialog jDialog = new JDialog();
                    jDialog.setSize(344, 344);
                    jDialog.setAlwaysOnTop(true);
                    jDialog.setLocationRelativeTo(null);
                    jDialog.setModal(true);
                    ImageIcon catImage = new ImageIcon("library/images/about.JPG");
                    JLabel jLabel = new JLabel(catImage);
                    jLabel.setBounds(0, 0, 258, 258);
                    jDialog.getContentPane().add(jLabel);
                    jDialog.setVisible(true);
                }
            }
        });

    }

    /**
     * shuffle the values in an array
     * save data to 2D array
     */
    public void initData() {
        int[] tempArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        for (int i = 0; i < tempArr.length; i++) {
            int index = myRandom.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        for (int i = 0; i < tempArr.length; i++) {
            // value 0 is the blank block
            if (tempArr[i] == 0) {
                myX = i / 4;
                myY = i % 4;
            }
            myData[i / 4][i % 4] = tempArr[i];
        }
    }

    public void initImage() {
        this.getContentPane().removeAll();
        if (victory()) {
            ImageIcon winIcon = new ImageIcon("library/images/win.png");
            JLabel winLabel = new JLabel(winIcon);
            winLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winLabel);
        }
        JLabel stepCountLabel = new JLabel("Steps: " + myStep);
        stepCountLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCountLabel);

        for (int i = 0; i < myData.length; i++) {
            for (int j = 0; j < myData[i].length; j++) {
                int num = myData[i][j];
                ImageIcon image = new ImageIcon(myPath + num + ".jpg");
                JLabel jLabel = new JLabel(image);
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }
        }

        ImageIcon bg = new ImageIcon("library/images/background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    public boolean victory() {
        for (int i = 0; i < myData.length; i++) {
            for (int j = 0; j < myData[i].length; j++) {
                if (myData[i][j] != myVictory[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
