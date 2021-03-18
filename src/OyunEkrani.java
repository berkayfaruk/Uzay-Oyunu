
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.JFrame;


public class OyunEkrani extends JFrame
{

    public OyunEkrani(String title) throws HeadlessException
    {
        super(title);
    }
    
    public static void main(String[] args) throws IOException
    {
            OyunEkrani ekran=new OyunEkrani("Uzay Oyunu");
            
            ekran.setResizable(false);
            
            ekran.setFocusable(false);//jrame e değil jpanele odaklan diyoruz
            ekran.setSize(800,600);
            
            ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            Oyun oyun=new Oyun();
            
            oyun.requestFocus();//klavyeden işlemleri anlamak için bana odağı ver
            
            oyun.addKeyListener(oyun);
            
            oyun.setFocusable(true);
            
            oyun.setFocusTraversalKeysEnabled(true);// klavye işlemlerini anlaması için 
            ekran.add(oyun);//jpaneli jframe' e ekle
            ekran.setVisible(true);
            
            
    }
    
}
