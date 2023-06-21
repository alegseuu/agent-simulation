class MassObject {
    private int mass;
    private int age;
    private String name;
    private double[] position;

    public MassObject(int mass, int age, String name, double[] position) {
        this.mass = mass;
        this.age = age;
        this.name = name;
        this.position = position;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getPosition() {
        return position;
    }

    public double getX() {
        return position[0];
    }

    public double getY() {
        return position[1];
    }

    public double setX() {
        return position[0];
    }

    public double setY(){
        return position[1];
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public void setX(double x) {
        this.position[0] = x;
    }
    public void setY(double y) {
        this.position[1] = y;
    }
}

