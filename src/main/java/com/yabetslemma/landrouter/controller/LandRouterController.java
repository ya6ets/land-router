package com.yabetslemma.landrouter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/routing")
public class LandRouterController {

    private final Map<String, List<String>> countryGraph;

    public LandRouterController(Map<String, List<String>> countryGraph) {
        this.countryGraph = countryGraph;
    }

    @GetMapping("/{origin}/{destination}")
    public ResponseEntity<Map<String, List<String>>> getRoute(@PathVariable String origin, @PathVariable String destination) {

        String start = origin.toUpperCase();
        String end = destination.toUpperCase();

        if (!countryGraph.containsKey(start) || !countryGraph.containsKey(end)) return ResponseEntity.badRequest().build();

        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(start));
        Set<String> visited = new HashSet<>();
        visited.add(start);

        while (!queue.isEmpty()) {

            List<String> path = queue.poll();
            String last = path.get(path.size() - 1);

            if (last.equals(end)) return ResponseEntity.ok(Map.of("route", path));

            for (String neighbor : countryGraph.getOrDefault(last, Collections.emptyList())) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
