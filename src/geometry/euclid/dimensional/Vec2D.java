package geometry.euclid.dimensional;

public class Vec2D {
    public double x1, y1, x2, y2;


    public Vec2D(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public Vec2D(Pos2D start, Pos2D end) {
        this.x1 = start.x;
        this.y1 = start.y;
        this.x2 = end.x;
        this.y2 = end.y;
    }


    public Pos2D getStart() {
        return new Pos2D(x1, y1);
    }

    public Pos2D getEnd() {
        return new Pos2D(x2, y2);
    }

    public Pos2D getDelta() {
        return getEnd().sub(getStart());
    }

    public double getMagnitude() {
        Pos2D delta = getDelta();
        return Math.sqrt(delta.x * delta.x + delta.y * delta.y);
    }

    public Vec2D add(Vec2D vec) {
        return new Vec2D(
            getStart().add(vec.getStart()),
            getEnd().add(vec.getEnd())
        );
    }
    public Vec2D add(double x1, double y1, double x2, double y2) {
        return add(new Vec2D(x1, y1, x2, y2));
    }

    public Vec2D multiply(Vec2D vec) {
        return new Vec2D(
                getStart().multiply(vec.getStart()),
                getEnd().multiply(vec.getEnd())
        );
    }
    public Vec2D multiply(double x1, double y1, double x2, double y2) {
        return multiply(new Vec2D(x1, y1, x2, y2));
    }

    public Vec2D rotateAroundPoint(double angle, Pos2D origin) {
        return new Vec2D(
            this.getStart().rotateAroundPoint(angle, origin),
            this.getEnd().rotateAroundPoint(angle, origin)
        );
    }

    public Vec2D rotate(double angle) {
        return this.rotateAroundPoint(angle, this.getStart());
    }

    public Vec2D scale(double scale) {
        return multiply(1, 1, scale, scale);
    }

    public double dot(Vec2D vec) {
        Pos2D out = getEnd().multiply(vec.getEnd());

        return out.x + out.y;
    }

    public Vec2D flip() {
        return new Vec2D(getEnd(), getStart());
    }

    public Vec2D normalize() {
        return this.scale(1 / this.getMagnitude());
    }

    @Override
    public String toString() {
        return String.format("%1$s -> %2$s", getStart(), getEnd());
    }
}
