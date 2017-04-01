package simpegamecode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

    public static boolean BlnRun = true;
    public static GameTick GTick = new GameTick();
    public long FPS;
    public long FTmr;
    Dimension Screen;
    public JFrame JF;
    private int Distance;

    public Main() {
        JF = new JFrame();
        Thread GameThread = new Thread(GTick);
        BlnRun = true;
        GameThread.start();
        Screen = Toolkit.getDefaultToolkit().getScreenSize();
        JF.addKeyListener(new KeyboardInput());
        JF.addMouseListener(new MouseInput());
        JF.addMouseMotionListener(new MouseInput());
        JF.addKeyListener(new KeyboardInput());
        JF.addMouseWheelListener(new MouseInput());

        JF.setTitle("Game");
        JF.setSize(Screen);
        JF.setExtendedState(JF.MAXIMIZED_BOTH);
        JF.setUndecorated(true);
        JF.setResizable(false);
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF.add(this);
        JF.setVisible(true);
        run();
    }

    public void run() {
        while (BlnRun) {
            repaint();
            //try {
            //    Thread.sleep(1000 / 60);
            //} catch (Exception e) {}
        }
    }

    public void paint(Graphics g) {
        FTmr = System.nanoTime();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintComponent(g2);
        g2.dispose();
        g.dispose();
        FPS = 1000000000 / (System.nanoTime() - FTmr);
    }

    public void paintComponent(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(125, 75, 125));
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.BLACK);
        g2.drawString(String.format("FPS: %d   TPS: %d", FPS, GTick.TPS), 100, 200);
        g2.dispose();
    }

    public void DrawGrid(Graphics g) {
        int X = 0, Y = 0;
        Distance = Screen.width / 36; //144, 72, 36
        while (X < Screen.width) {
            g.drawLine(X, 0, X, Screen.height);
            X += Distance;
        }
        Distance = Screen.height / 20; //80, 40, 20
        while (Y < Screen.height) {
            g.drawLine(0, Y, Screen.width, Y);
            Y += Distance;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
