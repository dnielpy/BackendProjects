package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

//    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository noteRepository;
    @Autowired ListRepository listRepository;

    //Notes CRUD
    @PostMapping("/createNote")
    public String createNote(@RequestParam String username, @RequestParam String tittle, @RequestParam String note){
        Notes notes = new Notes(username, tittle, note);
        if (noteRepository.findByTittle(tittle) == null) {
            noteRepository.save(notes);
            return "Nota guardada con exito";
        }
        else {
            return "Titulo de Nota ya existe en la base de datos";
        }
    }
    @GetMapping("/getNote")
    public String getNote(@RequestParam String tittle){
        Notes notes = noteRepository.findByTittle(tittle);
        if (notes == null) {
            return "La nota no existe";
        }
        else {
            //Rellenar esto
            String note_info = "Tittle: \n " + notes.getTittle() + "\nNote: " + notes.getNote();
            return note_info;
        }
    }
    @PutMapping("/updateNote")
    public String updateNote(@RequestParam String tittle, @RequestParam String note){
        Notes notes = noteRepository.findByTittle(tittle);
        if (notes == null) {
            return "La nota no existe";
        }
        else {
            notes.setTittle(tittle);
            notes.setNote(note);
            noteRepository.save(notes);
            return "Nota actualizada con exito";
        }
    }
    @PutMapping("/deleteNote")
    public String deleteNote(@RequestParam String tittle){
        Notes notes = noteRepository.findByTittle(tittle);
        if (notes == null) {
            return "La nota no existe";
        }
        else {
            noteRepository.delete(notes);
            return "Nota eliminada con exito";
        }
    }

    //List CRUD
    @PostMapping("/createList")
    public String createList(@RequestParam String username, @RequestParam String lists_tittle){
        Lists lists = new Lists(username, lists_tittle, null);
        if (listRepository.findByTittle(lists_tittle) == null) {
            listRepository.save(lists);
            return "Lista guardada con exito";
        }
        else {
            return "Titulo de Lista ya existe en la base de datos";
        }
    }
    @GetMapping("/getList")
    public String getList(@RequestParam String tittle){
        Lists lists = listRepository.findByTittle(tittle);
        if (lists == null) {
            return "La nota no existe";
        }
        else {
            //Rellenar esto
            String note_info = "Tittle: \n " + lists.getTittle() + "\nUser: " + lists.getUsername();
            return note_info;
        }
    }
    @PutMapping("/updateList")
    public String updateList(@RequestParam String tittle, @RequestParam String username){
        Lists lists = listRepository.findByTittle(tittle);
        if (lists == null) {
            return "La lista no existe";
        }
        else {
            lists.setTittle(tittle);
            lists.setUsername(username);
            listRepository.save(lists);
            return "Lista actualizada con exito";
        }
    }
    @PutMapping("/deleteList")
    public String deleteList(@RequestParam String tittle){
        Lists lists = listRepository.findByTittle(tittle);
        if (lists == null) {
            return "La lista no existe";
        }
        else {
            listRepository.delete(lists);
            return "Lista eliminada con exito";
        }
    }
    @PutMapping("/addList")
    public String addList(@RequestParam String list_tittle, @RequestParam String note_tittle){
        Lists lists = listRepository.findByTittle(list_tittle);
        Notes notes = noteRepository.findByTittle(note_tittle);
        if (lists == null) {
            return "La lista no existe";
        }
        else if (notes == null) {
            return "La nota no existe";
        }
        else {
            List<Notes> notes_list = lists.getNotes();
            notes_list.add(notes);
            lists.setNotes(notes_list);
            listRepository.save(lists);
            return "Nota agregada a la lista con exito";
        }
    }
}
