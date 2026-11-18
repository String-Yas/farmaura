
package br.com.farmaura.controller;

import br.com.farmaura.security.PrincipalFarmaceutico;
import br.com.farmaura.service.ProdutoService;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaInicialController {

    private final ProdutoService produtoService;

    public PaginaInicialController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {

        boolean logado = authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);

        boolean isAdmin = false;
        boolean isFarmaceutico = false;
        String perfilStr = null;

        if (logado) {
            isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            isFarmaceutico = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_FARMACEUTICO"));

            if (authentication.getPrincipal() instanceof PrincipalFarmaceutico principal) {
                perfilStr = principal.getPerfil().name();
            }
        }

        model.addAttribute("usuarioLogado", logado);
        model.addAttribute("nomeUsuario", logado ? authentication.getName() : null);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isFarmaceutico", isFarmaceutico);
        model.addAttribute("podeGerenciarProdutos", isAdmin || isFarmaceutico);
        model.addAttribute("perfilUsuario", perfilStr);

        // NOVO: envia as categorias para o Thymeleaf
        model.addAttribute("categorias", produtoService.listarCategorias());

        return "index";
    }
}
