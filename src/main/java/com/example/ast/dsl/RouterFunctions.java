package com.example.ast.dsl;

import com.example.ast.model.Bird;
import com.example.ast.model.Eagle;
import com.example.ast.model.Penguin;
import com.example.ast.model.Sparrow;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@Component
public class RouterFunctions {

    private final List<Bird> birds = new ArrayList<>(
            Arrays.asList(
                    new Sparrow("Sparrow", 0.25, 0.02),
                    new Eagle("Eagle", 2.0, 6.0),
                    new Penguin("Penguin", 0.75, 15.0)
            )
    );

    @Bean
    public RouterFunction<ServerResponse> router() {
        return route()
                .GET("/router-functions/birds", request -> {
                    /* query params */
                    Optional<String> species = request.param("species");
                    Optional<Double> wingspan = request.param("wingspan").map(Double::parseDouble);
                    Optional<Double> weight = request.param("weight").map(Double::parseDouble);

                    List<Bird> birdsCopy = new ArrayList<>(birds);
                    species.ifPresent(s -> birdsCopy.removeIf(bird -> !bird.getSpecies().equalsIgnoreCase(s)));
                    wingspan.ifPresent(w -> birdsCopy.removeIf(bird -> bird.getWingspan() < w));
                    weight.ifPresent(w -> birdsCopy.removeIf(bird -> bird.getWeight() < w));
                    return ok().body(birdsCopy);
                })
                .GET("/router-functions/birds/{name}", request -> {
                    String name = request.pathVariable("name").toLowerCase();
                    return ok().body(birds.stream().filter(bird -> bird.getSpecies().toLowerCase().equals(name)).findFirst());
                })
                .POST("/router-functions/birds", request -> {
                    Bird bird = request.body(Bird.class);
                    birds.add(bird);
                    return ok().body(birds);
                })
                .PATCH("/router-functions/birds/{name}", request -> {
                    String name = request.pathVariable("name").toLowerCase();
                    Bird bird = request.body(Bird.class);
                    birds.stream()
                            .filter(b -> b.getSpecies().toLowerCase().equals(name))
                            .findFirst()
                            .ifPresent(b -> {
                                b.setWeight(bird.getWeight());
                                b.setWingspan(bird.getWingspan());
                            });
                    return ok().body(birds);
                })
                .DELETE("/router-functions/birds/{name}", request -> {
                    String name = request.pathVariable("name").toLowerCase();
                    birds.removeIf(bird -> bird.getSpecies().toLowerCase().equals(name));
                    return ok().body(birds);
                })
                .build();
    }
}
