package game.controller;

import game.model.*;
import game.model.Point;
import game.model.Vector;
import game.model.environment.Map;
import game.view.GameWindowRenderer;
import game.view.MapRenderer;
import game.view.StatsRenderer;
import gui.Language;
import gui.MainWindow;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;

import static javax.swing.SwingUtilities.invokeLater;

public class GameWindowController extends JPanel implements ActionListener {
    private static final String PRESSED = "Pressed";
    private static final String RELEASED = "Released";
    private static final String PAUSE = "Pause";

    private GameWindow gameWindow;
    private LanderController landerController;
    private GameWindowRenderer gameWindowRenderer;
    private Timer timer;
    private HashMap<Direction, Boolean> directionMap = new HashMap<>();

    public GameWindowController(String configurationFileName, Player player){
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.gameWindow = new GameWindow(configurationFileName, player);
        this.landerController = new LanderController(gameWindow.getLander(), this);
        this.gameWindowRenderer = new GameWindowRenderer(gameWindow,
                new MapRenderer(gameWindow.getMap()),
                new StatsRenderer(gameWindow.getStats()));

        this.timer = new Timer(10, new TimerListener());

        for (Direction direction : Direction.values()) {
            directionMap.put(direction, false);
        }
        bindKeys();


    }

    private void bindKeys(){
        int context = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(context);
        ActionMap actionMap = getActionMap();

        for (Direction direction : Direction.values()) {
            inputMap.put(KeyStroke.getKeyStroke(direction.getKeyCode(), 0, false),
                    direction.getName() + PRESSED);
            inputMap.put(KeyStroke.getKeyStroke(direction.getKeyCode(), 0, true),
                    direction.getName() + RELEASED);

            actionMap.put(direction.getName() + PRESSED, new MoveAction(true, direction));
            actionMap.put(direction.getName() + RELEASED, new MoveAction(false, direction));
        }

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), PAUSE);
        actionMap.put(PAUSE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause();
            }
        });
    }

    enum Direction {
        UP("Up", KeyEvent.VK_W, new Vector(0, 0.03)),
        LEFT("Left", KeyEvent.VK_A, new Vector(-0.03, 0)),
        RIGHT("Right", KeyEvent.VK_D, new Vector(0.03, 0));

        private String name;
        private int keyCode;
        private Vector vector;

        Direction(String name, int keyCode, Vector vector) {
            this.name = name;
            this.keyCode = keyCode;
            this.vector = vector;
        }
        public String getName() {
            return name;
        }
        public int getKeyCode() {
            return keyCode;
        }
        public Vector getVector() {
            return vector;
        }
    }

    private class MoveAction extends AbstractAction {
        private Boolean pressed;
        private Direction direction;
        public MoveAction(boolean pressed, Direction direction) {
            this.pressed = pressed;
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            directionMap.put(direction, pressed);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // normowanie punktów
        double xScale = (double) MainWindow.getInstance().getWidth() / GameWindow.WIDTH;
        double yScale = (double) MainWindow.getInstance().getHeight() / GameWindow.HEIGHT;
        // skalowanie komponentów
        ((Graphics2D) g).scale(xScale, yScale);

        gameWindowRenderer.render(g);
    }

    public void updateStats(){
        Stats stats = gameWindow.getStats();
        Lander lander = gameWindow.getLander();
        Map map = gameWindow.getMap();

        stats.setFuel(lander.getFuel());
        stats.setHeight(lander.getPosition().y  - map.getLandingSpot().getHeight() - 15);
        stats.setHorizontalVelocity(lander.getVelocityVector().getX()*100);
        stats.setVerticalVelocity(lander.getVelocityVector().getY()*100);
        stats.setScore(stats.getScore() - 5);
        stats.setLevelName(gameWindow.getConfigurationFileName());
        stats.setLives(gameWindow.getPlayer().getLivesLeft());
    }

    private void pause(){
        if(timer.isRunning()){
            stopGame();
        }
        else{
            startGame();
        }
    }

    public void startGame(){
        timer.start();
    }

    public void stopGame(){
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){
            case "crash":
                stopGame();
                forceRepaint();
                gameWindow.getPlayer().loseLife();

                JOptionPane.showMessageDialog(MainWindow.getInstance(), "You've lost a life.", "Crashed", JOptionPane.WARNING_MESSAGE);

                if(gameWindow.getPlayer().getLivesLeft() == 0){

                    Object[] options = {"Tak", "Nie"};
                    int decision = JOptionPane.showOptionDialog(MainWindow.getInstance(), "Czy chcesz spróbować ponownie?", "Gra przegrana", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

                    if(decision == 0) {
                        MainWindow.getInstance().retry();
                    }
                    else {
                        MainWindow.getInstance().actionPerformed(new ActionEvent(this, 0, Language.MAIN_MENU));
                    }
                }
                else{
                    retry();
                }
                break;

            case "land":
                stopGame();
                forceRepaint();
                gameWindow.setScore(gameWindow.getScore() + 10000);
                gameWindow.getPlayer().appendScore(gameWindow.getScore());
                JOptionPane.showMessageDialog(MainWindow.getInstance(), "You've landed safely.", "Landed", JOptionPane.PLAIN_MESSAGE);
                MainWindow.getInstance().nextLevel();
                break;
        }
    }

    private void forceRepaint(){
        repaint();
        invokeLater(new Runnable() {
            @Override
            public void run() {
                    repaint();
            }
        });
    }

    private void retry() {
        this.gameWindow = new GameWindow(gameWindow.getConfigurationFileName(), gameWindow.getPlayer());
        this.landerController = new LanderController(gameWindow.getLander(), this);
        this.gameWindowRenderer = new GameWindowRenderer(gameWindow,
                new MapRenderer(gameWindow.getMap()),
                new StatsRenderer(gameWindow.getStats()));

        startGame();
    }

    private void checkCollisions(){
        Point[] points = gameWindow.getLander().returnCornersOfLander();

        for (Point point : points) {
            if(checkCollisionWithGround(point) || checkCollisionWithLandingSpot(point)) break;
        }
    }

    private boolean checkCollisionWithGround(Point point){
        if(gameWindow.getMap().collisionWithGround((int)point.getX(),(int)(GameWindow.HEIGHT - point.getY())) || point.y<0)
        {
            landerController.crash();
            return true;
        }
        return false;
    }

    private boolean checkCollisionWithLandingSpot(Point point){
        if (gameWindow.getMap().getLandingSpot().isOn((int) point.getX(), (int) (GameWindow.HEIGHT - point.getY())))
        {
            if(Math.abs(gameWindow.getStats().getHorizontalVelocity()) < gameWindow.getMap().getMaxHorizontalVelocity()
                        && Math.abs(gameWindow.getStats().getVerticalVelocity()) < gameWindow.getMap().getMaxVerticalVelocity())
            {
                landerController.land();
                return true;
            }
            else{
                landerController.crash();
                return true;
            }
        }
        return false;
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(gameWindow.getScore() > 0) gameWindow.setScore(gameWindow.getScore() - 5);
            landerController.freeFall(gameWindow.getMap().getGravitationalAcceleration());

            for (Direction direction : Direction.values()) {
                if (directionMap.get(direction)) {
                    landerController.move(direction.getVector());
                }
            }
            landerController.updatePosition(gameWindow.getMap().getViscosity());
            checkCollisions();
            updateStats();

            repaint();
        }
    }
}
