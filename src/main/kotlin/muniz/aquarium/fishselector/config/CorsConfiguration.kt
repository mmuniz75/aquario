package muniz.aquarium.fishselector.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer


@Configuration
@EnableWebFlux
class CorsConfiguration : WebFluxConfigurer{

    @Value("\${application.website}")
    private lateinit var server: String

    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/**")
            .allowedOrigins(server)
            .allowedMethods("*")
            .maxAge(1L)
    }
}
