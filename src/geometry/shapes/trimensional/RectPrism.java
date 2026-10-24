package geometry.shapes.trimensional;

import geometry.euclid.Axis;
import geometry.euclid.trimensional.Pos3D;
import geometry.euclid.trimensional.Vec3D;
import geometry.shapes.dimensional.Quad;

import java.util.stream.Stream;

public class RectPrism extends Poly3D<RectPrism, Quad> {
    public RectPrism(Vec3D origin, double width, double height, double depth) {
        Vec3D _origin = origin.normalize();
        Stream<Quad> quads = Stream.of(
            new Quad( // bottom
                _origin.scale(width),
                _origin.rotate(-Math.PI / 2, Axis.Y).scale(depth)
            ),
            new Quad( // front
                _origin.scale(width),
                _origin.rotate()
            )
        );
    }

    private RectPrism(Pos3D origin, Quad[] quads) {
        super(origin, quads);
    }


    @Override
    public RectPrism translate(Pos3D delta) {
        return new RectPrism(this.origin.add(delta), this.getTranslatedFundamental(delta));
    }

    @Override
    public RectPrism rotateAroundPoint(double angle, Axis axis, Pos3D origin) {
        return null;
    }

    @Override
    public RectPrism scale(Vec3D scaling) {
        return null;
    }

    @Override
    public RectPrism scale(double scale) {
        return null;
    }
}
