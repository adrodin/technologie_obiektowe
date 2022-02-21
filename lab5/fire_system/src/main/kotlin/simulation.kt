import java.lang.Thread.sleep
import kotlin.random.Random

class Simulation {
    var squadsMenager : SquadsMenager? = null

    init {
        val squads = arrayListOf(
            FireSquad("JRG-1", 50.05995, 19.94324),
            FireSquad("JRG-2", 50.03352, 19.93583),
            FireSquad("JRG-3", 50.07560, 19.88730),
            FireSquad("JRG-4", 50.03758, 20.00538),
            FireSquad("JRG-5", 50.09174, 19.91967),
            FireSquad("JRG-6", 50.01594, 20.01529),
            FireSquad("JRG-7", 50.09412, 19.97739),
            FireSquad("LSP Lotniska w Balicach", 50.07311, 19.78584),
            FireSquad("JRG Skawina", 49.97218, 19.79603),
            FireSquad("JRG Szkoly Aspirantow PSP",50.07735, 20.03269),
        )
        squadsMenager = SquadsMenager(squads)

    }

    fun run(){

        while (true){
            if (Random.nextDouble(0.0,1.0) < 0.02){

                val event = Event()
                squadsMenager?.newEvent(event)

            }
            sleep(100)
        }


    }
}