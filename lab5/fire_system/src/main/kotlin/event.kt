import kotlin.random.Random

class Event {
    private var eventType: EventType? = null
    private var coords: Coords? = null
    init{
        eventType = if (Random.nextDouble(0.0,1.0) < 0.7){
            if (Random.nextDouble(0.0,1.0) < 0.05){
                EventType.LOCAL_FALSE
            }else{
                EventType.LOCAL
            }
        } else{
            if (Random.nextDouble(0.0,1.0) < 0.05){
                EventType.FIRE_FALSE
            }else{
                EventType.FIRE
            }
        }
    }

    fun getTime(): Int? {
        return eventType?.time
    }

    fun getNeededTrucks(): Int? {
        return eventType?.neededTrucks
    }

    override fun toString(): String {
        return eventType?.description.toString()
    }

    init {
        coords = Coords()
    }

    fun getCoords(): Coords? {
        return coords
    }

}

enum class EventType(val time: Int, val neededTrucks : Int, val description: String){
    FIRE(Random.nextInt(5,25),3, "pozar"),
    LOCAL(Random.nextInt(5,25),2, "lokalne zagrozenie"),
    FIRE_FALSE(0,3,"pozar falszywy"),
    LOCAL_FALSE(0,2,"lokalne zagrozenie falszywe")
}