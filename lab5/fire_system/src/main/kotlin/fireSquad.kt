import java.util.*
import kotlin.collections.ArrayList

class FireSquad(private val name: String, x: Double, y:Double) {
    private var fireTrucks : ArrayList<FireTruck>? = null
    private var coords : Coords? = null

    init {
        coords = Coords(x,y)
        fireTrucks = arrayListOf(
            FireTruck(),
            FireTruck(),
            FireTruck(),
            FireTruck(),
            FireTruck()
        )
    }

    operator fun compareTo(other: Objects): Int {
        if (this.toString().equals(other.toString())) return 0
        else return -1
    }

    override fun toString(): String {
        return name
    }

    fun numOfReadyTrucks():Int{
        return (fireTrucks?.filter { f -> f.isReady() })!!.size
    }

    fun truckGo(number: Int){
        var i=0
        while( i < number){
            if (fireTrucks?.get(i)?.isReady() == true){
                fireTrucks?.get(i)?.requestBusy()
                i++
            }
        }
    }

    fun truckBack(number: Int){
        var i=0
        while( i < number){
            if (fireTrucks?.get(i)?.isReady() == false){
                fireTrucks?.get(i)?.requestReady()
                i++
            }
        }
    }

    fun distance(coords: Coords) : Double? {
        return this.coords?.distance(coords)
    }

}