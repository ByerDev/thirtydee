package geometry.shapes.fundamentals;

import geometry.euclid.trimensional.Pos3D;

public abstract class Base<T extends Base<T>> implements Transformation<T> {
    public Pos3D origin;
    protected Pos3D[] vertices;


    protected Base(Pos3D origin) {
        this.origin = origin;
        this.vertices = getVertices();
    }


    public Pos3D[] getVertices() {
        return vertices;
    }

    public T translateTo(Pos3D target) {
        return translate(target.sub(origin));
    }

    public T rotate(double angle, Vec3D axis) {
        return rotateAroundPoint(angle, axis, origin);
    }
}
