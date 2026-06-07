package cl.duoc.gestionguias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.gestionguias.entity.GuiaDespacho;
import cl.duoc.gestionguias.repository.GuiaRepository;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class GuiaService {

    @Autowired
    private GuiaRepository guiaRepository;

    public GuiaDespacho crearGuia(GuiaDespacho guia) {
        guia.setEstado("GENERADA");

        GuiaDespacho guiaGuardada = guiaRepository.save(guia);
        try {

            File carpeta = new File("guias");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            FileWriter writer = new FileWriter(
                    "guias/" + guiaGuardada.getNumeroGuia() + ".txt");

            writer.write("Número Guía: "
                    + guiaGuardada.getNumeroGuia()
                    + "\n");

            writer.write("Transportista: "
                    + guiaGuardada.getTransportista()
                    + "\n");

            writer.write("Fecha: "
                    + guiaGuardada.getFecha()
                    + "\n");

            writer.write("Estado: "
                    + guiaGuardada.getEstado());

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return guiaGuardada;
    }

    public List<GuiaDespacho> obtenerGuias() {

        return guiaRepository.findAll();
    }

    public GuiaDespacho buscarPorId(Long id) {
        Optional<GuiaDespacho> guia = guiaRepository.findById(id);

        return guia.orElse(null);
    }

    public GuiaDespacho actualizarGuia(Long id, GuiaDespacho guiaActualizada) {
        Optional<GuiaDespacho> guiaExistente = guiaRepository.findById(id);

        if (guiaExistente.isPresent()) {

            GuiaDespacho guia = guiaExistente.get();

            guia.setNumeroGuia(guiaActualizada.getNumeroGuia());
            guia.setTransportista(guiaActualizada.getTransportista());
            guia.setFecha(guiaActualizada.getFecha());
            guia.setRutaArchivo(guiaActualizada.getRutaArchivo());
            guia.setUrlS3(guiaActualizada.getUrlS3());
            guia.setEstado("ACTUALIZADA");

            return guiaRepository.save(guia);
        }

        return null;
    }

    public boolean eliminarGuia(Long id) {

        if (guiaRepository.existsById(id)) {
            guiaRepository.deleteById(id);

            return true;
        }

        return false;
    }

   public List<GuiaDespacho> buscarPorTransportista(String transportista) {

        return guiaRepository.findByTransportista(transportista);
    }

    public List<GuiaDespacho> buscarPorFecha(String fecha) {

        return guiaRepository.findByFecha(fecha);
    }
}