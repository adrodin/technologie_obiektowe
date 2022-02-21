

import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class SquadsMenager(private val squads: ArrayList<FireSquad>) {

    fun newEvent(event: Event){
        println("Otrzymano zgloszenie: ${event.getCoords()?.getCoords()?.get(0)} ${event.getCoords()?.getCoords()?.get(1)}")
        val strategy = Strategy.prepareStrategy(squads,event)
        Strategy.executeStrategy(squads,event,strategy)
    }

}


class Notifications(squads: ArrayList<FireSquad>) {
    private var listeners: MutableList<FireSquad> =  arrayListOf()


    fun subscribe(squad: FireSquad) {
        listeners.add(squad)
    }

    fun unsubscribe(squad: FireSquad) {
        listeners.remove(squad)
    }

    fun notify(event: Event): ClosestSquad {
        return ClosestSquad(listeners as ArrayList<FireSquad>,event)
    }

    init {
        for (squad in squads) {
            listeners.add(squad)
        }
    }
}

