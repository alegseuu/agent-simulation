import java.awt.*;

public class Drawing extends Canvas {
    static int black_holes_number = 0, stars_number = 0, planets_number = 0, moons_number = 0;
    static BlackHole[] blackHoles1;
    static Star[] stars1;
    static Planet[] planets1;
    static Moon[] moons1;
    static int black_hole_size = 20;
    static int star_size = 10;
    static int planet_size = 5;
    static int moon_size = 3;
    static Color black_hole_color = Color.DARK_GRAY;
    static Color star_color = Color.magenta;
    static Color planet_color = Color.blue;
    static Color moon_color = Color.green;

    public static void main(String[] args) {

    }

    public static void getParameters(int numBlackHoles, int numStars, int numPlanets, int numMoons,
                                     BlackHole[] blackHoles, Star[] stars, Planet[] planets, Moon[] moons){
        black_holes_number = numBlackHoles;
        stars_number = numStars;
        planets_number = numPlanets;
        moons_number = numMoons;
        blackHoles1 = blackHoles;
        stars1 = stars;
        planets1 = planets;
        moons1 = moons;
    };

    public void updateDrawing( BlackHole[] blackHoles, Star[] stars, Planet[] planets, Moon[] moons){
        repaint();
    }

    public void paint(Graphics g) {
        Drawing.getParameters(black_holes_number, stars_number, planets_number, moons_number, blackHoles1, stars1, planets1, moons1);
        //black holes printing
        g.setColor(black_hole_color);
        for (int i = 0; i < black_holes_number; i++){
            g.fillOval((int)blackHoles1[i].getX(), (int)blackHoles1[i].getY(),
                    (int) black_hole_size*blackHoles1[i].getMass()/100, (int) black_hole_size*blackHoles1[i].getMass()/100);
        }

        //stars printing
        g.setColor(star_color);
        for (int i = 0; i < stars_number; i++){
            g.fillOval((int)stars1[i].getX(), (int)stars1[i].getY(), (int) star_size*stars1[i].getMass()/100, (int) star_size*stars1[i].getMass()/100);
        }
/*
        //planet printing
        g.setColor(planet_color);
        for (int i = 0; i < planets_number; i++){
            g.fillOval(planets1[i].getX(), planets1[i].getY(), planet_size, planet_size);
        }

        //moon printing
        g.setColor(moon_color);
        for (int i = 0; i < moon_size; i++){
            g.fillOval(moons1[i].getX(), moons1[i].getY(), moon_size, moon_size);
        }

 */
    }
}
