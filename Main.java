import java.util.*;

import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    //simulation parameters
    private static final int maxX = 1500; //wywalam w jedno miejsce
    private static final int maxY = 800;
    private static final int maxBHMass = 100;
    private static final int maxBHAge = 1000;
    private static final int maxStarMass = 100;
    private static final int maxStarAge = 100;
    private static final int maxLifeExpectancy = 1000;
    private static final int maxPlanetMass = 100;
    private static final int maxPlanetAge = 100;
    private static final int maxMoonMass = 100;
    private static final int maxMoonAge = 100;
    public static void main(String[] args) {
        /*
        // Get input from the user
        int numBlackHoles = getInput("Enter the number of BlackHoles: ");
        int numStars = getInput("Enter the number of Stars: ");
        int numPlanets = getInput("Enter the number of Planets: ");
        int numMoons = getInput("Enter the number of Moons: ");
        int numIterations = getInput("Enter the number of iterations: "); //czemu wlasciwie razy 100?
        */
        int numBlackHoles = 2;
        int numStars = 1;
        int numPlanets = 0;
        int numMoons = 0;
        int numIterations = 10000;
        long sleep_time = 10;

        // Create the objects
        BlackHole[] blackHoles = createBlackHoles(numBlackHoles);
        Star[] stars = createStars(numStars);
        Planet[] planets = createPlanets(numPlanets);
        Moon[] moons = createMoons(numMoons);

        Canvas canvas = printSimulationState(numIterations, numBlackHoles, numStars, numPlanets, numMoons, blackHoles, stars, planets, moons);

        // Perform simulation iterations
        for (int i = 1; i <= numIterations; i++) {
            // Print the current state of the simulation
            updateSimulationState((Drawing) canvas, blackHoles, stars, planets, moons);
            try {
                Thread.sleep(sleep_time); // Add a delay between iterations for visualization
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Update positions and velocities of objects
            updatePositionsAndVelocities(i, blackHoles, stars, planets, moons, numBlackHoles);
        }
    }


    private static void updatePositionsAndVelocities(int iteration, BlackHole[] blackHoles, Star[] stars, Planet[] planets,
                                                     Moon[] moons, int numBHoles) {
        // Update positions and velocities of black holes
        for (Star star : stars) {
            StarAttraction star_attract = new StarAttraction();
            double[] direction = new double[2];
            double[][] Forces = new double[numBHoles][2];
            for(int i = 0; i < numBHoles; i++){
                star_attract.calculateDistance(star,blackHoles[i]);
                direction = star_attract.getDirection(star,blackHoles[i]);
                star_attract.calculateForce(star.getMass(), blackHoles[i].getMass());
                Forces[i][0] = star_attract.calculateForceX();
                Forces[i][1] = star_attract.calculateForceY();
                //
            }
            star.calculateNetForce(Forces, numBHoles);
            star.calculateAcceleration();
            star.calculateVelocity();
            star.nextPosition(direction);
        }
    }


    private static Drawing printSimulationState(int iteration, int numBH, int numStars, int numPlanets, int numMoons,
                                             BlackHole[] blackHoles, Star[] stars, Planet[] planets, Moon[] moons) {
        JFrame frame = new JFrame("Kosmos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Drawing();
        canvas.setSize(1500, 800);
        canvas.setBackground(Color.black);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        ((Drawing) canvas).getParameters(numBH, numStars, numPlanets, numMoons, blackHoles, stars, planets, moons);
        //updateSimulationState((Drawing) canvas);
        return (Drawing) canvas;
    }

    private static void updateSimulationState(Drawing canvas, BlackHole[] blackHoles, Star[] stars, Planet[] planets, Moon[] moons){
        canvas.updateDrawing(blackHoles, stars, planets, moons);
    }

    private static BlackHole[] createBlackHoles(int numBlackHoles) {
        BlackHole[] blackHoles = new BlackHole[numBlackHoles];
        Random random = new Random();

        for (int i = 0; i < numBlackHoles; i++) {
            int[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            int mass = random.nextInt(maxBHMass);
            int age = random.nextInt(maxBHAge);
            String name = "black_hole1" + Integer.toString(i);
            blackHoles[i] = new BlackHole(mass, age, name, position);
        }

        return blackHoles;
    }

    private static Star[] createStars(int numStars) {
        Star[] stars = new Star[numStars];
        Random random = new Random();

        for (int i = 0; i < numStars; i++) {
            int mass = random.nextInt(maxStarMass);
            int age = random.nextInt(maxStarAge);
            int lifeExpectancy = random.nextInt(maxLifeExpectancy);
            double[] velocity = {0.0, 0.0};
            int[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            String name = "Star" + Integer.toString(i);
            stars[i] = new Star(mass, age, lifeExpectancy, velocity, position, name);
        }

        return stars;
    }

    private static Planet[] createPlanets(int numPlanets) {
        Planet[] planets = new Planet[numPlanets];
        Random random = new Random();

        for (int i = 0; i < numPlanets; i++) {
            int mass = random.nextInt(maxPlanetMass);
            int age = random.nextInt(maxPlanetAge);
            String name = "Planet" + Integer.toString(i);
            double[] velocity = {0.0, 0.0};
            int[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            planets[i] = new Planet(mass, age, name, velocity, position);
        }

        return planets;
    }

    private static Moon[] createMoons(int numMoons) {
        Moon[] moons = new Moon[numMoons];
        Random random = new Random();

        for (int i = 0; i < numMoons; i++) {
            int mass = random.nextInt(maxMoonMass);
            int age = random.nextInt(maxMoonAge);
            String name = "Moon" + Integer.toString(i);
            double[] velocity = {0.0, 0.0};
            int[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            moons[i] = new Moon(mass, age, name, velocity, position);
        }

        return moons;
    }




    private static int getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextInt();
    }

}
