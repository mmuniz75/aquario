package muniz.aquarium.fishselector.domain

class Tank(width: Int, length: Int, heigth: Int) {

    var liter : Int = width * length * heigth / 1000

    var glassThickness : GlassThicknessEnum = GlassThicknessEnum.thickness(liter)


}