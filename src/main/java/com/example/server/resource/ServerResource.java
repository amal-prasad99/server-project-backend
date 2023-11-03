package com.example.server.resource;

import com.example.server.enumaration.Status;
import com.example.server.model.Responce;
import com.example.server.model.Server;
import com.example.server.service.implimentation.ServerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import static com.example.server.enumaration.Status.*;
import static java.time.LocalDateTime.*;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Responce> getServers() {
        return ResponseEntity.ok(
                Responce.builder()
                        .timeStamp(now())
                        .data(of("servers", serverService.list(30)))
                        .message("Servers Retrived")
                        .status(OK)
                        .statusCodee(OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Responce> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Responce.builder()
                        .timeStamp(now())
                        .data(of("server", server))
                        .message(server.getStatus()== SERVER_UP ? "Ping success" : "Ping fail")
                        .status(OK)
                        .statusCodee(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Responce> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Responce.builder()
                        .timeStamp(now())
                        .data(of("server", server))
                        .message("Serer created")
                        .status(CREATED)
                        .statusCodee(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Responce> getServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Responce.builder()
                        .timeStamp(now())
                        .data(of("server", serverService.get(id)))
                        .message("Server retrived")
                        .status(OK)
                        .statusCodee(OK.value())
                        .build()
        );
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Responce> deleteServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Responce.builder()
                        .timeStamp(now())
                        .data(of("deleted", serverService.delete(id)))
                        .message("Server deleted")
                        .status(OK)
                        .statusCodee(OK.value())
                        .build()
        );
    }

    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] deleteServer(@PathVariable("fileName") String  fileName) throws IOException {
        return Files.readAllBytes(Paths.get("../resources/images" + fileName));
    }

}
