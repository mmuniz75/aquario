package muniz.aquarium.fishselector.domain

class Tank(val width: Int,
           val length: Int,
           val heigth: Int) {

    val liter : Int = width * length * heigth / 1000

    val glassThickness : GlassThickness = GlassThickness.thickness(liter)

    val realLiter = calculateRealLiter()

    private fun calculateRealLiter() : Int {
        val WATER_SURFACE_SPACE = 1

        val realWidth = width - glassThickness.doubleCm
        val realLength = length - glassThickness.doubleCm
        val realHeight = heigth - glassThickness.doubleCm - WATER_SURFACE_SPACE

        return  realWidth * realLength * realHeight / 1000
    }

}