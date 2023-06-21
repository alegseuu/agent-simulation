class Star extends MassObject {
    private int lifeExpectancy;
    private double[] velocity;
    private double[] acceleration;
    private double[] netForce;
    private double minDistance = 10;

    public Star(int mass, int age, int lifeExpectancy, double[] velocity, double[] position, String name, int minDistance) {
        super(mass, age, name, position);
        this.lifeExpectancy = lifeExpectancy;
        this.velocity = velocity;
        this.acceleration = new double[]{0.0, 0.0};
        this.netForce = new double[]{0.0, 0.0};
        this.minDistance = (double) minDistance;
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
        }
    }

    public void calculateAcceleration() {
        this.acceleration[0] = this.netForce[0] / ((double) this.getMass() /10);
        this.acceleration[1] = this.netForce[1] / ((double) this.getMass() / 10);
    }

    public void calculateVelocity(StarAttraction starat) {
       this.velocity[0] =Math.sqrt(this.acceleration[0] * starat.dx / 100);
       this.velocity[1] = Math.sqrt(this.acceleration[1] * starat.dy /100);
    }

    public void nextPosition(double[] direction, StarAttraction starat, BlackHole[] blackHoles, int numBH) {
        double x = this.getX(); //aktualna pozycja
        double y = this.getY();
        double next_x = this.getX()+((this.velocity[0]) * direction[0]); // następny x, zanim ustawimy go jako set x/y
        // trzeba sprawdzić czy ta następna pozycja nie jest za blisko obiektu
        double next_y = this.getY()+(this.velocity[1]* direction[1]);
        double dis;
        boolean dist_check = false;
        for(int i = 0; i < numBH; i++){
            dis = starat.checkDistance(this, blackHoles[i]);
            if(dis <= minDistance){ dist_check = true; }
        }
        if(!dist_check){
            this.setX( (this.getX()+((this.velocity[0]) * direction[0])));
            this.setY( (this.getY()+(this.velocity[1]* direction[1])));
        }
        else{
        }
    }
}
