package part4.part4.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import part4.part4.dto.MovieDTO;
import part4.part4.dto.PageRequestDTO;
import part4.part4.service.MovieService;

@Controller
@Log4j2
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes){
        log.info("movieDTO: "+movieDTO);

        Long mno = movieService.register(movieDTO);

        redirectAttributes.addFlashAttribute("msg",mno);

        return "redirect:/movie/list";
    }
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){


        model.addAttribute("result",movieService.getList(pageRequestDTO));
    }
    @GetMapping({"/read","/modify"})
    public void read(long mno, @ModelAttribute("pageRequestDTO")PageRequestDTO pageRequestDTO,Model model){
        MovieDTO movieDTO = movieService.getMovie(mno);

        model.addAttribute("dto",movieDTO);
    }
}
