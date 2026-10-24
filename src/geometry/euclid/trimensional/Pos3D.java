package geometry.euclid.trimensional;

import geometry.euclid.Axis;
import geometry.euclid.dimensional.Pos2D;

import java.util.Arrays;


public class Pos3D {
    public static final Pos3D ORIGIN = new Pos3D(0, 0, 0);
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

    // https://en.wikipedia.org/wiki/Rotation_matrix#Basic_3D_rotations
    public Pos3D rotate(double angle, Axis axis) {
        final double s = Math.sin(angle);
        final double c = Math.cos(angle);

        return switch (axis) {
            case X -> new Pos3D(
                x,
                y * c - z * s,
                y * s + z * c
            );
            case Y -> new Pos3D(
                x * c - z * s,
                y,
                x * s + z * c
            );
            case Z -> new Pos3D(
                x * c - y * s,
                x * s + y * c,
                z
            );
        };
    }

    public Pos3D rotateAroundPoint(double angle, Axis axis, Pos3D origin) {
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
