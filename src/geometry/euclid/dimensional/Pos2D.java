package geometry.euclid.dimensional;

import geometry.euclid.trimensional.Pos3D;

import java.util.Arrays;

public class Pos2D {
    public static final Pos2D ORIGIN = new Pos2D(0, 0);
    public double x, y;


    public Pos2D(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public Pos2D add(double x, double y) {
        return new Pos2D(this.x + x, this.y + y);
    }
    public Pos2D add(Pos2D pos) {
        return add(pos.x, pos.y);
    }

    public Pos2D sub(double x, double y) {
        return new Pos2D(this.x - x, this.y - y);
    }
    public Pos2D sub(Pos2D pos) {
        return sub(pos.x, pos.y);
    }

    public Pos2D multiply(double x, double y) {
        return new Pos2D(this.x * x, this.y * y);
    }
    public Pos2D multiply(Pos2D pos) {
        return multiply(pos.x, pos.y);
    }

    public Pos2D divide(double x, double y) {
        return new Pos2D(this.x / x, this.y / y);
    }
    public Pos2D divide(Pos2D pos) {
        return divide(pos.x, pos.y);
    }

    // https://en.wikipedia.org/wiki/Rotation_matrix#In_two_dimensions
    public Pos2D rotate(double angle) {
        return new Pos2D(
            x * Math.cos(angle) - y * Math.sin(angle),
            x * Math.sin(angle) + y * Math.cos(angle)
        );
    }

    public Pos2D rotateAroundPoint(double angle, Pos2D origin) {
        return this.sub(origin).rotate(angle).add(origin);
    }

    public Pos2D scale(double scale) {
        return multiply(scale, scale);
    }

    @Override
    public String toString() {
        return String.format("Pos2D(%1$f, %2$f)", x, y);
    }

    public Pos3D to3D() {
        return new Pos3D(x, y, 0);
    }

    public Vec2D toVector(Pos2D start) {
        return new Vec2D(start, this);
    }
    public Vec2D toVector(double startX, double startY) {
        return toVector(new Pos2D(startX, startY));
    }
    public Vec2D toVector() {
        return toVector(ORIGIN);
    }

    public double[] toArray() {
        return new double[]{x,y};
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Pos2D.class) {
            Pos2D pos = (Pos2D) o;
            return Arrays.equals(toArray(), pos.toArray());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(
            (long) Double.hashCode(x)
            << 32
            | ((long) Double.hashCode(y))
        );
    }
}
