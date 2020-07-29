import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener,ActionListener {
    private int[] snakexlength= new int[750];
    private int[] snakeylength= new int[750];

    private boolean right= false;
    private boolean left= false;
    private boolean down= false;
    private boolean up= false;

   private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon snakeimage;

    private int[]enemyxpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[]enemyypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private ImageIcon enemyimage;
    private Random random= new Random();
    private int xpos= random.nextInt(34);
    private int ypos= random.nextInt(23);

    private Timer timer;
    private int delay=200;
    private int lengthofsnake=3;
    private int moves=0;
    private int scores=0;

    private ImageIcon icon;

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer= new Timer(delay,this);
        timer.start();
    }
    public static void main(String[] arguments) {
        new Gameplay();
    }
    public void paint(Graphics g){
        if(moves==0){
            snakexlength[0]=100;
            snakexlength[1]=75;
            snakexlength[2]=50;

            snakeylength[0]=100;
            snakeylength[1]=100;
            snakeylength[2]=100;
        }

        // BORDER of title box
        g.setColor(Color.CYAN);
        icon= new ImageIcon("snaketitle.jpg");
        icon.paintIcon(this, g,25,11);
        // BORDER of gameplay
        g.setColor(Color.CYAN);
        g.drawRect(24,74,851,577);
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Scores : "+scores,780,30);
        g.drawString("Length : "+lengthofsnake,780,50);

         rightmouth= new ImageIcon("rightmouth.png");
         rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
         for(int a=0;a<lengthofsnake;a++) {
             if(a==0 && right){
                 rightmouth= new ImageIcon("rightmouth.png");
                 rightmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
             }
             if(a==0 && left) {
                 leftmouth = new ImageIcon("leftmouth.png");
                 leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
             }
             if(a==0 && up) {
                 upmouth = new ImageIcon("upmouth.png");
                 upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
             }
             if(a==0 && down) {
                 downmouth = new ImageIcon("downmouth.png");
                 downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
             }
             if(a!=0) {
                 snakeimage = new ImageIcon("snakeimage.png");
                 snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
                 if(enemyxpos[xpos]== snakexlength[0] && enemyypos[ypos]== snakeylength[0]){
                     lengthofsnake++;
                     scores++;
                     xpos= random.nextInt(34);
                     ypos= random.nextInt(23);
                 }
             }
         }
         enemyimage= new ImageIcon("enemy.png");
         enemyimage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);
         for(int i=1;i<lengthofsnake;i++){
             if(snakexlength[i]== snakexlength[0] && snakeylength[i]==snakeylength[0])
             {
                 right= false;
                 left= false;
                 up= false;
                 down= false;
                 g.setColor(Color.WHITE);
                 g.setFont(new Font("arial",Font.BOLD,50));
                 g.drawString("GAME OVER!", 300,300);
                 g.setFont(new Font("arial",Font.BOLD,20));
                 g.drawString("Space to RESTART", 350,340);
             }
         }
         g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(right){
            for(int i=lengthofsnake-1;i>=0;i--){
                snakeylength[i+1]= snakeylength[i];
            }
            for(int j=lengthofsnake;j>=0;j--){
                if(j==0){
                    snakexlength[j]=snakexlength[j]+25;
                }
                else{
                    snakexlength[j]=snakexlength[j-1];
                }
                if(snakexlength[j]>850)
                    snakexlength[j]=25;
            }
           repaint();
        }
        if(left){
            for(int i=lengthofsnake-1;i>=0;i--){
                snakeylength[i+1]= snakeylength[i];
            }
            for(int j=lengthofsnake;j>=0;j--){
                if(j==0){
                    snakexlength[j]=snakexlength[j]-25;
                }
                else{
                    snakexlength[j]=snakexlength[j-1];
                }
                if(snakexlength[j]<25)
                    snakexlength[j]=850;
            }
            repaint();
        }
        if(up){
            for(int i=lengthofsnake-1;i>=0;i--){
                snakexlength[i+1]= snakexlength[i];
            }
            for(int j=lengthofsnake;j>=0;j--){
                if(j==0){
                    snakeylength[j]=snakeylength[j]-25;
                }
                else{
                    snakeylength[j]=snakeylength[j-1];
                }
                if(snakeylength[j]<75)
                    snakeylength[j]=625;
            }
            repaint();
        }
        if(down){
            for(int i=lengthofsnake-1;i>=0;i--){
                snakexlength[i+1]= snakexlength[i];
            }
            for(int j=lengthofsnake;j>=0;j--){
                if(j==0){
                    snakeylength[j]=snakeylength[j]+25;
                }
                else{
                    snakeylength[j]=snakeylength[j-1];
                }
                if(snakeylength[j]>625)
                    snakeylength[j]=75;
            }
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_LEFT && !right) {
            moves++;
            left = true;
            up = false;
            down = false;
        }
        if ( e.getKeyCode()== KeyEvent.VK_RIGHT && !left) {
            moves++;
            right = true;
            up = false;
            down = false;
        }

        if ( e.getKeyCode()== KeyEvent.VK_UP && !down) {
            moves++;
            left = false;
            up = true;
            right = false;
        }

        if ( e.getKeyCode()== KeyEvent.VK_DOWN && !up) {
            moves++;
            left = false;
            down = true;
            right = false;
        }
        if(e.getKeyCode()== KeyEvent.VK_SPACE){
            scores=0;
            moves=0;
            lengthofsnake=3;
            repaint();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

