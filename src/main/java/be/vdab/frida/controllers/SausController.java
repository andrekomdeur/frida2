package be.vdab.frida.controllers;

import be.vdab.frida.services.SausService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sauzen")
class SausController {
/*    private final Saus[] sauzen = {
            new Saus(1L, "Cocktail",
                    new String[] {"Mayonaise","Tomatenketchup","Whiskey","Cayenne peper"}),
            new Saus(2L, "Mayonaise",
                    new String[] {"Eidooier","Citroensap","Olie","Zout","Peper"}),
            new Saus(3L, "Mosterd",
                    new String[] {"Mosterdzaad","Water","Zout","Azijn"}),
            new Saus(4L, "Tartare",
                    new String[] {"Ei","Mayonaise","Kappertjes","Augurk","Dragon"}),
            new Saus(5L, "Vinaigrette",
                    new String[] {"Olie","Water","Zout","Azijn"})};
*/
private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
private final SausService sausService;
    SausController(SausService sausService) {
        this.sausService = sausService;
    }    @GetMapping
    public ModelAndView sauzen(@CookieValue(name="kleur", required = false) String kleur) {
        return new ModelAndView("sauzen", "sauzen", sausService.findAll())
                .addObject("kleur", kleur);
    }
    @GetMapping("{nummer}")
    public ModelAndView saus(@PathVariable long nummer,@CookieValue(name="kleur", required = false) String kleur) {
        ModelAndView modelAndView = new ModelAndView("saus");
/*        Arrays.stream(sauzen)
                .filter(saus -> saus.getNummer() == nummer)
                .findFirst() */
        sausService.findById(nummer)
                .ifPresent(saus -> modelAndView.addObject(saus));
        return modelAndView.addObject("kleur", kleur);
    }
    @GetMapping("alfabet")
    public ModelAndView alfabet(@CookieValue(name="kleur", required = false) String kleur) {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet)
                .addObject("kleur", kleur);
    }
/*    private List<Saus> sauzenDieBeginnenMet(char letter) {
        return Arrays.stream(sauzen)
                .filter(saus->saus
                        .getNaam()
                        .toLowerCase()
                        .charAt(0) == letter)
                .collect(Collectors.toList());
    } */
    @GetMapping("alfabet/{letter}")
    public ModelAndView sauzenBeginnendMet(
            @PathVariable char letter,
            @CookieValue(name="kleur", required = false) String kleur) {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet)
                .addObject("sauzen",sausService.findByNaamBegintMet(letter))
                .addObject("kleur", kleur);
    }
}

