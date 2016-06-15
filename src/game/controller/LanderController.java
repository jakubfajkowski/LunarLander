package game.controller;

import game.model.Lander;
import game.model.Point;
import game.model.Vector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanderController{
    private ActionListener listener;
    private Lander lander;

    public LanderController(Lander lander, ActionListener listener){
        this.lander = lander;
        this.listener = listener;
    }

    public void land(){
        lander.setLanded(true);
        listener.actionPerformed(new ActionEvent(this, 0, "land"));
    }

    public void crash(){
        lander.setCrashed(true);
        listener.actionPerformed(new ActionEvent(this, 0, "crash"));
    }

    public void freeFall(double gravitationalAcceleration) {
        Vector v = new Vector(0,-gravitationalAcceleration / 1000);
        lander.getVelocityVector().add(v);
    }

    public void move(Vector vector) {
        if(burnFuel()){
            triggerBoosters(vector);
            lander.getVelocityVector().add(vector);
        }
    }

    private void triggerBoosters(Vector vector){
        if (vector.getY() > 0){
            lander.setLeftBoosterWorking(true);
            lander.setRightBoosterWorking(true);
        }

        if (vector.getX() > 0){
            lander.setLeftBoosterWorking(true);
        }

        if (vector.getX() < 0){
            lander.setRightBoosterWorking(true);
        }
    }

    public boolean burnFuel() {
        if(lander.getFuel() > 0){
            lander.setFuel(lander.getFuel() - 0.5);
            return true;
        }
        else
            return false;
    }

    public void updatePosition(double viscosity) {
        Point currentLanderPosition = lander.getPosition();

        slowDown(viscosity);

        currentLanderPosition.x += lander.getVelocityVector().getX();
        currentLanderPosition.y += lander.getVelocityVector().getY();

        lander.setPosition(currentLanderPosition);
    }

    public void slowDown(double viscosity) {
        Vector currentLanderVelocityVector = lander.getVelocityVector();

        double x = currentLanderVelocityVector.getX() * viscosity / 1000;
        double y = currentLanderVelocityVector.getY() * viscosity / 1000;

        currentLanderVelocityVector.add(new Vector(-x,-y));

        lander.setVelocityVector(currentLanderVelocityVector);
    }
}
