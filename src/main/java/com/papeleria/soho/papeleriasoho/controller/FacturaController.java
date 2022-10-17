package com.papeleria.soho.papeleriasoho.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import com.papeleria.soho.papeleriasoho.payload.request.FacturaRequest;
import com.papeleria.soho.papeleriasoho.payload.response.ProductoResponse;
import com.papeleria.soho.papeleriasoho.payload.response.Response;
import com.papeleria.soho.papeleriasoho.services.FacturaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    FacturaService facturaService;

    @Operation(summary = "Traer todas las facturas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado de facturas", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoResponse.class)) }),
        @ApiResponse(responseCode = "400", description = "MM")
    })
    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(facturaService.findAll());
    }

    @Operation(summary = "Traer factura por id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(facturaService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody FacturaRequest model) {
        return ResponseEntity.ok(facturaService.save(model));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?>  update(@Valid @RequestBody FacturaRequest model, @PathVariable("id") Long id) {
        return ResponseEntity.ok(facturaService.update(id, model));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> del(@PathVariable("id") Long id) {
        facturaService.delete(id);
        var resp = new Response(false, "Eliminacion exitosa.");
        return ResponseEntity.ok(resp);
    }
}
