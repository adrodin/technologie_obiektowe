import kotlin.math.sqrt

class Vector3DInheritance(x: Double, y:Double, private var z: Double = 0.0 ):Vector2D(x,y) {
    override fun abs() : Double {
        val old = super.abs()
        return sqrt(old * old + z * z)
    }
    override fun cdot(param: IVector): Double{
        return super.cdot(param) + z * param.getComponents()[2]
    }
    override fun getComponents(): Array<Double>{
        return super.getComponents().plus(z)
    }
    fun cross(param: IVector) : Vector3DInheritance{
        val a = getComponents()
        val b = param.getComponents().plus(0.0)
        return Vector3DInheritance(a[1]*b[2]-a[2]*b[1],a[2]*b[0]-a[0]*b[2], a[0]*b[1]-a[1]*b[0]);
    }
    fun getSrcV(): IVector{
        val cords = super.getComponents()
        return Vector2D(cords[0],cords[1])
    }


}
