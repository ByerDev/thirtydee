package geometry.shapes.dimensional;

import geometry.euclid.Axis;
import geometry.euclid.trimensional.Pos3D;
import geometry.euclid.trimensional.Vec3D;
import geometry.shapes.fundamentals.Base;

public class Triangle extends Base<Triangle> {
    public Pos3D p1, p2, p3;


    public Triangle(Pos3D p1, Pos3D p2, Pos3D p3) {
        super(p1, new Pos3D[]{p1, p2, p3});

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(Vec3D a, Vec3D b) throws IllegalArgumentException {
        if (!a.getStart().equals(b.getStart())) {
            throw new IllegalArgumentException("Both vectors need to have the same origin.");
        }

        this(a.getStart(), a.getEnd(), b.getEnd());
    }


    /*// https://en.wikipedia.org/wiki/Heron's_formula
    public double getArea() {
        double a = s1.getMagnitude();
        double b = s2.getMagnitude();
        double c = s3.getMagnitude();
        double s = (a + b + c) / 2;

        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public double getCircumference() {
        return s1.getMagnitude() + s2.getMagnitude() + s3.getMagnitude();
    }*/



    public Triangle translate(Pos3D delta) {
        return new Triangle(
            p1.add(delta),
            p2.add(delta),
            p3.add(delta)
        );
    }

    public Triangle rotateAroundPoint(double angle, Axis axis, Pos3D origin) {
        return new Triangle(
            p1.rotateAroundPoint(angle, axis, origin),
            p2.rotateAroundPoint(angle, axis, origin),
            p3.rotateAroundPoint(angle, axis, origin)
        );
    }
}