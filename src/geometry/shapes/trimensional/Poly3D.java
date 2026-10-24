package geometry.shapes.trimensional;

import geometry.euclid.trimensional.Pos3D;
import geometry.shapes.dimensional.Poly2D;
import geometry.shapes.fundamentals.Base;

import java.util.Arrays;

public abstract class Poly3D<T extends Poly3D<T, Fundamental>, Fundamental extends Poly2D<Fundamental>> extends Base<T> {
    public Fundamental[] fundamentals;


    protected Poly3D(Pos3D origin, Fundamental[] fundamentals) {
        super(origin);
        this.fundamentals = fundamentals;
    }


    @SuppressWarnings("unchecked")
    protected Fundamental[] getTranslatedFundamental(Pos3D delta) {
        return (Fundamental[]) Arrays.stream(this.fundamentals)
            .map(fundamental -> fundamental.translate(delta))
            .toArray();
    }

    @Override
    public Pos3D[] getVertices() {
        return (Pos3D[]) Arrays.stream(fundamentals)
            .map(Fundamental::getVertices)
            .flatMap(Arrays::stream)
            .distinct()
            .toArray();
    };
}
