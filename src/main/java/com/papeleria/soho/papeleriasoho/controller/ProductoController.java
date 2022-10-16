package com.papeleria.soho.papeleriasoho.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papeleria.soho.papeleriasoho.payload.request.ProductoRequest;
import com.papeleria.soho.papeleriasoho.payload.response.ProductoResponse;
import com.papeleria.soho.papeleriasoho.payload.response.Response;
import com.papeleria.soho.papeleriasoho.services.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ProductoService productoService;

    @Operation(summary = "Traer todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "MM")
    })
    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody ProductoRequest producto) {
        return ResponseEntity.ok(productoService.save(producto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?>  update(@Valid @RequestBody ProductoRequest producto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(productoService.update(id, producto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> del(@PathVariable("id") Long id) {
        productoService.delete(id);
        var resp = new Response(false, "Eliminacion exitosa.");
        return ResponseEntity.ok(resp);
    }
}
