package Classroom;

import java.awt .*;
import java.awt.event .*;

public class MouseEventDemo extends Frame implements MouseListener, MouseMotionListener {

    String msg = "";
    int mouseX = 0, mouseY = 0; // coordinates of mouse

    public MouseEventDemo() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(new MyWindowAdapter2());

    }

    // Handle mouse clicked.
    public void mouseClicked(MouseEvent me) {
        msg = msg + "-- click received";
        repaint();
    }

    // Handle mouse entered.
    public void mouseEntered(MouseEvent me) {
        mouseX = 100;
        mouseY = 100;
        msg = "Mouse entered. ";
        repaint();
    }

    // Handle mouse exited.
    public void mouseExited(MouseEvent me) {
        mouseX = 100;
        mouseY = 100;
        msg = "Mouse exited.";
        repaint();

    }

    // Handle button pressed.
    public void mousePressed(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Button down";
        repaint();

    }

    // Handle button released.
    public void mouseReleased(MouseEvent me) {
// save coordinates
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Button Released";
        repaint();
    }

    // Handle mouse dragged.
    public void mouseDragged(MouseEvent me) {
// save coordinates
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "*" + " mouse at " + mouseX + ", " + mouseY;
        repaint();
    }

    // Handle mouse moved.
    public void mouseMoved(MouseEvent me) {
        msg = "Moving mouse at " + me.getX() + ", " + me.getY();
        repaint();
    }
// Display msg in the window at current X, Y location.
        public void paint (Graphics g){
            g.drawString(msg, mouseX, mouseY);
        }

        public static void main (String[]args){
            MouseEventDemo appwin = new MouseEventDemo();

            appwin.setSize(new Dimension(300, 300));
            appwin.setTitle("Classroom.MouseEventDemo");
            appwin.setVisible(true);

        }

    }

    // When the close box in the frame is clicked,
// close the window and exit the program.
    class MyWindowAdapter2 extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);

        }

    }

