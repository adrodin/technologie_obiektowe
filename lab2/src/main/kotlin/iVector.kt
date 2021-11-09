interface IVector {
    fun abs(): Double
    fun cdot(param: IVector): Double
    fun getComponents(): Array<Double>
}