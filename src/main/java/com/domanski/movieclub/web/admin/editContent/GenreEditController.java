package com.domanski.movieclub.web.admin.editContent;

import com.domanski.movieclub.domain.genre.GenreService;
import com.domanski.movieclub.domain.genre.dto.GenreDto;
import com.domanski.movieclub.web.admin.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class GenreEditController {
    private final GenreService genreService;

    public GenreEditController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/admin/gatunki")
    public String getGenreList(Model model) {
        List<GenreDto> genres = genreService.findAllGenre();
        model.addAttribute("genres", genres);
        return "admin/edit-genre-panel";
    }

    @GetMapping("/admin/gatunki/{id}")
    public String getGenreToEdit(@PathVariable long id, Model model) {
        GenreDto genre = genreService.findGenreById(id).orElseThrow();
        model.addAttribute("genre", genre);
        return "admin/edit-genre";
    }

    @PostMapping("/admin/gatunki")
    public String editGenre(GenreDto genre, RedirectAttributes redirectAttributes) {
        genreService.editGenre(genre);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Gatunek %s został edytowany".formatted(genre.getName()));
        return "redirect:/admin";
    }

    @GetMapping("/admin/gatunki/usun/{id}")
    public String deleteGenre(@PathVariable long id, RedirectAttributes redirectAttributes) {
        GenreDto genre = genreService.findGenreById(id).orElseThrow();
        genreService.deleteGenre(id);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Gatunek %s został usunięty".formatted(genre.getName()));
        return "redirect:/admin";
    }
}
