package muniz.aquarium.fishselector.config

import muniz.aquarium.fishselector.infra.repository.converter.FishDBConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.PostgresDialect


@Configuration
class R2dbcConfiguration {

    @Bean
    fun customConversions(): R2dbcCustomConversions? {
        val converters: MutableList<Converter<*, *>> = ArrayList<Converter<*, *>>()
        converters.add(FishDBConverter())
        return R2dbcCustomConversions.of(PostgresDialect.INSTANCE, converters)
    }
}