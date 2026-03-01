import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;
public class Main extends JFrame  implements ActionListener
{
    JButton Calculate;
    JTextField x1f,y1f,x2f,y2f;
    JLabel lx1,ly1,lx2,ly2,Result,calc;
    public  Main()
    {
        this.setTitle("SLOPE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(370,250);
        this.setVisible(true);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(250,250,250));

        this.setLayout(null);
        //x1
        lx1=new JLabel("X1");
        x1f=new JTextField();
        add(lx1);
        add(x1f);
        x1f.setBackground(Color.blue);
        x1f.setForeground(Color.white);
        lx1.setForeground(Color.BLACK);
        //y1
        ly1=new JLabel("Y1");
        y1f=new JTextField();
        add(y1f);
        add(ly1);
        y1f.setBackground(Color.BLUE);
        y1f.setForeground(Color.WHITE);
        ly1.setForeground(Color.BLACK);
        //x2
        lx2=new JLabel("X2");
        x2f=new JTextField();
        add(x2f);
        add(lx2);
        x2f.setBackground(Color.BLUE);
        x2f.setForeground(Color.WHITE);
        lx2.setForeground(Color.BLACK);
        //y2
        ly2=new JLabel("Y2");
        y2f=new JTextField();
        add(y2f);
        add(ly2);
        y2f.setBackground(Color.BLUE);
        y2f.setForeground(Color.WHITE);
        ly2.setForeground(Color.BLACK);

        //calculate
        Calculate=new JButton("Calculate");
        add(Calculate);
        Calculate.setBackground(new Color(210,180,140));
        Calculate.setForeground(Color.BLACK);
        calc=new JLabel("Calculate");
        add(calc);
        Result=new JLabel("Result: ");
        add(Result);
        Result.setForeground(new Color(210,180,140));


        lx1.setBounds(5,0,20,50);
        x1f.setBounds(30,10,60,30);
        ly1.setBounds(5,50,20,50);
        y1f.setBounds(30,60,60,30);
        lx2.setBounds(100,0,20,50);
        x2f.setBounds(130,10,60,30);
        ly2.setBounds(100,50,20,50);
        y2f.setBounds(130,60,60,30);
        Calculate.setBounds(0,130,100,30);
        //Calculate.setBounds(60,130,100,30);
        Result.setBounds(180,130,160,30);
        Calculate.addActionListener(this);





    }
    public void actionPerformed(ActionEvent arg0)// هنا بتدي اوامر عشان ينفذ الفعل ده
    {
        calculateSlope();


    }
    private void calculateSlope(){
        try {


            double x1 = Double.parseDouble(x1f.getText());
            double y1 = Double.parseDouble(y1f.getText());
            double x2 = Double.parseDouble(x2f.getText());
            double y2 = Double.parseDouble(y2f.getText());


            if (x1 == x2) {
                Result.setText("Result: Undefined ");
                return;

            }
            if (y1 == y2) {
                Result.setText("Result: 0 ");
                return;

            }
            double slope = (y1 - y2) / (x1 - x2);
            Result.setText("Result: " + slope);
        }
        catch (NumberFormatException e){
            Result.setText("Please enter valid number");
        }



    }
    public static void main(String[] args)
    {

        new Main();



    }
}