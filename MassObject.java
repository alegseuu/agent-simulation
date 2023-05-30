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

    public void setPosition(int[] position) {
        this.position = position;
    }
}
