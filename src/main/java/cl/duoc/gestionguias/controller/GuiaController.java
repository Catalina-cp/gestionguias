package cl.duoc.gestionguias.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import cl.duoc.gestionguias.service.GuiaService;
import cl.duoc.gestionguias.entity.GuiaDespacho;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class GuiaController {

    @Autowired
    private GuiaService guiaService;

    @GetMapping("/guias")
    public List<GuiaDespacho> obtenerGuias() {

        return guiaService.obtenerGuias();
    }

    @PostMapping("/guias")
    public GuiaDespacho crearGuia(
            @RequestBody GuiaDespacho guia) {

        return guiaService.crearGuia(guia);
    }

    @GetMapping("/guias/{id}")
    public GuiaDespacho buscarPorId(
            @PathVariable Long id) {

        return guiaService.buscarPorId(id);
    }

    @PutMapping("/guias/{id}")
    public GuiaDespacho actualizarGuia(
            @PathVariable Long id,
            @RequestBody GuiaDespacho guia) {

        return guiaService.actualizarGuia(id, guia);
    }

    @DeleteMapping("/guias/{id}")
    public String eliminarGuia(
            @PathVariable Long id) {
        boolean eliminada = guiaService.eliminarGuia(id);
        if (eliminada) {
            return "Guía eliminada correctamente";
        }
        return "No se encontró la guía";
    }

    @GetMapping("/guias/transportista/{transportista}")
    public List<GuiaDespacho> buscarPorTransportista(
            @PathVariable String transportista) {

        return guiaService.buscarPorTransportista(transportista);
    }

    @GetMapping("/guias/fecha/{fecha}")
    public List<GuiaDespacho> buscarPorFecha(
            @PathVariable String fecha) {

        return guiaService.buscarPorFecha(fecha);
    }
}
