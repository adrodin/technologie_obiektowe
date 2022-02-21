
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

class Coords(
    private val x: Double = Random.nextDouble(49.95855025648944,50.154564013341734),
    private val y: Double = Random.nextDouble(19.688292482742394,20.02470275868903)
    ) {

    fun getCoords():ArrayList<Double>{
        return arrayListOf(x,y)
    }

    fun distance(coords: Coords): Double{
        val xy = coords.getCoords()
        return sqrt((x - xy[0]).pow(2.0)+(y - xy[1]).pow(2.0))
    }

}