package geometry.shapes.fundamentals;

import geometry.euclid.trimensional.Pos3D;
import geometry.euclid.trimensional.Vec3D;

public interface Transformation<T extends Transformation<T>> {
    T translate(Pos3D delta);
    T translateTo(Pos3D newOrigin);

    T rotate(double angle, Vec3D axis);
    T rotateAroundPoint(double angle, Vec3D axis, Pos3D origin);

    T scale(Vec3D scaling);
    T scale(double scale);
}
