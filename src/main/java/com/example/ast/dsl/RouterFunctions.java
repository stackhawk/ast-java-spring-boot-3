package com.example.ast.dsl;

import com.example.ast.model.Bird;
import com.example.ast.model.Eagle;
import com.example.ast.model.Penguin;
import com.example.ast.model.Sparrow;
import com.example.ast.service.BirdService;
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

    private final BirdService birdService;

    RouterFunctions(BirdService birdService){
        this.birdService = birdService;
    }

    @Bean
    public RouterFunction<ServerResponse> router() {
        return route()
                // birds
                .GET("/router-functions/birds", request -> {
                    /* query params */
                    Optional<String> species = request.param("species");
                    Optional<Double> wingspan = request.param("wingspan").map(Double::parseDouble);
                    Optional<Double> weight = request.param("weight").map(Double::parseDouble);

                    List<Bird> birdsCopy = new ArrayList<>(birdService.getBirds());
                    species.ifPresent(s -> birdsCopy.removeIf(bird -> !bird.getSpecies().equalsIgnoreCase(s)));
                    wingspan.ifPresent(w -> birdsCopy.removeIf(bird -> bird.getWingspan() < w));
                    weight.ifPresent(w -> birdsCopy.removeIf(bird -> bird.getWeight() < w));
                    return ok().body(birdsCopy);
                })
                .GET("/router-functions/birds/{name}", request -> {
                    String name = request.pathVariable("name").toLowerCase();
                    return ok().body(birdService.getBirds().stream().filter(bird -> bird.getSpecies().toLowerCase().equals(name)).findFirst());
                })
                .POST("/router-functions/birds", request -> {
                    Bird bird = request.body(Bird.class);
                    BirdService.birds.add(bird);
                    return ok().body(birdService.getBirds());
                })
                .PATCH("/router-functions/birds/{name}", request -> {
                    String name = request.pathVariable("name").toLowerCase();
                    Bird bird = request.body(Bird.class);
                    birdService.getBirds().stream()
                            .filter(b -> b.getSpecies().toLowerCase().equals(name))
                            .findFirst()
                            .ifPresent(b -> {
                                b.setWeight(bird.getWeight());
                                b.setWingspan(bird.getWingspan());
                            });
                    return ok().body(birdService.getBirds());
                })
                .DELETE("/router-functions/birds/{name}", request -> {
                    String name = request.pathVariable("name").toLowerCase();
                    birdService.getBirds().removeIf(bird -> bird.getSpecies().toLowerCase().equals(name));
                    return ok().body(birdService.getBirds());
                })
                // bird songs
                .GET("/router-functions/birdsongs", request -> ok().body(birdService.getSongs()))
                .GET("/router-functions/birdsongs/{year}", request -> {
                    int year = Integer.parseInt(request.pathVariable("year"));
                    return ok().body(birdService.getSongs().stream().filter(song -> song.year() == year).findFirst());
                })
                .build();
    }
}
