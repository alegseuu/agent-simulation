class StarAttraction {
    private double distance;
    private double[] gravitationalForce;
    private double centrifugalForce;
    private double[] force; //array of forces from every black hole for every star
    private double[] direction;

    public StarAttraction() {
        this.distance = 0.0;
        this.gravitationalForce = new double[]{0.0, 0.0};
        this.centrifugalForce = 0.0;
        this.force = new double[]{0.0, 0.0};
        this.direction = new double[]{0.0, 0.0};
    }

    public double calculateDistance(int[] position1, int[] position2) {
        int dx = position2[0] - position1[0];
        int dy = position2[1] - position1[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getDistance(double[] vector) {
        return Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
    }

    public double getDistance(){return distance;};

    public int[] getDirection(Star star, BlackHole blackHole){
        int[] direction = new int[2];
        if(star.getX() != blackHole.getX()){
            direction[0] = (blackHole.getX()-star.getX())/Math.abs(blackHole.getX()-star.getX());
        }
        else {direction[0] = 0;
            System.out.println(this.direction[0]);}
        if(star.getY() != blackHole.getY()){
            direction[1] = (blackHole.getY()-star.getY())/Math.abs(blackHole.getY()-star.getY());
        }
        else {direction[1] = 0;
            System.out.println(this.direction[1]+" ");}
        //System.out.println(this.direction[0]+" "+this.direction[1]+" ");
        return direction;
    }

    public double calculateForceX( /*, double distance,*/ double mass1, double mass2) {
        //double gravitationalConstant = 6.67430e-11;
        //double force = (gravitationalConstant * mass1 * mass2) / (distance * distance);
        //double[] forceVector = {force * direction[0], force * direction[1]};
        this.force[0] = mass1 * mass2 /100;
        return this.force[0];
    }
    public double calculateForceY(double mass1, double mass2){
        this.force[1] = mass1 * mass2 /100;
        return this.force[1];
    }

    public double getForce(double[] forceVector) {
        return Math.sqrt(forceVector[0] * forceVector[0] + forceVector[1] * forceVector[1]);
    }

    public boolean compareForces(double force1, double force2) {
        return force1 > force2;
    }
}
