class MassObject {
    private int mass;
    private int age;
    private String name;
    private int[] position;

    public MassObject(int mass, int age, String name, int[] position) {
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

    public int[] getPosition() {
        return position;
    }

    public int getX() {
        return position[0];
    }

    public int getY() {
        return position[1];
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setX(int x) {
        this.position[0] = x;
    }
    public void setY(int y) {
        this.position[1] = y;
    }
}

