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

    public double getDistance(){return distance;};

    public int[] getDirection(int[] position_star, int[] position_blackhole){
        int[] direction = new int[2];
        if(position_star[0]!=position_blackhole[0]){
            direction[0] = (position_blackhole[0]-position_star[0])/Math.abs(position_blackhole[0]-position_star[0]);
        }
        else {direction[0] = 0;}
        if(position_star[1]!=position_blackhole[1]){
            direction[1] = (position_blackhole[1]-position_star[1])/Math.abs(position_blackhole[1]-position_star[1]);
        }
        else {direction[1] = 0;}
        return direction;
    }

    public double[] calculateForce(int[] direction, double distance, double mass1, double mass2) {
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
