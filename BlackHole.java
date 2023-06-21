class BlackHole extends MassObject {
    public BlackHole(int mass, int age, String name, double[] position, int maxMass) {

        super(mass, age, name, position);
        double maxMass1 = (double) maxMass;
        this.radius = this.getMass()/ maxMass1*10;
    }
}
