import com.soywiz.korge.*
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.*
import

class Menu :Scene(){


    override suspend fun Container.sceneInit(): Unit {

        text("Czesc poczatkowej populacji oraz wylosowanych osobnikow posiada odpornosc").xy(10.0,300.0)
        var resistance = roundRect(20.0,20.0,5.0,5.0,Colors.RED).xy(600.0,300.0)
        resistance.onClick { if (resistance.fill == Colors.RED) resistance.fill =Colors.GREEN else resistance.fill=Colors.RED}

        val load = roundRect(200.0,50.0,5.0,5.0,Colors.AZURE)
		.xy(250.0,500.0)
        load.onClick {jump(resistance.fill == Colors.GREEN)}
	    text("Load",color=Colors.BLACK).centerOn(load)

	    val start = roundRect(200.0,50.0,5.0,5.0,Colors.AZURE).xy(600.0,500.0)
        start.onClick {jump(resistance.fill == Colors.GREEN)}
	    text("Start",color=Colors.BLACK).centerOn(start)


    }

    fun jump(action: Boolean){
        if (action) {
            try{
                File()
            }

        }

        launchImmediately { sceneContainer.changeTo<Simulation>() }
    }

}