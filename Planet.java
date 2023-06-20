import java.util.List;

class Planet extends MassObject {
    private double[] velocity;

    public Planet(int mass, int age, String name, double[] velocity, double[] position) {
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

    public double[] nextPosition() {
        double[] position = getPosition();
        position[0] += velocity[0];
        position[1] += velocity[1];
        return position;
    }
}
