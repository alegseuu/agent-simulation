import java.util.List;

class Star extends MassObject {
    private int lifeExpectancy;
    private double[] velocity;

    public Star(int mass, int age, int lifeExpectancy, double[] velocity, int[] position, String name) {
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
