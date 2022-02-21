class ClosestSquad(private var squads: ArrayList<FireSquad>, private val event : Event): Iterator<FireSquad> {
    private var index :Int = 0
    override fun hasNext(): Boolean {
        return  squads.isNotEmpty()
    }


    override fun next(): FireSquad {
        val squad = squads[index]
        index = (index+1)%squads.size
        return squad
    }

    init {
        index = 0
        squads.sortBy { event.getCoords()?.let { it1 -> it.distance(it1) } }
    }
}