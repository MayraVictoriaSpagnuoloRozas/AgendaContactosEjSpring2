package com.example.AgendaContactosEjSpring2.Controlador;

import com.example.AgendaContactosEjSpring2.Dto.UsuarioRegistroDto;
import com.example.AgendaContactosEjSpring2.Entidad.Contacto;
import com.example.AgendaContactosEjSpring2.servicio.ContactoServicio;
import com.example.AgendaContactosEjSpring2.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactoControlador {
    @Autowired//inyecta la dependencia necesaria
    private ContactoServicio contactoServicio;


    //--Falta inyección de UsuarioServicio (Probablemente el error que sale)
    @Autowired
    private UsuarioServicio Uservicio;

    //--Falta métodos para iniciar con el login, que es lo primero que vemos
    @GetMapping("/login")
    public String iniciarSesion(){
        return "login";
    }

    @GetMapping("/") //Esta ruta es de parte de seguridad, retornara index(lista de usuarios autorizados a ver la web)
    public String verPaginaDeInicio(Model modelo){
        modelo.addAttribute("usuarios", Uservicio.listarUsuarios());
        return "index"; //--como este se llama index, el de abajo(index viejo) deberemos cambiar
    }

    // -- Te añadi la Pagina de inicio del sistema(tenias el html pero no el metodo aca)
    @GetMapping("/home")
    public String home(){
        return "home";
    }


    @GetMapping("/listar")//--le cambie el nombre de la ruta por listar(antes era "/" pero esa ya existe)
    public String verPaginaDeInicioC(Model modelo) {//ModelMap pasa variables del controlador a nuestro html
        List<Contacto> contacto = contactoServicio.listarTodosLosContactos();

        modelo.addAttribute("contactos", contacto);//addAtribute manda 2 argumentos, el identificador
        //que coincide con el Thymeleaf y el objeto que queremos mandar por html
        return "lista"; //--lo cambié a "lista", ya que antes era index, pero ocupamos ese nombre cuando añadimos seguridad
    }


    @GetMapping("/new") //le cambie el nombre, ya que no respetaba con el del hmtl para retornar el form de registros
    public String crearContactos (Model modelo){ //se llamaba "listarContactos", lo cambie

        modelo.addAttribute("contacto", new Contacto());
        return "crear_contacto"; //decia que retornaba un html "nuevo"
        //-- como no existe lo cambie por "crear_contacto"
    }


    @PostMapping("/save")//asigna un metodo a la peticion de la vista
    public String guardarContacto(@Validated Contacto contacto, BindingResult bindingResult,
                                  RedirectAttributes redirect, Model modelo) {

        if(bindingResult.hasErrors()) {
            modelo.addAttribute("contacto", contacto);
            return "crear_contacto";
        }

        contactoServicio.guardarContacto(contacto);
        redirect.addFlashAttribute("msgExito", "El contacto ha" +
                " sido agregado con exito");

        return "redirect:/listar"; //cambie la redireccion
    }


    @GetMapping("/editar/{id}")//Path es ruta: / lo que viene despuès de la barra
    public String mostrarFormularioDeEditarContacto(@PathVariable Long id,Model modelo) {
        Contacto contacto = contactoServicio.obtenerContactoPorId(id);
        modelo.addAttribute("contacto", contacto);
        return "editar_contacto"; //--Cambie el html "editar" que no existe por "editar_contacto"
    }

    @PostMapping("/editar/{id}")
    public String actualizarContacto(@PathVariable Long id,@Validated Contacto contacto,
                                     BindingResult bindingResult,RedirectAttributes redirect,Model modelo) {

        Contacto contactoDB = contactoServicio.obtenerContactoPorId(id);
        if(bindingResult.hasErrors()) {
            modelo.addAttribute("contacto", contacto);
            return "editar_contacto"; //--Cambie el html "editar" que no existe por "editar_contacto"
        }

        contactoDB.setNombre(contacto.getNombre());
        contactoDB.setTelefono(contacto.getTelefono());


        contactoServicio.guardarContacto(contactoDB);
        redirect.addFlashAttribute("msgExito", "El contacto " +
                "ha sido actualizado correctamente");

        return "redirect:/listar";
    }

    //--Aca mezclaste el metodo eliminar con la funcion de js y el tipico, lo arregle por el tipico
    @GetMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable Long id,RedirectAttributes redirect) {
        Contacto contacto = contactoServicio.obtenerContactoPorId(id);

        contactoServicio.eliminarContacto((long) contacto.getId());

        redirect.addFlashAttribute("msgExito", "El contacto ha " +
                "sido eliminado correctamente");

        return "redirect:/listar";
    }

}

