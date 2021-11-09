import kotlin.math.acos
import kotlin.math.sqrt

class Polar2DAdapter(private var srcVector: Vector2D): IPolar2D, IVector {

    override fun abs(): Double {
        return this.srcVector.abs()
    }

    override fun cdot(param: IVector): Double {
        return this.srcVector.cdot(param)
    }
    override fun getComponents(): Array<Double> {
        return this.srcVector.getComponents()
    }

    override fun getAngle(): Double {

        val cosTheta = cdot(Vector2D(1.0,0.0)) / srcVector.abs()
        val sinTheta = sqrt(cosTheta * cosTheta - 1.0)

        val angleRadian = if (sinTheta > 0) -acos(cosTheta) else acos(cosTheta)
        val angle = angleRadian * 180 / Math.PI
        val y = getComponents()[1]
        return if (y < 0) 360.0-angle else angle

    }

}