

class FireTruck {
    private var state: State? = null

    init  {
        state = ReadyState(this)
    }

    fun changeState(state: State?) {
        this.state = state
    }

    fun getState(): State? {
        return state
    }

    fun requestBusy(){
        state?.handleBusy()
    }
    fun requestReady(){
        state?.handleReady()
    }

    fun isReady(): Boolean{
        return state is ReadyState
    }


}


abstract class State internal constructor(fireTruck: FireTruck) {
    var fireTruck: FireTruck? = null
    abstract fun handleReady()
    abstract fun handleBusy()

    init {
        this.fireTruck = fireTruck
    }
}

class BusyState(fireTruck: FireTruck): State(fireTruck){
    override fun handleReady(){
        fireTruck?.changeState(fireTruck?.let { ReadyState(it) })
    }

    override fun handleBusy() {
        super.fireTruck
    }

}

class ReadyState(fireTruck: FireTruck): State(fireTruck){
    override fun handleReady(){
        super.fireTruck
    }

    override fun handleBusy() {
        fireTruck?.changeState(fireTruck?.let { BusyState(it) })
    }

}