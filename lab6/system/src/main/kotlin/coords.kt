class Coords(private val x: Double, private val y: Double){

    fun getCoords(): ArrayList<Double> {
        return arrayListOf(x,y)
    }

    override fun toString(): String {
        return "x: $x y: $y"
    }
}