class PlanetAttraction {
    private double distance;
    double dx;
    double dy;
    private double[] gravitationalForce;
    private double centrifugalForce;
    private double Force;
    private double[] forceXY;
    private double[] direction;
    //final double gravitationalConstant = 6.67430e-11;
    final double gravitationalConstant = 6.67430;

    public PlanetAttraction() {
        this.distance = 0.0;
        this.gravitationalForce = new double[]{0.0, 0.0};
        this.centrifugalForce = 0.0;
        this.forceXY = new double[]{0.0, 0.0};
        this.direction = new double[]{0.0, 0.0};
        this.Force=0.0;
    }

    public void calculateDistance(Planet planet, Star star) {
       this.dx = Math.abs(planet.getX() - star.getX());
       this.dy = Math.abs(planet.getY() - star.getY());
       this.distance = Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    public double checkDistance(Planet planet, Star star) {
        this.dx = Math.abs(planet.getX() - star.getX());
        this.dy = Math.abs(planet.getY() - star.getY());
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
    public double getDistance(){return distance;}

    public double[] getDirection(Planet planet, Star star){
        if(planet.getX() != star.getX()){
            this.direction[0] = (star.getX()-planet.getX())/Math.abs(star.getX()-planet.getX());
        }
        else {this.direction[0] = 0;}
        if(planet.getY() != star.getY()){
            this.direction[1] = (star.getY()-planet.getY())/Math.abs(star.getY() - planet.getY());
        }
        else {this.direction[1] = 0;}
        return this.direction;
    }

    public void calculateForce(double mass1, double mass2) {
        this.Force = gravitationalConstant * mass1 * mass2 /(this.distance * this.distance);
    }

    public double calculateForceX() {
        this.forceXY[0] = this.Force * this.dx / this.distance; //sin
        //System.out.println(this.forceXY[0]);
        return this.forceXY[0];
    }
    public double calculateForceY(){
        this.forceXY[1] = this.Force * this.dy / this.distance; //cos
        //System.out.println(this.forceXY[1]);
        return this.forceXY[1];
    }
}
