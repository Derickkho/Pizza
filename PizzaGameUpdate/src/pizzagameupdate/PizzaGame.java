import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;


class Topping {
    private int x, y; // Koordinat topping
    private Color color; // Warna topping
    public static int top = 0;

    public Topping(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 20, 20); // Menggambar topping sebagai lingkaran
    }
}

class PizzaGame extends JPanel {
    private ArrayList<Topping> toppings; // Daftar topping pada pizza

    public PizzaGame() {
        toppings = new ArrayList<>();

        JLabel l1 = new JLabel();
        l1.setBounds(500,50,300,20);
        add(l1);

        JButton b1 = new JButton("Peperoni");
        b1.setBounds(500,90,100,40);
        b1.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Topping.top = 1;
                    l1.setText("Selected Topping:Peperoni");
                }
            }
        );
        add(b1);

        JButton b2 = new JButton("Cheese");
        b2.setBounds(500,150,100,40);
        b2.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Topping.top = 2;
                    l1.setText("Selected Topping: Cheese");
                }
            }
        );
        add(b2);

        JButton b3 = new JButton("Mushroom");
        b3.setBounds(500,210,100,40);
        b3.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Topping.top = 3;
                    l1.setText("Selected Topping: Mushroom");
                }
            }
        );
        add(b3);

        // Menambahkan event listener untuk mouse
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                try{
                    if (Topping.top == 1){
                // Menambahkan topping baru dengan posisi yang diklik oleh mouse
                        toppings.add(new Topping(x, y, Color.red));
                        repaint(); // Memperbarui tampilan setelah penambahan topping
                    } else if (Topping.top == 2){
                        toppings.add(new Topping(x, y, Color.yellow));
                        repaint();
                    } else if (Topping.top == 3){
                        toppings.add(new Topping(x, y, Color.gray));
                        repaint();
                    }
                } catch (Exception ev){
                    l1.setText(ev.toString());
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Menggambar pizza sebagai lingkaran
        g.setColor(Color.darkGray);
        g.fillOval(50, 50, 300, 300);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(60, 60, 280, 280);

        // Menggambar semua topping pada pizza
        for (Topping topping : toppings) {
            topping.draw(g);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pizza Topping Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);

        PizzaGame game = new PizzaGame();
        frame.add(game);
        frame.setVisible(true);
    }
}
