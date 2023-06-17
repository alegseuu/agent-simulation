class Star extends MassObject {
    private int lifeExpectancy;
    private double[] velocity;
    private double[] acceleration;
    private double[] netForce;

    public Star(int mass, int age, int lifeExpectancy, double[] velocity, int[] position, String name) {
        super(mass, age, name, position);
        this.lifeExpectancy = lifeExpectancy;
        this.velocity = velocity;
        this.acceleration = new double[]{0.0, 0.0};
        this.netForce = new double[]{0.0, 0.0};
    }



    public int getLifeExpectancy() {return lifeExpectancy;
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

    public void calculateNetForce(double[][] forces, int numBHoles) {
        netForce = new double[]{0.0, 0.0};
        for(int i = 0; i < numBHoles; i++){
            netForce[0] += forces[i][0];
            netForce[1] += forces[i][1];
            //System.out.println(this.netForce[0]+" "+this.netForce[1]+" "+this.getMass());
        }
    }

    public void calculateAcceleration() {
        this.acceleration[0] = this.netForce[0] / ((double) this.getMass() /100);
        this.acceleration[1] = this.netForce[1] / ((double) this.getMass() / 100);
    }

    public void calculateVelocity() {
       this.velocity[0] = this.acceleration[0];
       this.velocity[1] = this.acceleration[1];
    }

    public void nextPosition() {
        this.setX((int) (this.getX()+this.velocity[0]));
        this.setY((int) (this.getY()+this.velocity[1]));
    }
    /*
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

     */
}
