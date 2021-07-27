package com.ying;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Main {
    JFrame frame = new JFrame("Drawing");


    int AREA_WIDTH = 1000;
    int AREA_HEIGHT = 1000;

    Color foreColor = Color.BLACK;
    int preX = -1;
    int preY = -1;
    JPopupMenu popupMenu = new JPopupMenu("Color");
    JMenuItem redItem = new JMenuItem("Red");
    JMenuItem greenItem = new JMenuItem("Green");
    JMenuItem blueItem = new JMenuItem("Blue");

    BufferedImage image = new BufferedImage(AREA_WIDTH,AREA_HEIGHT,BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();

public class MyCanvas extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(image,0,0,null);
        }
    }


    MyCanvas drawArea = new MyCanvas();
    Main(){

        ActionListener listener = e -> {
            switch (e.getActionCommand()) {
                case "Red" -> foreColor = Color.RED;
                case "Green" -> foreColor = Color.GREEN;
                case "Blue" -> foreColor = Color.BLUE;
            }
        };


        redItem.addActionListener(listener);
        greenItem.addActionListener(listener);
        blueItem.addActionListener(listener);

        popupMenu.add(redItem);
        popupMenu.add(greenItem);
        popupMenu.add(blueItem);
        drawArea.add(popupMenu);

        drawArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 3){
                    popupMenu.show(drawArea,e.getX(),e.getY());
                    popupMenu.setVisible(true);
                }
                preX = -1;
                preY = -1;
            }
        });

        g.setColor(Color.WHITE);
        g.fillRect(0,0,AREA_WIDTH,AREA_HEIGHT);

        drawArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (preX >= 0 && preY >= 0){
                    g.setColor(foreColor);
                    g.drawLine(preX,preY, e.getX(),e.getY());

                }
                preX = e.getX();
                preY = e.getY();
                drawArea.repaint();
            }
        });
        drawArea.setPreferredSize(new Dimension(AREA_WIDTH,AREA_HEIGHT));


        frame.add(drawArea);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }









    public static void main(String[] args) {
       new Main();

    }
}
