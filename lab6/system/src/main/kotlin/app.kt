import com.google.gson.Gson
import java.io.File
import kotlin.random.Random

class App {
    private var proxy: Proxy? = null

    init {
        proxy = Proxy(FlyweightFactory())
    }

    private fun add(){
        println("Podaj imie i nazwisko osoby")
        readLine()?.let { proxy?.add(it,cordsGen()) }
        println("Dodano nowa lokalizacje do bazy")
    }

    private fun get(){
        println("Podaj imie i nazwisko osoby")
        val name = readLine()
        try {
            val history = name?.let { proxy?.get(it)}
            println("Historia lokalizacji dla $name: $history")
        }catch (e :Exception){
            println("Nie ma takiego imienia w bazie")
        }


    }
    private fun save(){
        val tmp = Gson().toJson(proxy)
        File("data.json").writeText(tmp)
        println("Zapisano")
    }
    private fun load(){
        val xd = File("data.json").readText()
        proxy = Gson().fromJson<Proxy>(xd,Proxy::class.java)
        println("Wczytano")
    }
    private fun showMenu(){
        println("1. Dodaj")
        println("2. Wyszukaj")
        println("3. Zapisz")
        println("4. Wczytaj")
        println("5. Wyjdź")
    }

    fun run(){
        var work = true
        while (work){
            showMenu()
            when (readLine()){
                "1" -> add()
                "2" -> get()
                "3" -> save()
                "4" -> load()
                "5" -> work = false
                else ->{
                    println("Zla komenda")
                }
            }
        }
    }
    private fun cordsGen(): Coords {
        val x = Random.nextDouble(-180.0,180.0)
        val y = Random.nextDouble(-90.0,90.0)
        return Coords(x,y)
    }
}