package muniz.aquarium.fishselector.domain

class Fish (name: String,
            size:Int,
            ph : List<PH>,
            dh: List<DH>,
            maxTemperature:Int,
            minTemperature:Int,
            minNumber:Int,
            widthTank:Int,
            lengthTank:Int,
            compatibility : List<Fish> = ArrayList()) {

}