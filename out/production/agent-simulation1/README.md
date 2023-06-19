# agent-simulation
//wszystko napisane ciągiem


import javax.xml.namespace.QName;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class MassObject {
    private int mass;
    private int age;
    private String name;
    private int[] position;

    public MassObject(int mass, int age, String name, int[] position) {
        this.mass = mass;
        this.age = age;
        this.name = name;
        this.position = position;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
}

class BlackHole extends MassObject {
    public BlackHole(int[] position) {
        super(0, 0, "Black Hole", position);
    }
}

class Star extends MassObject {
    private int lifeExpectancy;
    private double[] velocity;

    public Star(int mass, int age, int lifeExpectancy, double[] velocity, int[] position) {
        super(mass, age, name, position);
        this.lifeExpectancy = lifeExpectancy;
        this.velocity = velocity;
    }

    public int getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(int lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public double[] calculateNetForce(List<double[]> forces) {
        double[] netForce = {0.0, 0.0};
        for (double[] force : forces) {
            netForce[0] += force[0];
            netForce[1] += force[1];
        }
        return netForce;
    }

    public double[] calculateAcceleration(double[] force) {
        double[] acceleration = {force[0] / getMass(), force[1] / getMass()};
        return acceleration;
    }

    public double[] calculateVelocity(double[] acceleration) {
        velocity[0] += acceleration[0];
        velocity[1] += acceleration[1];
        return velocity;
    }

    public int[] nextPosition() {
        int[] position = getPosition();
        position[0] += velocity[0];
        position[1] += velocity[1];
        return position;
    }

    public void checkStarAge(int years) {
        if (getAge() + years > lifeExpectancy) {
            // Transform into a black hole
            BlackHole blackHole = new BlackHole(getPosition());
            blackHole.setMass(getMass());
            blackHole.setAge(getAge());
            blackHole.setName(getName());
            // ...
        } else {
            setAge(getAge() + years);
        }
    }
}

class Planet extends MassObject {
    private double[] velocity;

    public Planet(int mass, int age, String name, double[] velocity, int[] position) {
        super(mass, age, name, position);
        this.velocity = velocity;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public double[] calculateNetForce(List<double[]> forces) {
        double[] netForce = {0.0, 0.0};
        for (double[] force : forces) {
            netForce[0] += force[0];
            netForce[1] += force[1];
        }
        return netForce;
    }

    public double[] calculateAcceleration(double[] force) {
        double[] acceleration = {force[0] / getMass(), force[1] / getMass()};
        return acceleration;
    }

    public double[] calculateVelocity(double[] acceleration) {
        velocity[0] += acceleration[0];
        velocity[1] += acceleration[1];
        return velocity;
    }

    public int[] nextPosition() {
        int[] position = getPosition();
        position[0] += velocity[0];
        position[1] += velocity[1];
        return position;
    }
}

class Moon extends MassObject {
    private double[] velocity;

    public Moon(int mass, int age, String name, double[] velocity, int[] position) {
        super(mass, age, name, position);
        this.velocity = velocity;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public double[] calculateNetForce(List<double[]> forces) {
        double[] netForce = {0.0, 0.0};
        for (double[] force : forces) {
            netForce[0] += force[0];
            netForce[1] += force[1];
        }
        return netForce;
    }

    public double[] calculateAcceleration(double[] force) {
        double[] acceleration = {force[0] / getMass(), force[1] / getMass()};
        return acceleration;
    }

    public double[] calculateVelocity(double[] acceleration) {
        velocity[0] += acceleration[0];
        velocity[1] += acceleration[1];
        return velocity;
    }

    public int[] nextPosition() {
        int[] position = getPosition();
        position[0] += velocity[0];
        position[1] += velocity[1];
        return position;
    }
}

class StarAttraction {
    private double distance;
    private double[] gravitationalForce;
    private double centrifugalForce;

    public StarAttraction() {
        this.distance = 0.0;
        this.gravitationalForce = new double[]{0.0, 0.0};
        this.centrifugalForce = 0.0;
    }

    public double calculateDistance(int[] position1, int[] position2) {
        int dx = position2[0] - position1[0];
        int dy = position2[1] - position1[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getDistance(double[] vector) {
        return Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
    }

    public double[] calculateForce(double[] direction, double distance, double mass1, double mass2) {
        double gravitationalConstant = 6.67430e-11;
        double force = (gravitationalConstant * mass1 * mass2) / (distance * distance);
        double[] forceVector = {force * direction[0], force * direction[1]};
        return forceVector;
    }

    public double getForce(double[] forceVector) {
        return Math.sqrt(forceVector[0] * forceVector[0] + forceVector[1] * forceVector[1]);
    }

    public boolean compareForces(double force1, double force2) {
        return force1 > force2;
    }
}

class PlanetAttraction {
    private double distance;
    private double[] gravitationalForce;
    private double centrifugalForce;

    public PlanetAttraction() {
        this.distance = 0.0;
        this.gravitationalForce = new double[]{0.0, 0.0};
        this.centrifugalForce = 0.0;
    }

    public double calculateDistance(int[] position1, int[] position2) {
        int dx = position2[0] - position1[0];
        int dy = position2[1] - position1[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getDistance(double[] vector) {
        return Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
    }

    public double[] calculateForce(double[] direction, double distance, double mass1, double mass2) {
        double gravitationalConstant = 6.67430e-11;
        double force = (gravitationalConstant * mass1 * mass2) / (distance * distance);
        double[] forceVector = {force * direction[0], force * direction[1]};
        return forceVector;
    }

    public double getForce(double[] forceVector) {
        return Math.sqrt(forceVector[0] * forceVector[0] + forceVector[1] * forceVector[1]);
    }

    public boolean compareForces(double force1, double force2) {
        return force1 > force2;
    }
}

class MoonAttraction {
    private double distance;
    private double[] gravitationalForce;
    private double centrifugalForce;

    public MoonAttraction() {
        this.distance = 0.0;
        this.gravitationalForce = new double[]{0.0, 0.0};
        this.centrifugalForce = 0.0;
    }

    public double calculateDistance(int[] position1, int[] position2) {
        int dx = position2[0] - position1[0];
        int dy = position2[1] - position1[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getDistance(double[] vector) {
        return Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
    }

    public double[] calculateForce(double[] direction, double distance, double mass1, double mass2) {
        double gravitationalConstant = 6.67430e-11;
        double force = (gravitationalConstant * mass1 * mass2) / (distance * distance);
        double[] forceVector = {force * direction[0], force * direction[1]};
        return forceVector;
    }

    public double getForce(double[] forceVector) {
        return Math.sqrt(forceVector[0] * forceVector[0] + forceVector[1] * forceVector[1]);
    }

    public boolean compareForces(double force1, double force2) {
        return force1 > force2;
    }
}

public class Main {
    public static void main(String[] args) {
        // Get input from the user
        int numBlackHoles = getInput("Enter the number of BlackHoles: ");
        int numStars = getInput("Enter the number of Stars: ");
        int numPlanets = getInput("Enter the number of Planets: ");
        int numMoons = getInput("Enter the number of Moons: ");
        int numIterations = getInput("Enter the number of iterations: ") * 100;

        // Create the objects
        BlackHole[] blackHoles = createBlackHoles(numBlackHoles);
        Star[] stars = createStars(numStars);
        Planet[] planets = createPlanets(numPlanets);
        Moon[] moons = createMoons(numMoons);

        // Perform simulation iterations
        for (int i = 1; i <= numIterations; i++) {
            // Update positions and velocities of objects
            updatePositionsAndVelocities(blackHoles, stars, planets, moons);

            // Print the current state of the simulation
            printSimulationState(i, blackHoles, stars, planets, moons);
        }
    }

    private static int getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextInt();
    }

    private static BlackHole[] createBlackHoles(int numBlackHoles) {
        BlackHole[] blackHoles = new BlackHole[numBlackHoles];
        Random random = new Random();
        int maxX = 1000;
        int maxY = 1000;

        for (int i = 0; i < numBlackHoles; i++) {
            int[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            blackHoles[i] = new BlackHole(position);
        }

        return blackHoles;
    }

    private static Star[] createStars(int numStars) {
        Star[] stars = new Star[numStars];
        Random random = new Random();
        int maxX = 1000;
        int maxY = 1000;

        for (int i = 0; i < numStars; i++) {
            int mass = random.nextInt(100);
            int age = random.nextInt(100);
            int lifeExpectancy = random.nextInt(1000);
            double[] velocity = {0.0, 0.0};
            int[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            stars[i] = new Star(mass, age, lifeExpectancy, velocity, position);
        }

        return stars;
    }

    private static Planet[] createPlanets(int numPlanets) {
        Planet[] planets = new Planet[numPlanets];
        Random random = new Random();
        int maxX = 1000;
        int maxY = 1000;

        for (int i = 0; i < numPlanets; i++) {
            int mass = random.nextInt(100);
            int age = random.nextInt(100);
            String name = "Planet";
            double[] velocity = {0.0, 0.0};
            int[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            planets[i] = new Planet(mass, age, name, velocity, position);
        }

        return planets;
    }

    private static Moon[] createMoons(int numMoons) {
        Moon[] moons = new Moon[numMoons];
        Random random = new Random();
        int maxX = 1000;
        int maxY = 1000;

        for (int i = 0; i < numMoons; i++) {
            int mass = random.nextInt(100);
            int age = random.nextInt(100);
            String name = "Moon";
            double[] velocity = {0.0, 0.0};
            int[] position = {random.nextInt(maxX), random.nextInt(maxY)};
            moons[i] = new Moon(mass, age, name, velocity, position);
        }

        return moons;
    }

    private static void updatePositionsAndVelocities(BlackHole[] blackHoles, Star[] stars, Planet[] planets, Moon[] moons) {
        // Update positions and velocities of black holes
        for (BlackHole blackHole : blackHoles) {
            // Black holes do not move, so no update is needed
        }

        // Update positions and velocities of stars
        for (Star star : stars) {
            double[] force = {0.0, 0.0};
            for (BlackHole blackHole : blackHoles) {
                double[] attractionForce = star.calculateForce(blackHole.getPosition(), blackHole.getMass(), star.getMass(), star.getDistance());
                force[0] += attractionForce[0];
                force[1] += attractionForce[1];
            }
            star.setForce(force);
            star.updateVelocity();
            star.updatePosition();
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

    private static void printSimulationState(int iteration, BlackHole[] blackHoles, Star[] stars, Planet[] planets, Moon[] moons) {
        char[][] board = new char[1000][1000];

        // Initialize the board with empty spaces
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }

        // Place BlackHoles on the board
        for (BlackHole blackHole : blackHoles) {
            int x = blackHole.getPosition()[0];
            int y = blackHole.getPosition()[1];
            board[y][x] = 'B';
        }

        // Place Stars on the board
        for (Star star : stars) {
            int x = star.getPosition()[0];
            int y = star.getPosition()[1];
            board[y][x] = 'S';
        }

        // Place Planets on the board
        for (Planet planet : planets) {
            int x = planet.getPosition()[0];
            int y = planet.getPosition()[1];
            board[y][x] = 'P';
        }

        // Place Moons on the board
        for (Moon moon : moons) {
            int x = moon.getPosition()[0];
            int y = moon.getPosition()[1];
            board[y][x] = 'M';
        }

        // Print the board
        System.out.println("Iteration " + iteration);
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println();
    }
}
