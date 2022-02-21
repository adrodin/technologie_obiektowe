class Proxy(private val factory: FlyweightFactory) {

    private fun adaptName(fullName:String): String {

        val components = fullName.split(" ")
        var output = ""
        for (element in components){
            output += element.capitalize()+" "
        }
        return output.trim()
    }

    fun get(name: String): MutableList<Coords> {
        return factory.get(adaptName(name)).getCoords()
    }

    fun add(name: String, coords: Coords){

        factory.add(adaptName(name),coords)
    }

}