class StarAttraction {
    private double distance;
    private double dx;
    private double dy;
    private double[] gravitationalForce;
    private double centrifugalForce;
    private double Force;
    private double[] forceXY; //array of forces from every black hole for every star
    private double[] direction;
    //final double gravitationalConstant = 6.67430e-11;
    final double gravitationalConstant = 6.67430;

    public StarAttraction() {
        this.distance = 0.0;
        this.gravitationalForce = new double[]{0.0, 0.0};
        this.centrifugalForce = 0.0;
        this.forceXY = new double[]{0.0, 0.0};
        this.direction = new double[]{0.0, 0.0};
        this.Force=0.0;
    }

    public void calculateDistance(Star star, BlackHole BH) {
        this.dx = Math.abs(star.getX()-BH.getX());
        this.dy = Math.abs(star.getY()-BH.getY());
        this.distance = Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        System.out.println(this.distance);
    }



    //public double getDistance(double[] vector) {
    //    return Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
    //}

    public double getDistance(){return distance;};

    public double[] getDirection(Star star, BlackHole blackHole){
        //System.out.println(star.getX()+" "+blackHole.getX());
        if(star.getX() != blackHole.getX()){
            this.direction[0] = (blackHole.getX()-star.getX())/Math.abs(blackHole.getX()-star.getX());
            //System.out.println(blackHole.getX()-star.getX()+" "+Math.abs(blackHole.getX()-star.getX())+" "+
                   // (blackHole.getX()-star.getX())/Math.abs(blackHole.getX()-star.getX())+" "+this.direction[0]);
        }
        else {this.direction[0] = 0;
            //System.out.println(this.direction[0]);
            }
        if(star.getY() != blackHole.getY()){
            this.direction[1] = (blackHole.getY()-star.getY())/Math.abs(blackHole.getY()-star.getY());
        }
        else {this.direction[1] = 0;
            }
        System.out.println(this.direction[0]+" "+this.direction[1]+" ");
        return this.direction;
    }

    public void calculateForce(double mass1, double mass2) {
        //double force = (gravitationalConstant * mass1 * mass2) / (distance * distance);
        //double[] forceVector = {force * direction[0], force * direction[1]};
        this.Force = gravitationalConstant * mass1 * mass2 /(this.distance * this.distance);
        System.out.println(this.Force);
    }
    public double calculateForceX() {
        this.forceXY[0] = this.Force * this.dx / this.distance; //sin
        System.out.println(this.forceXY[0]);
        return this.forceXY[0];
    }
    public double calculateForceY(){
        this.forceXY[1] = this.Force * this.dy / this.distance; //cos
        System.out.println(this.forceXY[1]);
        return this.forceXY[1];
    }

    public double getForce(double[] forceVector) {
        return Math.sqrt(forceVector[0] * forceVector[0] + forceVector[1] * forceVector[1]);
    }

    public boolean compareForces(double force1, double force2) {
        return force1 > force2;
    }
}
