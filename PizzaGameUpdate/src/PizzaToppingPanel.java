import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PizzaToppingPanel extends JPanel {
    
    private Image pizzaImage;
    private int toppingX;
    private int toppingY;
    
    public PizzaToppingPanel() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        
        // Memuat gambar pizza
        pizzaImage = new ImageIcon(getClass().getResource("pizza.png")).getImage();
        
        // Inisialisasi posisi topping
        toppingX = 0;
        toppingY = 0;
        
        // Menambahkan event listener untuk mouse click
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Mendapatkan posisi klik mouse
                toppingX = e.getX();
                toppingY = e.getY();
                
                // Menggambar ulang panel
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Menggambar pizza
        g.drawImage(pizzaImage, 0, 0, null);
        
        // Menggambar topping pada posisi yang telah ditentukan
        int toppingSize = 20;
        g.setColor(Color.RED);
        g.fillOval(toppingX - toppingSize/2, toppingY - toppingSize/2, toppingSize, toppingSize);
    }
}
