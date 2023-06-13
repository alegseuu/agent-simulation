import java.util.*;

import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        // Get input from the user
        int numBlackHoles = getInput("Enter the number of BlackHoles: ");
        int numStars = getInput("Enter the number of Stars: ");
        int numPlanets = getInput("Enter the number of Planets: ");
        int numMoons = getInput("Enter the number of Moons: ");
        int numIterations = getInput("Enter the number of iterations: "); //czemu wlasciwie razy 100?
        */
        int numBlackHoles = 10;
        int numStars = 10;
        int numPlanets = 10;
        int numMoons = 10;
        int numIterations = 20;

        // Create the objects
        BlackHole[] blackHoles = createBlackHoles(numBlackHoles);
        Star[] stars = createStars(numStars);
        Planet[] planets = createPlanets(numPlanets);
        Moon[] moons = createMoons(numMoons);

        Canvas canvas = printSimulationState(numIterations, numBlackHoles, numStars, numPlanets,  numMoons, blackHoles, stars, planets, moons);

        // Perform simulation iterations
        for (int i = 1; i <= numIterations; i++) {
            // Print the current state of the simulation
            updateSimulationState((Drawing) canvas, blackHoles, stars, planets, moons);
            try {
                Thread.sleep(1000); // Add a delay between iterations for visualization
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Update positions and velocities of objects
            updatePositionsAndVelocities(i, blackHoles, stars, planets, moons);
        }
    }

    private static Drawing printSimulationState(int iteration, int numBH, int numStars, int numPlanets, int numMoons,
                                             BlackHole[] blackHoles, Star[] stars, Planet[] planets, Moon[] moons) {
        JFrame frame = new JFrame("Kosmos");
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

        //simulation parameters
    private static final int maxX = 1500; //wywalam w jedno miejsce
    private static final int maxY = 800;
    private static final int maxBHMass = 100;
    private static final int maxBHAge = 100;
    private static final int maxStarMass = 100;
    private static final int maxStarAge = 100;
    private static final int maxLifeExpectancy = 1000;
    private static final int maxPlanetMass = 100;
    private static final int maxPlanetAge = 100;
    private static final int maxMoonMass = 100;
    private static final int maxMoonAge = 100;

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

    private static void updatePositionsAndVelocities(int iteration, BlackHole[] blackHoles, Star[] stars, Planet[] planets, Moon[] moons) {
        /*
        // Update positions and velocities of stars
        for (Star star : stars){
            int i = 0;
            double[][] Forces = new double[numBlackholes][2];
            for(BlackHole blackHole : blackHoles){
                StarAttraction star_attraction = new StarAttraction();
                star_attraction.calculateDistance(star.getPosition(),blackHole.getPosition());
                int[] dir = new int[2];
                dir = star_attraction.getDirection(star.getPosition(),blackHole.getPosition());
                Forces[i] = star_attraction.calculateForce(dir, star_attraction.getDistance(),star.getMass(),blackHole.getMass());
            }
            double[][] netForce = new double[numStars][2];
            netForce[i] = star.calculateNetForce(Forces);
            double[][] acceleration = new double[numStars][2];
            acceleration[i] = star.calculateAcceleration(netForce[i]);
            star.setVelocity(star.calculateVelocity(acceleration[i]));
            star.setVelocity(1,1);
            star.nextPosition();


        }}}
*/

            // Update positions and velocities of black holes
        for (BlackHole blackHole : blackHoles) {
            blackHole.setX(blackHole.getX()+iteration);
            blackHole.setY(blackHole.getY()+iteration);}}}

/*
        // Update positions and velocities of stars
        for (Star star : stars) {
            double[] netForce = {0.0, 0.0};
            for (BlackHole blackHole : blackHoles) {
                double[] attractionForce = calculateForce(blackHole.getPosition(), blackHole.getMass(), star.getMass(),
                        calculateDistance(blackHole.getPosition(), star.getPosition()));
                netForce[0] += attractionForce[0];
                netForce[1] += attractionForce[1];
            }
            star.setVelocity(calculateVelocity(netForce));
            star.setPosition(star.nextPosition());
        }

        // Update positions and velocities of planets
        for (Planet planet : planets) {
            double[] force = {0.0, 0.0};
            for (Star star : stars) {
                double[] attractionForce = planet.calculateForce(star.getPosition(), star.getMass(), planet.getMass(), planet.getDistance());
                force[0] += attractionForce[0];
                force[1] += attractionForce[1];
            }
            planet.setForce(force);
            planet.updateVelocity();
            planet.updatePosition();
        }

        // Update positions and velocities of moons
        for (Moon moon : moons) {
            double[] force = {0.0, 0.0};
            for (Planet planet : planets) {
                double[] attractionForce = moon.calculateForce(planet.getPosition(), planet.getMass(), moon.getMass(), moon.getDistance());
                force[0] += attractionForce[0];
                force[1] += attractionForce[1];
            }
            moon.setForce(force);
            moon.updateVelocity();
            moon.updatePosition();
        }
    }


    private static int getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextInt();
    }

}
*/