import kotlin.math.sqrt

open class Vector2D(private var x: Double, private var y:Double):IVector {

    override fun getComponents(): Array<Double> {
        return arrayOf(x, y)
    }
    override fun abs(): Double {
        return sqrt(x * x + y * y)
    }
    override fun cdot(param: IVector): Double {
        val components = param.getComponents()
        return this.x * components[0] + this.y * components[1]
    }


}