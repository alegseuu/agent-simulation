import java.util.*;
import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    //simulation parameters
    private static final int maxX = 1500;
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
    private static final int starMinDistance = 10;
    private static final int planetMinDistance = 10;
    private static final int moonMinDistance = 10;


    public static void main(String[] args) {
        
        // Get input from the user
        int numBlackHoles = getInput("Enter the number of BlackHoles: ");
        int numStars = getInput("Enter the number of Stars: ");
        int numPlanets = getInput("Enter the number of Planets: ");
        int numMoons = getInput("Enter the number of Moons: ");
        int numIterations = getInput("Enter the number of iterations: "); //czemu wlasciwie razy 100?
        long sleep_time = 100;
        
        /*    //test
        int numBlackHoles = 10;
        int numStars = 10;
        int numPlanets = 10;
        int numMoons = 10;
        int numIterations = 10000;
        long sleep_time = 100;
        */
        
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
            updatePositionsAndVelocities(i, blackHoles, stars, planets, moons, numBlackHoles, numStars, numPlanets);
            saveDataToCSV(i, blackHoles, stars, planets, moons);    //saving to .csv file with all iterations
        }
    }


    private static void updatePositionsAndVelocities(int iteration, BlackHole[] blackHoles, Star[] stars, Planet[] planets,
                                                     Moon[] moons, int numBHoles, int numStars, int numPlanets) {
        // Update positions and velocities of moons
        for(Moon moon : moons){
            MoonAttraction moon_attract = new MoonAttraction();
            double[] direction = new double[2];
            double[][] Forces = new double[numPlanets][2];
            for(int i = 0; i < numPlanets; i++){
                moon_attract.calculateDistance(moon, planets[i]);
                direction = moon_attract.getDirection(moon, planets[i]);
                moon_attract.calculateForce(moon.getMass(),planets[i].getMass());
                Forces[i][0] = moon_attract.calculateForceX();
                Forces[i][1] = moon_attract.calculateForceY();
            }
            moon.calculateNetForce(Forces, numPlanets);
            moon.calculateAcceleration();
            moon.calculateVelocity(moon_attract);
            moon.nextPosition(direction, moon_attract, planets, numPlanets);
        }


        // Update positions and velocities of planets
        for(Planet planet : planets){
            PlanetAttraction planet_attract = new PlanetAttraction();
            double[] direction = new double[2];
            double[][] Forces = new double[numStars][2];
            for(int i = 0; i < numStars; i++){
                planet_attract.calculateDistance(planet, stars[i]);
                direction = planet_attract.getDirection(planet, stars[i]);
                planet_attract.calculateForce(planet.getMass(), stars[i].getMass());
                Forces[i][0] = planet_attract.calculateForceX();
                Forces[i][1] = planet_attract.calculateForceY();
            }
            planet.calculateNetForce(Forces, numStars);
            planet.calculateAcceleration();
            planet.calculateVelocity(planet_attract);
            planet.nextPosition(direction, planet_attract, stars, numStars);
        }

        // Update positions and velocities of stars
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
            }
            star.calculateNetForce(Forces, numBHoles);
            star.calculateAcceleration();
            star.calculateVelocity(star_attract);
            star.nextPosition(direction, star_attract, blackHoles, numBHoles);
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
            double[] position = {random.nextInt(maxX), random.nextInt(maxY)};
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
            double[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            String name = "Star" + Integer.toString(i);
            stars[i] = new Star(mass, age, lifeExpectancy, velocity, position, name, starMinDistance);
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
            double[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            planets[i] = new Planet(mass, age, name, velocity, position, planetMinDistance);
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
            double[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            moons[i] = new Moon(mass, age, name, velocity, position, moonMinDistance);
        }
        return moons;
    }

private static void saveDataToCSV(int iteration, BlackHole[] blackHoles, Star[] stars, Planet[] planets, Moon[] moons) {
        try {
            FileWriter writer = new FileWriter("simulation_data.csv", true);

            if (iteration == 1) {
                writer.append("Iteration, Type, Mass, Age, Name, PositionX, PositionY, VelocityX, VelocityY\n");
            }

            for (BlackHole blackHole : blackHoles) {
                writer.append(iteration + ",BlackHole," + blackHole.getMass() + "," + blackHole.getAge() + "," + blackHole.getName() + ","
                        + blackHole.getPosition()[0] + "," + blackHole.getPosition()[1] + ",0.0,0.0\n");
            }

            for (Star star : stars) {
                writer.append(iteration + ",Star," + star.getMass() + "," + star.getAge() + ",," + star.getName() + ","
                        + star.getPosition()[0] + "," + star.getPosition()[1] + ","
                        + star.getVelocity()[0] + "," + star.getVelocity()[1] + "\n");
            }

            for (Planet planet : planets) {
                writer.append(iteration + ",Planet," + planet.getMass() + "," + planet.getAge() + "," + planet.getName() + ","
                        + planet.getPosition()[0] + "," + planet.getPosition()[1] + ","
                        + planet.getVelocity()[0] + "," + planet.getVelocity()[1] + "\n");
            }

            for (Moon moon : moons) {
                writer.append(iteration + ",Moon," + moon.getMass() + "," + moon.getAge() + "," + moon.getName() + ","
                        + moon.getPosition()[0] + "," + moon.getPosition()[1] + ","
                        + moon.getVelocity()[0] + "," + moon.getVelocity()[1] + "\n");
            }

            writer.flush();
            writer.close();
            System.out.println("Simulation data saved to simulation_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextInt();
    }

}
