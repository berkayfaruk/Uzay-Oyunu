
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//toplarmızı ve mekiğimizi hareket etttirmemiz için
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;//klavye işlemlerimizi anlamamız için
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Oyun extends JPanel implements KeyListener,ActionListener
{
    Timer timer=new Timer(5, this);
    private int gecenSure=0;
    private int harcananAtes=0;
    private BufferedImage resim;
    
    private ArrayList<Ates> atesler=new ArrayList<Ates>();
    
    private int atesdirY=1;//bizim ateşlerimiz belli bir yerden oluşacak daha sonra bu atesdirY yi biz her actionPerformed den sonra o atesdirY koordinatına ekleyecez ve ateşlerimiz hareket etmiş olacak
    
    private int topX=0;//topun sağa ve sola gitmesini ayarlayacaz.Yani actionPerformed olduğunda (yani her 5 ms de)bir bu topX değişecek ve biz topu bu şeklide hareket ettirmisiz olacaz
    
    private int topDirX=2; //ben bu topDirX i sürekli topX e ekleyecez ve daha sonra topXimiz belli bir limite geldiği zaman tekra sağa veya sola kayacak
    
    private int uzayGemisiX=0;//Uzay gemisinin nerden başkalayacağı(tam alt sol yani x =0)
    
    private int dirUzayX=20;//mesala ben klavyeden sağa bastığım zaman uzayGemisiX'e dirUzayX'i ekliyecem ve bu sekilde 20 br sağa kaymış olacak veya sola kaymış olacak 
    
    public boolean kontrolEt()
    {
        for (Ates ates : atesler)
        {
            if (new Rectangle(ates.getX(), ates.getY(), 10, 20).intersects(new Rectangle(topX, 0, 20, 20)))
            {
                return true;
            }
        }
        return false;
    }
    //Constructor
    public Oyun() throws IOException
    {
        resim=ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        setBackground(Color.black);
        timer.start();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g); 
        
        gecenSure+=5;
        
        g.setColor(Color.red);
        g.fillOval(topX, 0, 20, 20);
        g.drawImage(resim, uzayGemisiX,490,resim.getWidth()/10,resim.getHeight()/10,this);
        
        for (Ates ates : atesler)
        {
            if (ates.getY()<0)
            {
                atesler.remove(ates);
            }
        }
        g.setColor(Color.blue);
        
        for (Ates ates : atesler)
        {
            g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        if (kontrolEt())
        {
            timer.stop();
            String message="Kazandınız";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0 );
        } 
    }

    @Override//kendi içinde java 
    public void repaint()
    {
        super.repaint(); 
    }
    

    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int gelen=e.getKeyCode();
        if (gelen==KeyEvent.VK_LEFT)
        {
            if (uzayGemisiX<=0)
            {
                uzayGemisiX=0;
            }
            else
            {
                uzayGemisiX-=dirUzayX;
            }
        }
        else if (gelen==KeyEvent.VK_RIGHT)
        {
             if (uzayGemisiX>=730)
            {
                uzayGemisiX=730;
            }
             else
             {
                 uzayGemisiX+=dirUzayX;
             }
        }
        else if (gelen==KeyEvent.VK_SPACE)
        {
            atesler.add(new Ates(uzayGemisiX+15, 490));
            harcananAtes++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (Ates ates : atesler)
        {
            ates.setY(ates.getY()-atesdirY);
            
        }
        
        
       topX=topX+topDirX;
        if (topX>=750)
        {
            topDirX=(-1)*topDirX;
        }
        if (topX<=0)
        {
            topDirX=(-1)*topDirX;
        }
        repaint();
        
    }
    
}
