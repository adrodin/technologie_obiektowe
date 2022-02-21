import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.random.Random

class Strategy {

    companion object{
        fun prepareStrategy(squads: ArrayList<FireSquad>, event: Event): HashMap<String, Int> {
            val neededTrucks = event.getNeededTrucks()
            val distance = Notifications(squads).notify(event)
            var currentTrucks = 0
            var truckToSend :HashMap<String,Int> = HashMap()
            while (distance.hasNext()){
                val squad = distance.next()
                if (squad.numOfReadyTrucks() > 0){
                    val truckInSquad = squad.numOfReadyTrucks()
                    if (truckInSquad >= neededTrucks!!){
                        truckToSend[squad.toString()] = neededTrucks - currentTrucks
                        currentTrucks = neededTrucks
                    }else{
                        truckToSend[squad.toString()] = truckInSquad
                        currentTrucks += truckInSquad
                    }

                }

                if (currentTrucks == neededTrucks){
                    break
                }
            }
            return truckToSend
        }


        fun executeStrategy(squads: ArrayList<FireSquad>,event: Event,truckToSend :HashMap<String,Int>){
            Thread {
                send(squads,event,truckToSend)
                back(squads,truckToSend)
            }.start()
        }
        private fun send(squads: ArrayList<FireSquad>,event: Event,truckToSend :HashMap<String,Int>){

            for ((key,value) in truckToSend){
                for (squad in squads){
                    if (squad.toString() == key){
                        squad.truckGo(value)
                        println("Wyjazd ${value} wozow z ${key} do zdarzenia")
                        break
                    }
                }
            }


            sleep(Random.nextLong(0,3)*1000)

            println("Zarejestrowane zdarzenie: $event w ${event.getCoords()?.getCoords()?.get(0)} ${event.getCoords()?.getCoords()?.get(1)}")
            val time : Int? = event.getTime()
            if (time != null) {
                sleep((time*1000).toLong())
                println("Zagrozenie w ${event.getCoords()?.getCoords()?.get(0)} ${event.getCoords()?.getCoords()?.get(1)} usuniete")
            }

        }
        private fun back(squads: ArrayList<FireSquad>,truckToBack :HashMap<String,Int>){
            sleep(Random.nextLong(0,3)*1000)
            for ((key,value) in truckToBack){
                for (squad in squads){
                    if (squad.toString() == key){
                        println("Powrot ${value} wozow do ${key}")
                        squad.truckBack(value)
                        break
                    }
                }
            }
        }
    }

}