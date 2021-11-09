import kotlin.math.acos
import kotlin.math.sqrt

class Polar2DInheritance(x: Double, y: Double): Vector2D(x, y) {

     fun getAngle(): Double{

         val cosTheta = cdot(Vector2D(1.0,0.0)) / super.abs()
         val sinTheta = sqrt(cosTheta * cosTheta - 1.0)

         val angleRadian = if (sinTheta > 0) -acos(cosTheta) else acos(cosTheta)
         val angle = angleRadian * 180 / Math.PI
         val y = getComponents()[1]
         return if (y < 0) 360.0-angle else angle

    }
}