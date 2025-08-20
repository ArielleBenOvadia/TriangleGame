package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class TrianglePanel extends JPanel implements MouseListener {

    private final List<Point2D> points = new ArrayList<>(3);
    private static final double EPS = 1e-6;

    public TrianglePanel() {
        setBackground(Color.WHITE);
        addMouseListener(this);
    }

    public void reset() {
        points.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // מסגרת פשוטה
        g2.setColor(Color.DARK_GRAY);
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // תצוגת מצב
        g2.setColor(new Color(0, 0, 0, 170));
        g2.drawString("Clicks: " + points.size() + "/3", 12, 20);

        // ציור נקודות
        g2.setColor(Color.BLACK);
        for (int i = 0; i < points.size(); i++) {
            Point2D p = points.get(i);
            g2.fillOval((int) p.getX() - 3, (int) p.getY() - 3, 6, 6);
            g2.drawString("P" + (i + 1), (int) p.getX() + 5, (int) p.getY() - 5);
        }

        // אם יש 3 נקודות - מציגים משולש או הודעת קולינאריות
        if (points.size() == 3) {
            Point2D A = points.get(0);
            Point2D B = points.get(1);
            Point2D C = points.get(2);

            if (areCollinear(A, B, C)) {
                g2.setColor(Color.RED.darker());
                g2.drawString("Points are collinear – not a triangle.", 12, 38);
                return; // לא מציירים ולא מחשבים
            }

            // קווים
            g2.setColor(Color.BLACK);
            g2.drawLine((int) A.getX(), (int) A.getY(), (int) B.getX(), (int) B.getY());
            g2.drawLine((int) B.getX(), (int) B.getY(), (int) C.getX(), (int) C.getY());
            g2.drawLine((int) C.getX(), (int) C.getY(), (int) A.getX(), (int) A.getY());

            // אורכי צלעות
            double c = distance(A, B); // AB (מול C)
            double a = distance(B, C); // BC (מול A)
            double b = distance(C, A); // CA (מול B)


            double per = a + b + c;
            double area = areaHeron(a, b, c);

            double angA = angleFromSides(b, c, a);
            double angB = angleFromSides(a, c, b);
            double angC = angleFromSides(a, b, c);

            g2.drawString("AB=" + (int) c, midX(A, B), midY(A, B));
            g2.drawString("BC=" + (int) a, midX(B, C), midY(B, C));
            g2.drawString("CA=" + (int) b, midX(C, A), midY(C, A));

            g2.drawString((int) Math.round(angA) + "°", (int) A.getX() + 8, (int) A.getY() - 8);
            g2.drawString((int) Math.round(angB) + "°", (int) B.getX() + 8, (int) B.getY() - 8);
            g2.drawString((int) Math.round(angC) + "°", (int) C.getX() + 8, (int) C.getY() - 8);

            g2.drawString("Perimeter=" + (int) per, 12, 38);
            g2.drawString("Area=" + (int) area, 12, 56);
        }
    }


    private static double distance(Point2D p1, Point2D p2) {
        return p1.distance(p2);
    }

    private static double areaHeron(double a, double b, double c) {
        double s = (a + b + c) / 2.0;
        double val = s * (s - a) * (s - b) * (s - c);
        return val <= 0 ? 0 : Math.sqrt(val);
    }


    private static double angleFromSides(double b, double c, double a) {
        double cosA = (b * b + c * c - a * a) / (2 * b * c);
        cosA = Math.max(-1.0, Math.min(1.0, cosA));
        return Math.toDegrees(Math.acos(cosA));
    }


    private static boolean areCollinear(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.getX() - a.getX()) * (c.getY() - a.getY())
                - (b.getY() - a.getY()) * (c.getX() - a.getX());
        return Math.abs(area2) < EPS;
    }

    private static int midX(Point2D p1, Point2D p2) { return (int) ((p1.getX() + p2.getX()) / 2); }
    private static int midY(Point2D p1, Point2D p2) { return (int) ((p1.getY() + p2.getY()) / 2); }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (points.size() < 3) {
            points.add(e.getPoint());
            repaint();
        }
    }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
