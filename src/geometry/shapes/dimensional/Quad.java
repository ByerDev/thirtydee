package geometry.shapes.dimensional;

import geometry.euclid.Axis;
import geometry.euclid.trimensional.Pos3D;
import geometry.euclid.trimensional.Vec3D;
import geometry.shapes.fundamentals.Base;

public class Quad extends Base<Quad> {
    public Triangle tri1, tri2;


    public Quad(Pos3D p1, Pos3D p2, Pos3D p3, Pos3D p4) {
        super(p1, new Pos3D[]{p1, p2, p3, p4});
        this.tri1 = new Triangle(p1, p2, p3);
        this.tri2 = new Triangle(p3, p4, p1);
    }

    public Quad(Vec3D v1, Vec3D v2, Vec3D diagonal) {
        this(v1.getStart(), v1.getEnd(), diagonal.getEnd(), v2.getEnd());
    }

    public Quad(Vec3D v1, Vec3D v2) {
        this(v1, v2, v1.add(v2));
    }

    private Quad(Triangle tri1, Triangle tri2) {
        this(tri1.p1, tri1.p2, tri2.p1, tri2.p2);
    }


    public Triangle[] getTris() {
        return new Triangle[]{tri1, tri2};
    }

    public Quad translate(Pos3D delta) {
        return new Quad(
            tri1.translate(delta),
            tri2.translate(delta)
        );
    }

    public Quad rotateAroundPoint(double angle, Axis axis, Pos3D origin) {
        return new Quad(
            tri1.rotateAroundPoint(angle, axis, origin),
            tri2.rotateAroundPoint(angle, axis, origin)
        );
    }
}
