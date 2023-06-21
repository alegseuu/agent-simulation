class Star extends MassObject {
    private int lifeExpectancy;
    private double[] velocity;
    private double[] acceleration;
    private double[] netForce;
    private double minDistance = radius * 4;
    double angle = 0.0;
    public Star(int mass, int age, int lifeExpectancy, double[] velocity, double[] position, String name, int minDistance, int maxMass) {
        super(mass, age, name, position);
        this.lifeExpectancy = lifeExpectancy;
        this.velocity = velocity;
        this.acceleration = new double[]{0.0, 0.0};
        this.netForce = new double[]{0.0, 0.0};
        this.minDistance = (double) minDistance;
        double maxMass1 = (double) maxMass;
        this.radius = this.getMass()/ maxMass1*10;
        //this.radius = 10;
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
            //System.out.println(this.netForce[0]+" "+this.netForce[1]);
        }
    }

    public void calculateAcceleration() {
        this.acceleration[0] = this.netForce[0] / ((double) this.getMass() /10);
        this.acceleration[1] = this.netForce[1] / ((double) this.getMass() / 10);
        //System.out.println(this.acceleration[0]+" "+this.acceleration[1]);
    }

    public void calculateVelocity(StarAttraction starat) {
       this.velocity[0] =Math.sqrt(this.acceleration[0] * starat.dx);
       this.velocity[1] = Math.sqrt(this.acceleration[1] * starat.dy);
        //System.out.println(this.velocity[0]+" "+this.velocity[1]+" "+this.getName());
    }

    public void nextPosition(double[] direction, StarAttraction starat, BlackHole[] blackHoles, int numBH) {
        //System.out.println(this.getX()+" "+this.getY());
        //System.out.println(this.velocity[0]+" "+this.velocity[1]+" "+this.getName()+" "+direction[0]+" "+direction[1]);
        //System.out.println((int)direction[0]);
        //double x = this.velocity[0] * (int)direction[0];
        //System.out.println(x);
        //double y = this.getX() + x;
        //System.out.println(y);
        //int z = (int) y;
        //System.out.println(z);
        double x = this.getX(); //aktualna pozycja
        double y = this.getY();
        double next_x = this.getX()+((this.velocity[0]) * direction[0]); // następny x, zanim ustawimy go jako set x/y
        // trzeba sprawdzić czy ta następna pozycja nie jest za blisko obiektu
        double next_y = this.getY()+(this.velocity[1]* direction[1]);
        double dis;
        boolean dist_check = false;
        int BHnum = 0;
        for(int i = 0; i < numBH; i++){
            dis = starat.checkDistance(this, blackHoles[i]);
            if(dis <= minDistance){
                dist_check = true;
                BHnum = i;
            }
        }
        if(!dist_check){
            this.setX( (this.getX()+((this.velocity[0]) * direction[0])));
            this.setY( (this.getY()+(this.velocity[1]* direction[1])));
        }
        else{
            System.out.println(angle);
            angle += (velocity[0]);
            System.out.println(angle);
            System.out.println(blackHoles[BHnum].getX()+" "+blackHoles[BHnum].getY()+" "+Math.cos(angle)+" ");
            System.out.println(this.getX()+" "+this.getY()+" "+minDistance+" "+(Math.cos(angle) * (minDistance)));
            this.setX(this.getX() + (Math.cos(angle) * (minDistance)));
            this.setY(this.getY() + (Math.sin(angle) * (minDistance)));
            System.out.println(this.getX()+" "+this.getY());
        }
        //System.out.println(this.getX()+" "+this.getY());
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
