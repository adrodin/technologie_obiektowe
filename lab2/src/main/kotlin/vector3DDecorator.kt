import kotlin.math.sqrt

class Vector3DDecorator(private val srcVector: IVector, private val z: Double = 0.0) {
    fun abs() : Double{
        val old = srcVector.abs()
        return sqrt(old*old + z*z)
    }
    fun cdot (param: IVector): Double{
        return srcVector.cdot(param) + z*param.getComponents()[2]
    }
    fun getComponents() : Array<Double>{
        return srcVector.getComponents().plus(z)
    }
    fun cross(param: IVector) : Vector3DDecorator{
        val a = getComponents()
        val b = param.getComponents().plus(0.0)
        return Vector3DDecorator(Vector2D(a[1]*b[2]-a[2]*b[1],a[2]*b[0]-a[0]*b[2]), a[0]*b[1]-a[1]*b[0]);
    }
    fun getSrcV(): IVector{
        return srcVector
    }

}

