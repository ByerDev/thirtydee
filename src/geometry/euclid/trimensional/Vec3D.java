package geometry.euclid.trimensional;

public class Vec3D {
    public double x1, y1, z1, x2, y2, z2;


    public Vec3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }
    public Vec3D(Pos3D start, Pos3D end) {
        this.x1 = start.x;
        this.y1 = start.y;
        this.z1 = start.z;
        this.x2 = end.x;
        this.y2 = end.y;
        this.z2 = end.z;
    }
    public Vec3D(Pos3D end) {
        this(Pos3D.ORIGIN, end);
    }


    public Pos3D getStart() {
        return new Pos3D(x1, y1, z1);
    }

    public Pos3D getEnd() {
        return new Pos3D(x2, y2, z2);
    }

    public Pos3D getDelta() {
        return getEnd().sub(getStart());
    }
    public Vec3D getDeltaVec() {
        return new Vec3D(getDelta());
    }

    public double getMagnitude() {
        Pos3D delta = getDelta();
        return Math.sqrt(delta.x * delta.x + delta.y * delta.y + delta.z * delta.z);
    }

    public boolean normalized() {
        return this.getMagnitude() == 1;
    }

    public Vec3D add(Vec3D vec) {
        return new Vec3D(
                vec.getEnd(),
                vec.getEnd().add(this.getDelta())
        );
    }
    public Vec3D add(double x1, double y1, double z1, double x2, double y2, double z2) {
        return add(new Vec3D(x1, y1, z1, x2, y2, z2));
    }

    public Vec3D multiply(Vec3D vec) {
        return new Vec3D(
                getStart().multiply(vec.getStart()),
                getEnd().multiply(vec.getEnd())
        );
    }
    public Vec3D multiply(Pos3D pos) {
        return this.multiply(1, 1, 1, pos.x, pos.y, pos.z);
    }
    public Vec3D multiply(double x1, double y1, double z1, double x2, double y2, double z2) {
        return multiply(new Vec3D(x1, y1, z1, x2, y2, z2));
    }

    public Vec3D rotateAroundPoint(double angle, Vec3D axis, Pos3D origin) {
        return new Vec3D(
            this.getStart().rotateAroundPoint(angle, axis, origin),
            this.getEnd().rotateAroundPoint(angle, axis, origin)
        );
    }
    public Vec3D rotate(double angle, Vec3D axis) {
        return this.rotateAroundPoint(angle, axis, this.getStart());
    }

    public Vec3D scale(double scale) {
        return multiply(scale, scale, scale, scale, scale, scale);
    }

    public double dot(Vec3D vec) {
        Pos3D out = getEnd().multiply(vec.getEnd());

        return out.x + out.y + out.z;
    }

    // https://www.euclideanspace.com/maths/algebra/vectors/vecAlgebra/index.htm
    public Vec3D cross(Vec3D vec) throws IllegalArgumentException {
        if (!getStart().equals(vec.getStart())) {
            throw new IllegalArgumentException("Both vectors need to have the same origin.");
        }

        Pos3D a = getEnd();
        Pos3D b = vec.getEnd();

        return new Pos3D(
                a.y * b.z - b.y * a.z,
                a.z * b.x - b.z * a.x,
                a.x * b.y - b.x * a.y
        ).toVector(getStart());
    }

    public Vec3D flip() {
        return new Vec3D(getEnd(), getStart());
    }

    public Vec3D normalize() {
        return this.scale(1 / this.getMagnitude());
    }

    @Override
    public String toString() {
        return String.format("%1$s -> %2$s", getStart(), getEnd());
    }
}
