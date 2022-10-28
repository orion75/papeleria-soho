package com.papeleria.soho.papeleriasoho.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import com.papeleria.soho.papeleriasoho.models.ETipoTercero;
import com.papeleria.soho.papeleriasoho.payload.request.TerceroRequest;
import com.papeleria.soho.papeleriasoho.payload.response.ProductoResponse;
import com.papeleria.soho.papeleriasoho.payload.response.Response;
import com.papeleria.soho.papeleriasoho.services.TerceroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TerceroService terceroService;

    @Operation(summary = "Traer todos los proveedores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de proveedores", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "MM")
    })
    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(terceroService.findAll(ETipoTercero.PROVEEDOR));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(terceroService.findById(id, ETipoTercero.PROVEEDOR));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody TerceroRequest model) {
        return ResponseEntity.ok(terceroService.save(model, ETipoTercero.PROVEEDOR));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?>  update(@Valid @RequestBody TerceroRequest model, @PathVariable("id") Long id) {
        return ResponseEntity.ok(terceroService.update(id, model));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> del(@PathVariable("id") Long id) {
        terceroService.delete(id);
        var resp = new Response(false, "Eliminacion exitosa.");
        return ResponseEntity.ok(resp);
    }
}
