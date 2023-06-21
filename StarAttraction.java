class StarAttraction {
    private double distance;
    double dx;
    double dy;
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
        this.Force = 0.0;
    }

    public void calculateDistance(Star star, BlackHole BH) {
        this.dx = Math.abs(star.getX()-BH.getX());
        this.dy = Math.abs(star.getY()-BH.getY());
        this.distance = Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    public double checkDistance(Star star, BlackHole BH) { //nowa metoda, żeby nie nadpisywać pola distance przy samym sprawdzaniu
        this.dx = Math.abs(star.getX() - BH.getX());
        this.dy = Math.abs(star.getY() - BH.getY());
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    public double getDistance(){return distance;};
    public double[] getDirection(Star star, BlackHole blackHole){
        if(star.getX() != blackHole.getX()){
            this.direction[0] = (blackHole.getX()-star.getX())/Math.abs(blackHole.getX()-star.getX());
        }
        else {this.direction[0] = 0;
            }
        if(star.getY() != blackHole.getY()){
            this.direction[1] = (blackHole.getY()-star.getY())/Math.abs(blackHole.getY()-star.getY());
        }
        else {this.direction[1] = 0;
            }
        return this.direction;
    }

    public void calculateForce(double mass1, double mass2) {
        this.Force = gravitationalConstant * mass1 * mass2 /(this.distance * this.distance);
    }
    public double calculateForceX() {
        this.forceXY[0] = this.Force * this.dx / this.distance; //sin
        return this.forceXY[0];
    }
    public double calculateForceY(){
        this.forceXY[1] = this.Force * this.dy / this.distance; //cos
        return this.forceXY[1];
    }
}
