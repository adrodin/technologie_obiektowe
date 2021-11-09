

fun main(){
    val a = Vector2D(1.0,-1.0)
    val b = Vector2D(7.3,5.1)
    val c = Vector2D(7.1,-1.1)
    val bCords = b.getComponents()

    println("Uklad kartezjanski")
    println("Wektor a: x= ${a.getComponents()[0]} y= ${a.getComponents()[1]}")
    println("Wektor b: x= ${b.getComponents()[0]} y= ${b.getComponents()[1]}")
    println("Wektor c: x= ${c.getComponents()[0]} y= ${c.getComponents()[1]}")

    val aPolar = Polar2DAdapter(a)
    val bPolar = Polar2DInheritance(bCords[0],bCords[1])
    val cPolar = Polar2DAdapter(c)
    println("Uklad biegunowy")
    println("Wektor a: dlugosc= ${aPolar.abs()} kat= ${aPolar.getAngle()}")
    println("Wektor b: dlugosc= ${bPolar.abs()} kat= ${bPolar.getAngle()}")
    println("Wektor c: dlugosc= ${cPolar.abs()} kat= ${cPolar.getAngle()}")

    println("Iloczyn kartezjanski")
    println("ab: ${a.cdot(b)}")
    println("ac: ${a.cdot(c)}")
    println("bc: ${b.cdot(c)}")


    val abCross = Vector3DDecorator(a).cross(b)
    val acCross = Vector3DDecorator(a).cross(c)
    val bcCross = Vector3DInheritance(bCords[0],bCords[1]).cross(c)
    println("Iloczyn wektorowy")
    println("axb: x= ${abCross.getComponents()[0]} y= ${abCross.getComponents()[1]} z= ${abCross.getComponents()[2]}")
    println("axc: x= ${acCross.getComponents()[0]} y= ${acCross.getComponents()[1]} z= ${acCross.getComponents()[2]}")
    println("bxc: x= ${bcCross.getComponents()[0]} y= ${bcCross.getComponents()[1]} z= ${bcCross.getComponents()[2]}")

}