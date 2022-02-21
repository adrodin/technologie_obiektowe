import java.lang.IllegalArgumentException

class FlyweightFactory() {
    private var flyweights : MutableList<Flyweight> = emptyList<Flyweight>().toMutableList()

    fun get(name:String): Flyweight{
        val components = name.split(" ").toTypedArray()
        val mainName = components[0]
        val restName = components.copyOfRange(1, components.size).joinToString(separator = " ")
        if(flyweights.contains(Flyweight(mainName))){
            return flyweights[flyweights.indexOf(Flyweight(mainName))].get(restName)
        }else
        {
            throw IllegalArgumentException()
        }
    }
    fun add(name: String, coords: Coords){
        val components = name.split(" ").toTypedArray()
        val mainName = components[0]
        val restName = components.copyOfRange(1, components.size).joinToString(separator = " ")
        if(flyweights.contains(Flyweight(mainName))){
            flyweights[flyweights.indexOf(Flyweight(mainName))].add(restName,coords)
        }else
        {
            val tmp = Flyweight(mainName)
            flyweights.add(tmp)
            tmp.add(restName,coords)
        }

    }
}



