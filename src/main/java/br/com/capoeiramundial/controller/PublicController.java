package br.com.capoeiramundial.controller;

import br.com.capoeiramundial.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PublicController {
    private final EventRepository events;
    private final MemberRepository members;
    private final VideoRepository videos;
    private final TrainingScheduleRepository trainings;
    private final GalleryImageRepository gallery;
    private final SiteContentRepository contents;
    private final ContactInfoRepository contacts;

    public PublicController(EventRepository e, MemberRepository m, VideoRepository v, TrainingScheduleRepository t, GalleryImageRepository g, SiteContentRepository s, ContactInfoRepository c) {
        events = e;
        members = m;
        videos = v;
        trainings = t;
        gallery = g;
        contents = s;
        contacts = c;
    }

    @ModelAttribute
    void shared(Model m) {
        m.addAttribute("contacts", contacts.findByVisibleTrue());
        m.addAttribute("whatsapp", "https://wa.me/62982314005");
    }

    @GetMapping("/")
    String home(Model m) {
        m.addAttribute("events", events.findAllByOrderByEventDateAsc().stream().limit(3).toList());
        m.addAttribute("members", members.findAllByOrderByDisplayOrderAscNameAsc().stream().limit(4).toList());
        m.addAttribute("trainings", trainings.findAll());
        m.addAttribute("intro", content("home_intro", "Capoeira que move, acolhe e transforma."));
        m.addAttribute("heroImage", content("home_hero_image", ""));
        m.addAttribute("founderImage", content("home_founder_image", ""));
        m.addAttribute("graduationImage", content("home_graduation_image", ""));
        m.addAttribute("ctaImage", content("home_cta_image", ""));
        return "index";
    }

    @GetMapping("/agenda")
    String agenda(Model m) {
        m.addAttribute("trainings", trainings.findAll());
        return "agenda";
    }

    @GetMapping("/eventos")
    String eventos(Model m) {
        m.addAttribute("events", events.findAllByOrderByEventDateAsc());
        return "eventos";
    }

    @GetMapping("/integrantes")
    String integrantes(Model m) {
        m.addAttribute("members", members.findAllByOrderByDisplayOrderAscNameAsc());
        return "integrantes";
    }

    @GetMapping("/videos")
    String videos(Model m) {
        m.addAttribute("videos", videos.findAllByOrderByPublishedAtDesc());
        return "videos";
    }

    @GetMapping("/sobre")
    String sobre(Model m) {
        m.addAttribute("history", contents.findByContentKey("about_history").map(x -> x.getContentValue()).orElse("Nossa história é escrita em roda."));
        return "sobre";
    }

    @GetMapping("/contato")
    String contato(Model m) {
        m.addAttribute("trainings", trainings.findAll());
        return "contato";
    }

    @PostMapping("/contato")
    String contatoEnviar(@RequestParam String nome, @RequestParam String email, @RequestParam String mensagem, RedirectAttributes ra) {
        ra.addFlashAttribute("success", "Obrigado, " + nome + "! Sua mensagem foi recebida.");
        return "redirect:/contato";
    }

    @GetMapping("/admin/login")
    String login() {
        return "admin/login";
    }

    private String content(String key, String fallback) {
        return contents.findByContentKey(key).map(x -> x.getContentValue()).orElse(fallback);
    }
}
