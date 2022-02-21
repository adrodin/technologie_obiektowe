import java.lang.IllegalArgumentException

class Flyweight(private val name: String, coords: Coords? = null, restNames: String? = null){
    private var connectedFlyweights: MutableList<Flyweight> = emptyArray<Flyweight>().toMutableList()
    private var coordsHistory: MutableList<Coords> = emptyArray<Coords>().toMutableList()

    init {
        if (!restNames.isNullOrEmpty()) {
            add(restNames,coords)
        }else{
            if (coords != null) {
                coordsHistory.add(coords)
            }
        }
    }

    override fun toString(): String {
        return name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun equals(other: Any?): Boolean = (other is Flyweight) && name == other.name

    fun getCoords(): MutableList<Coords> {
        return coordsHistory
    }

    private fun separeNames(fullName: String): Array<String> {
        return fullName.split(" ").toTypedArray()
    }

    private fun update(name: String?, coords: Coords?){
        if (name?.length == 0){
            if (coords != null) {
                coordsHistory.add(coords)
            }
        }else{
            if (name != null) {
                add(name,coords)
            }
        }
    }



    fun add(names: String, coords: Coords?){
        if(names.isNotEmpty()){
            val components = separeNames(names)
            val firstArg = components[0]
            val restArgs = components.copyOfRange(1, components.size).joinToString(separator = " ")
            val newFlyweight = Flyweight(firstArg,restNames = restArgs,coords = coords)
            if (!connectedFlyweights.contains(newFlyweight)){
                connectedFlyweights.add(newFlyweight)
            }else{
                connectedFlyweights[connectedFlyweights.indexOf(newFlyweight)].update(restArgs,coords)
            }
        }else{
            if (coords != null) {
                coordsHistory.add(coords)
            }

        }
    }


    fun get(names: String): Flyweight {
        return if(names.isNotEmpty()){
            val component = separeNames(names)
            val firstArg = component[0]
            val restArgs = component.copyOfRange(1, component.size).joinToString(separator = " ")
            val newFlyweight = Flyweight(firstArg,restNames = restArgs)
            if(connectedFlyweights.contains(newFlyweight)){
                //println("ZWRACA $name")
                connectedFlyweights[connectedFlyweights.indexOf(newFlyweight)].get(restArgs)
            }else{
                throw IllegalArgumentException()
            }
        }else{
           this
        }
    }
}