import java.util.List;

class Planet extends MassObject {
    private double[] velocity;
    private double[] acceleration;
    private double[] netForce;
    private double minDistance;

    public Planet(int mass, int age, String name, double[] velocity, double[] position, int minDistance) {
        super(mass, age, name, position);
        this.velocity = velocity;
        this.acceleration = new double[]{0.0, 0.0};
        this.netForce = new double[]{0.0, 0.0};
        this.minDistance = (double) minDistance;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public void calculateNetForce(double[][] forces, int numStars) {
        netForce = new double[]{0.0, 0.0};
        for(int i = 0; i < numStars; i++){
            netForce[0] += forces[i][0];
            netForce[1] += forces[i][1];
            System.out.println(this.netForce[0]+" "+this.netForce[1]);
        }
    }

    public void calculateAcceleration() {
        this.acceleration[0] = this.netForce[0] / ((double) this.getMass() / 10);
        this.acceleration[1] = this.netForce[1] / ((double) this.getMass() / 10);
    }

    public void calculateVelocity(PlanetAttraction planet_attract) {
        this.velocity[0] =Math.sqrt(this.acceleration[0] * planet_attract.dx / 100);
        this.velocity[1] = Math.sqrt(this.acceleration[1] * planet_attract.dy /100);
    }

    public void nextPosition(double[] direction, PlanetAttraction plant_at,
                             Star[] stars, int numStars){
        double dis;
        boolean dist_check = false;
        for(int i = 0; i < numStars; i++){
            dis = plant_at.checkDistance(this, stars[i]);
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
