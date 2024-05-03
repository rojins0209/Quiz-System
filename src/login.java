import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.Image;


public class login {
    int id;

    public void loginView() throws SQLException {
        SQLoperations manage = new SQLoperations();
        JFrame frame = new JFrame();
        frame.setSize(700,550 );
        frame.setLayout(null);
        frame.getContentPane().setBackground( Color.decode("#FFD1E3"));
        frame.setLocationRelativeTo(null);

       /* ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/background.jpg"));
        Image i2=i1.getImage().getScaledInstance(600, 550, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0, 0, 800, 600);
        frame.add(image);*/

        JLabel heading = new JLabel("Quiz Management System");
        heading.setBounds(0, 50, 700, 50);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setFont(new Font("Space Mono", Font.BOLD, 45));
        frame.add(heading);
        //image.add(heading);


        JLabel uname = new JLabel("Username : ");
        uname.setBounds(175, 130, 150, 50);
        uname.setFont(new Font("Space Mono",Font.PLAIN, 18));

        frame.add(uname);
        //image.add(uname);



        JTextField name = new JTextField();
        name.setBounds(175, 170, 350, 30);
        frame.add(name);

        JLabel upass = new JLabel("Password : ");
        upass.setBounds(175, 200, 150, 50);
        upass.setFont(new Font("Space Mono",Font.PLAIN, 18));

        frame.add(upass);
        //image.add(upass);


        JPasswordField pass = new JPasswordField();
        pass.setBounds(175, 240, 350, 30);
        frame.add(pass);


        /*Login section */
        JButton login = new JButton("LOGIN");
        login.setBounds(225, 300, 100, 40);
        login.setFont(new Font("Space Mono",Font.PLAIN, 15));
        login.setFocusable(false);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        frame.add(login);
       // image.add(login);




        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = name.getText();
                String password = new String(pass.getPassword());
                if(username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please Enter All Information.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    try {
                        SQLoperations manage= new SQLoperations();
                        id = manage.authUser(username, password);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (id == -1) {
                        JOptionPane.showMessageDialog(frame, "User Does Not Exist.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }
                    else if(id == 0) {
                        JOptionPane.showMessageDialog(frame, "Incorrect Password, please try again.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        mainpage mainPage = new mainpage();
                        try {
                            mainPage.mainPageView(id);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        frame.dispose();
                    }
                }
            }
        });

        /*Signup section option */
        JButton signUp = new JButton("SIGNUP");
        signUp.setBounds(375, 300, 100, 40);
        signUp.setFont(new Font("Space Mono",Font.PLAIN, 15));
        signUp.setFocusable(false);

        signUp.setBackground(Color.BLACK);
        signUp.setForeground(Color.WHITE);
        frame.add(signUp);
      //  image.add(signUp);

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup signup = new signup();
                signup.signUpView();
            }
        });

        /*Guest Quiz Option */
        JButton attend = new JButton("Complete Quiz (Guest)");
        attend.setBounds(225, 350, 250, 40);
        attend.setFont(new Font("Space Mono",Font.PLAIN, 13));
        attend.setFocusable(false);
        attend.setBackground(Color.BLACK);
        attend.setForeground(Color.WHITE);
        frame.add(attend);
        //image.add(attend);

        attend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String surveyCode = JOptionPane.showInputDialog("Please Enter the Unique Survey Code : ");
                try {
                    if(!surveyCode.isEmpty() && surveyCode.length() == 5) {
                        if(manage.check(surveyCode)) {
                            guest guest = new guest();
                            guest.guestView(surveyCode);
                        }
                        else {
                            JOptionPane.showMessageDialog(frame, "Incorrect Survey Code, please try again.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                catch(Exception e2) {

                }
            }
        });

        frame.setVisible(true);



    }
}