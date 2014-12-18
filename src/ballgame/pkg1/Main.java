package ballgame.pkg1;



import javax.swing.*;

public class Main extends JFrame {

    GamePanel panel= new GamePanel();
    
    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Nice is by tyhe cote azur");
        add( "Center", panel);
        pack();
        setVisible(true);
        
        panel.run();
    }

    public static void main(String[] args) {
        new Main();
    }

}
