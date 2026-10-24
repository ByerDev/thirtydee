package geometry.shapes.dimensional;

import geometry.euclid.trimensional.Pos3D;
import geometry.euclid.trimensional.Vec3D;
import geometry.shapes.fundamentals.Base;

import java.util.Arrays;

public abstract class Poly2D<T extends Poly2D<T>> extends Base<T> {
    public Vec3D[] sides;


    protected Poly2D(Pos3D origin, Pos3D[] vertices) {
        super(origin);

        this.vertices = vertices;
        this.sides = this.getSidesFromVertices();
    }


    private Vec3D[] getSidesFromVertices() {
        Vec3D[] sides = new Vec3D[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            sides[i] = new Vec3D(vertices[i], vertices[(i + 1) % vertices.length]);
        }

        return sides;
    }

    protected Pos3D[] getTranslatedVertices(Pos3D delta) {
        return (Pos3D[])
            Arrays.stream(vertices)
            .map(p -> p.add(delta))
            .toArray();
    }
}
