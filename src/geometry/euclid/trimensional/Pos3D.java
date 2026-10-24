package geometry.euclid.trimensional;

import geometry.euclid.dimensional.Pos2D;

import java.util.Arrays;


public class Pos3D {
    public static final Pos3D ORIGIN = new Pos3D(0, 0, 0);
    public static final double FULL_ROTATION = Math.PI * 2;
    public double x,y,z;


    public Pos3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public Pos3D add(double x, double y, double z) {
        return new Pos3D(this.x + x, this.y + y, this.z + z);
    }
    public Pos3D add(Pos3D pos) {
        return add(pos.x, pos.y, pos.z);
    }

    public Pos3D sub(double x, double y, double z) {
        return new Pos3D(this.x - x, this.y - y, this.z - z);
    }
    public Pos3D sub(Pos3D pos) {
        return sub(pos.x, pos.y, pos.z);
    }

    public Pos3D multiply(double x, double y, double z) {
        return new Pos3D(this.x * x, this.y * y, this.z * z);
    }
    public Pos3D multiply(Pos3D pos) {
        return multiply(pos.x, pos.y, pos.z);
    }

    public Pos3D divide(double x, double y, double z) {
        return new Pos3D(this.x / x, this.y / y, this.z * z);
    }
    public Pos3D divide(Pos3D pos) {
        return divide(pos.x, pos.y, pos.z);
    }

    // https://www.baeldung.com/java-matrix-multiplication#bd-own-implementation
    private Pos3D rotateWithMatrix(double[][] rotationMatrix) {
        double[] result = new double[3];

        for (int row = 0; row < 3; row++) {
            result[row] = 0;
            result[row] += rotationMatrix[row][0] * x;
            result[row] += rotationMatrix[row][1] * y;
            result[row] += rotationMatrix[row][2] * z;
        }

        return new Pos3D(result[0], result[1], result[2]);
    }

    // https://en.wikipedia.org/wiki/Rotation_matrix#Rotation_matrix_from_axis_and_angle
    public Pos3D rotate(double angle, Vec3D axis) {
        final double s = Math.sin(angle);
        final double c = Math.cos(angle);
        final double c1 = 1 - c;

        final Pos3D u = axis.normalize().getEnd();

        final double[][] r = new double[][]{
            new double[]{
                u.x * u.x * c1 + c,
                u.x * u.y * c1 - u.z * s,
                u.x * u.z * c1 + u.y * s
            },
            new double[]{
                u.x * u.y * c1 + u.z * s,
                u.y * u.y * c1 + c,
                u.y * u.z * c1 - u.x * s
            },
            new double[]{
                u.x * u.z * c1 - u.y * s,
                u.y * u.z * c1 + u.x * s,
                u.z * u.z * c1 + c
            }
        };

        return rotateWithMatrix(r);
    }

    public Pos3D rotateAroundPoint(double angle, Vec3D axis, Pos3D origin) {
        return this.sub(origin).rotate(angle, axis).add(origin);
    }

    public Pos3D scale(double scale) {
        return multiply(scale, scale, scale);
    }

    public String toString() {
        return String.format("Pos3D(%1$f, %2$f, %3$f)", x, y, z);
    }

    public Vec3D toVector(Pos3D start) {
        return new Vec3D(start, this);
    }
    public Vec3D toVector() {
        return toVector(ORIGIN);
    }

    public double[] toArray() {
        return new double[]{x, y, z};
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Pos3D.class) {
            Pos3D pos = (Pos3D) o;
            return Arrays.equals(toArray(), pos.toArray());
        }
        return false;
    }

    public Pos2D to2D() {
        return new Pos2D(x, y);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(
            ((long) to2D().hashCode()) << 32L &
                ((long) Double.hashCode(z))
        );
    }
}
