
package br.com.farmaura.security;

import br.com.farmaura.model.Usuario;
import br.com.farmaura.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class EstadoContaFilter extends OncePerRequestFilter {

    private final UsuarioRepository repository;

    public EstadoContaFilter(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        // Gera antecipadamente o token CSRF na página inicial.
        // Isso evita que o Thymeleaf precise criar uma sessão
        // durante a renderização dos formulários.
        boolean paginaInicial = "GET".equalsIgnoreCase(request.getMethod())
                && request.getRequestURI().equals(request.getContextPath() + "/");

        if (paginaInicial) {
            CsrfToken csrfToken = (CsrfToken) request.getAttribute(
                    CsrfToken.class.getName()
            );

            if (csrfToken != null) {
                csrfToken.getToken();
            }
        }

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth != null
                && auth.isAuthenticated()
                && auth.getPrincipal() instanceof PrincipalFarmaceutico principal) {

            Optional<Usuario> atual = repository.findById(principal.getId());

            boolean invalida = atual.isEmpty()
                    || !atual.get().isAtivo()
                    || !atual.get().getUsername().equals(principal.getUsername())
                    || atual.get().getPerfil() != principal.getPerfil();

            if (invalida) {
                SecurityContextHolder.clearContext();

                HttpSession session = request.getSession(false);

                if (session != null) {
                    session.invalidate();
                }

                response.sendRedirect(
                        request.getContextPath() + "/?expired"
                );

                return;
            }
        }

        chain.doFilter(request, response);
    }
}
